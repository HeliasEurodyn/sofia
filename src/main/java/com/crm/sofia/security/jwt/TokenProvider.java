package com.crm.sofia.security.jwt;

import com.crm.sofia.config.AppProperties;
import com.crm.sofia.model.user.LocalUser;
import com.crm.sofia.repository.user.TokenRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
public class TokenProvider {

    private final TokenRepository tokenRepository;

    private final AppProperties appProperties;

    public TokenProvider(AppProperties appProperties,
                         TokenRepository tokenRepository) {
        this.appProperties = appProperties;
        this.tokenRepository = tokenRepository;
    }

    public String createToken(Authentication authentication) {
        LocalUser userPrincipal = (LocalUser) authentication.getPrincipal();
        return this.createToken(userPrincipal.getUser().getId());
    }

    public String createToken(String userId) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder().setSubject(userId).setIssuedAt(new Date()).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret()).compact();
    }

    public String createRefreshToken(Authentication authentication) {
        LocalUser userPrincipal = (LocalUser) authentication.getPrincipal();
        return this.createRefreshToken(userPrincipal.getUser().getId());
    }

    public String createRefreshToken(String userId) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getRefreshTokenExpirationMsec());

        return Jwts
                .builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String jwt) {

        try {

            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);

            if (!isTokenValid) {
                return false;
            }

            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(jwt);
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
