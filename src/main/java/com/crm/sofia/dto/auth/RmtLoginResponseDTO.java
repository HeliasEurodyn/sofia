package com.crm.sofia.dto.auth;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RmtLoginResponseDTO {
  private String access_token;
  private Integer expires_in;
  private String token_type;
}
