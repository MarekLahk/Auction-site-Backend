package com.ibay.backend.jwt;

import com.google.common.base.Strings;
import com.ibay.backend.dao.AuthDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final AuthDao authDao;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig, AuthDao authDao) {
        this.jwtConfig = jwtConfig;
        this.authDao = authDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
        } else {

            try {

                String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(jwtConfig.getSecretKeyForSigning())
                        .build()
                        .parseClaimsJws(token);

                if (!isTokenValid(claimsJws.getBody().getId())) { throw new JwtException(""); }

                Claims body = claimsJws.getBody();

                String username = body.getSubject();

                var authorities = (List<Map<String, String>>) body.get("authorities");

                Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        authDao.selectUserByUsername(username).get(),
                        null,
                        grantedAuthorities
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (JwtException e) {
                throw new IllegalStateException("Unauthorized token");
            }
        }
    }

    private Boolean isTokenValid(String id) {
        return authDao.isTokenActive(id);
    }
}
