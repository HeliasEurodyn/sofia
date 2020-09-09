package com.crm.sofia.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A placeholder for a JWT.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JWTResponseDTO {

  // The JWT to include.
  private String jwt;

  // A helper flag to indicate that 2FA is required.
  private boolean requires2FA;
}
