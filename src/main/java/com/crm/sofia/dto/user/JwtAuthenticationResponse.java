package com.crm.sofia.dto.user;

import lombok.Value;

@Value
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private UserDTO user;

    public JwtAuthenticationResponse(String accessToken, String refreshToken, UserDTO user){
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
