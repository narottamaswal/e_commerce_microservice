package com.demo.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.notification.dao.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
