package com.crm.sofia.services.auth;


import com.crm.sofia.config.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class JWTService {

    private AppProperties appProperties;

    public JWTService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String getUserId() {

        String token = this.getJwt();

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
}
