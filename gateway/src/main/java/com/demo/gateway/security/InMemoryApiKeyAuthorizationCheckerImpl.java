package com.demo.gateway.security;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("inMemory-checker")
public class InMemoryApiKeyAuthorizationCheckerImpl implements ApiKeyAuthorizationChecker {

	private static Environment environment;
	private static final String IN_MEMORY_API_KEY_1 = "API_KEY_1";
	private static final String IN_MEMORY_API_KEY_2 = "API_KEY_2";
	private static final Map<String, List<String>> apiKeys = Map.of(IN_MEMORY_API_KEY_1,
			List.of("CUSTOMER", "PRODUCT", "NOTIFICATION", "ORDER", "APIKEY-MANAGER"), IN_MEMORY_API_KEY_2,
			List.of("PAYMENT"));

	@Override
	public boolean isAuthorized(String apiKey, String applicationName) {

		return apiKeys.getOrDefault(apiKeys, List.of()).stream().anyMatch(a -> a.contains(applicationName));
	}
}
