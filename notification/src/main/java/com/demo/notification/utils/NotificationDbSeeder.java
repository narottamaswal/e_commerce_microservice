package com.demo.notification.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.notification.dao.NotificationEntity;
import com.demo.notification.repository.NotificationRepository;

import java.time.LocalDateTime;

@Configuration
public class NotificationDbSeeder {

    @Bean
    CommandLineRunner commandLineRunner(NotificationRepository notificationRepository) {
        return args -> {
            NotificationEntity notification = NotificationEntity.builder()
                    .customerId(1L)
                    .customerName("Adnane Miliari Aka Nano")
                    .customerEmail("miliari.adnane@gmail.com")
                    .sender("NanoDev")
                    .message("Welcome to this demo in microservices")
                    .sentAt(LocalDateTime.now())
                    .build();

            notificationRepository.save(notification);
        };
    }
}
