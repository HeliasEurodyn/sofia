package com.crm.sofia.security.oauth2.user;

import java.util.Map;

public class KeycloakOAuth2UserInfo extends OAuth2UserInfo {

    public KeycloakOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sid");
    }

    @Override
    public String getName() {
        return (String) attributes.get("preferred_username");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("preferred_username");
    }

    @Override
    public String getProvider() {
        return "keycloak";
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
