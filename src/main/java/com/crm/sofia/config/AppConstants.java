package com.crm.sofia.config;

public class AppConstants {
    public class Jwt {

        // The unique JWT id.
        public static final String JWT_CLAIM_ID = "jti";
        public static final String JWT_CLAIM_CREATED_AT = "iat";
        public static final String JWT_CLAIM_EMAIL = "sub";
        public static final String JWT_CLAIM_ISSUER = "iss";
        public static final String JWT_CLAIM_EXPIRES_AT = "exp";
        public static final String JWT_CLAIM_USER_ID = "user_id";
        public static final String JWT_CLAIM_SESSION_ID = "session_id";
    }
    public static class Types {

        public enum UserStatus {
            enabled,
            disabled,
            deleted
        }
    }

}
