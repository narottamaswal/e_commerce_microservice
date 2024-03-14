package com.demo.customer.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	public static final String SWAGGER_GROUP_NAME = "customer";
	public static final String SWAGGER_CONFIG_BASE_PACKAGE = "com.demo.customer";
	public static final String SWAGGER_CONFIG_TITLE = "Customer Service Api Documentation";
	public static final String SWAGGER_CONFIG_DESCRIPTION = "Documentation automatically generated";
	public static final String SWAGGER_CONFIG_VERSION = "1.0";
	public static final String SWAGGER_CONTACT_CONTACT_NAME = "https://github.com/miliariadnane";
	public static final String SWAGGER_LICENSE_NAME = "Apache 2.0";
	public static final String SWAGGER_LICENSE_URL = "http://springdoc.org";

	@Bean
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group(SWAGGER_GROUP_NAME).packagesToScan(SWAGGER_CONFIG_BASE_PACKAGE).build();
	}

	@Bean
	OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title(SWAGGER_CONFIG_TITLE).description(SWAGGER_CONFIG_DESCRIPTION)
				.version(SWAGGER_CONFIG_VERSION).contact(new Contact().name(SWAGGER_CONTACT_CONTACT_NAME))
				.license(new License().name(SWAGGER_LICENSE_NAME).url(SWAGGER_LICENSE_URL)));
	}
}
