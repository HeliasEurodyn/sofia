package com.crm.sofia.config.users.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The list of properties defined in application.properties.
 */
@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "com.crm.sofia")
public class AuthProperties {

  // The secret to sign JWT.
  private String jwtSecret;

  // The number of minutes a JWT is valid for.
  private int jwtTtlMinutes;

  // The issuer of the JWT.
  private String jwtIssuer;
}
