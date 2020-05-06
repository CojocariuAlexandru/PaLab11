package com.gomokumanager.GomokuManager.security;

/**
 * Contains constants for working with JWT
 */

public class JwtProperties {
    public static final String SECRET = "randomSecret123";
    public static final int EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
