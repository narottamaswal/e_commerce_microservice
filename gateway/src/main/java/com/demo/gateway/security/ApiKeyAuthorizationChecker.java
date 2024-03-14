package com.demo.gateway.security;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String apiKey, String applicationName);
}
