package com.crm.sofia.services.auth;


import com.crm.sofia.config.AppProperties;
import com.crm.sofia.model.user.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
public class JWTService {

    private AppProperties appProperties;

    public JWTService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String getUserId() {
        String token = this.getJwt();
        return this.getUserIdByToken(token);
    }

    public String getUserIdByToken(String token) {

        if(token == null){
            return "";
        }

        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    private String getJwt() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if(servletRequestAttributes == null) {
            return null;
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public Date extractExpiration(String token) {
        final Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public boolean isTokenValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
