package com.crm.sofia.dto.auth;

import com.crm.sofia.dto.user.UserDTO;
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

  private String jwt;

  private UserDTO user;

}
