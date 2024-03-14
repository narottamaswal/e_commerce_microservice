package com.demo.notification.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.clients.notification.NotificationRequest;
import com.demo.notification.service.NotificationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/notifications")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class NotificationController {

	private final NotificationService notificationService;

	@PostMapping(path = "/send")
	public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
		log.info("Sending new notification {}", notificationRequest);
		notificationService.sendNotification(notificationRequest);
	}
}
