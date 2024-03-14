package com.demo.order.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.order.dao.OrderEntity;
import com.demo.order.repository.OrderRepository;

import java.time.LocalDateTime;

@Configuration
public class OrderDbSeeder {

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository) {
        return args -> {
            OrderEntity order = OrderEntity.builder()
                    .customerId(1L)
                    .productId(1L)
                    .amount(10)
                    .createAt(LocalDateTime.now()).build();
            orderRepository.save(order);
        };
    }
}
