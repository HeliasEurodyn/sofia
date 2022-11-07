package com.crm.sofia.dto.user;

import lombok.Value;

@Value
public class JwtAuthenticationResponse {
    private String accessToken;
    private UserDTO user;
}
