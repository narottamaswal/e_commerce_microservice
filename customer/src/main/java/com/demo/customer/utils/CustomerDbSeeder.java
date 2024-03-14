package com.demo.customer.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.customer.dao.CustomerEntity;
import com.demo.customer.repository.CustomerRepository;

@Configuration
public class CustomerDbSeeder {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            CustomerEntity customer = CustomerEntity.builder()
                    .name("Adnane Miliari")
                    .email("miliari.adnane@gmail.com")
                    .phone("+61112223333")
                    .address("Rabat, Morocco")
                    .build();
            customerRepository.save(customer);
        };
    }
}
