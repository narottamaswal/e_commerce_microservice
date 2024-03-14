package com.demo.gateway.security;

import org.springframework.stereotype.Service;

import com.demo.clients.api_key.ApiKeyManagerClient;

import lombok.AllArgsConstructor;

@Service("main-checker")
@AllArgsConstructor
public class ApiKeyAuthorizationCheckerImpl implements ApiKeyAuthorizationChecker {
	private final ApiKeyManagerClient apiKeyManagerClient;

	@Override
	public boolean isAuthorized(String apiKey, String applicationName) {
		return apiKeyManagerClient.isKeyAuthorizedForApplication(apiKey, applicationName).isAuthorized();
	}
}
