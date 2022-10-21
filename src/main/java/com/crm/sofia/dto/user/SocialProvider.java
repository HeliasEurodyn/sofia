package com.crm.sofia.dto.user;

public enum SocialProvider {

    FACEBOOK("facebook"),
    TWITTER("twitter"),
    LINKEDIN("linkedin"),
    GOOGLE("google"),
    GITHUB("github"),
    KEYCLOAK("keycloak"),
    KEYROCK("keyrock"),
    LOCAL("local");

    private String providerType;

    public String getProviderType() {
        return providerType;
    }

    SocialProvider(final String providerType) {
        this.providerType = providerType;
    }
}
