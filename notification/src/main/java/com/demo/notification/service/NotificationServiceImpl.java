package com.demo.notification.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.demo.clients.notification.NotificationRequest;
import com.demo.notification.aws.AWSEmailService;
import com.demo.notification.dao.NotificationEntity;
import com.demo.notification.repository.NotificationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final AWSEmailService emailService;

	@SuppressWarnings("static-access")
	@Override
	public void sendNotification(NotificationRequest notificationRequest) {

		notificationRepository.save(NotificationEntity.builder().customerId(notificationRequest.getCustomerId())
				.customerName(notificationRequest.getCustomerName())
				.customerEmail(notificationRequest.getCustomerEmail()).sender("nanodev")
				.message(notificationRequest.getMessage()).sentAt(LocalDateTime.now()).build());
		String emailMessage = """
				        Mail sent to: ${name}
				        <p>${message}</p>
				""";
		emailMessage = emailMessage.format(notificationRequest.getCustomerName(), notificationRequest.getMessage());
		emailService.send(notificationRequest.getCustomerEmail(), emailMessage, notificationRequest.getSender());
	}
	
}
