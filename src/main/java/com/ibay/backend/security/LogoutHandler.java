package com.ibay.backend.security;

import com.google.common.base.Strings;
import com.ibay.backend.dao.AuthDao;
import com.ibay.backend.jwt.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


public class LogoutHandler implements LogoutSuccessHandler {

    private final JwtConfig jwtConfig;
    private final AuthDao authDao;

    public LogoutHandler(JwtConfig jwtConfig, AuthDao authDao) {
        this.jwtConfig = jwtConfig;

        this.authDao = authDao;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Logging out");

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if (!Strings.isNullOrEmpty(authorizationHeader) && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            try {
                String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(jwtConfig.getSecretKeyForSigning())
                        .build()
                        .parseClaimsJws(token);

                if (authDao.isTokenActive(claimsJws.getBody().getId())) {
                    authDao.addTokenInactive(claimsJws.getBody().getId(), new Timestamp(claimsJws.getBody().getExpiration().getTime()));
                }
            } catch (JwtException ignored) {
            }

        }
    }
}
