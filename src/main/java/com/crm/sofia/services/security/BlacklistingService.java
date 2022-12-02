package com.crm.sofia.services.security;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BlacklistingService {

    @CachePut("jwt-black-list")
    public String blackListJwt(String jwt) {
        return jwt;
    }

    @Cacheable(value = "jwt-black-list", unless = "#result == null")
    public String getJwtBlackList(String jwt) {
        return null;
    }

}
