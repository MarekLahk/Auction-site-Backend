package com.ibay.backend.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibay.backend.dao.AuthDao;
import com.ibay.backend.model.Credentials;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class JwtUserAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final AuthDao authDao;

    public JwtUserAuthFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, AuthDao authDao) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.authDao = authDao;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Credentials credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword()
            );
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        String token = Jwts.builder()
                .setId(String.valueOf(UUID.randomUUID()))
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(jwtConfig.getSecretKeyForSigning())
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String id = authDao.selectUserByUsername(authResult.getName()).get().getId();
        response.getWriter().write(id);
    }




}
