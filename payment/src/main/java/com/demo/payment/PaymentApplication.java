package com.demo.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableEurekaClient
@EnableFeignClients(basePackages = "dev.nano.clients")
@PropertySource("classpath:amqp-${spring.profiles.active}.properties")
@PropertySource("classpath:clients-${spring.profiles.active}.properties")
@SpringBootApplication(scanBasePackages = { "com.demo.payment", "dev.nano.amqp" })
public class PaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
}
