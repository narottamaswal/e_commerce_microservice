package com.demo.payment.service;

import static dev.nano.exception.constant.ExceptionConstant.PAYMENT_NOT_FOUND_EXCEPTION_MESSAGE;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.clients.notification.NotificationRequest;
import com.demo.clients.order.OrderClient;
import com.demo.clients.payment.PaymentRequest;
import com.demo.payment.dao.PaymentEntity;
import com.demo.payment.dto.PaymentDTO;
import com.demo.payment.repository.PaymentRepository;
import com.demo.payment.utils.PaymentMapper;

import dev.nano.amqp.RabbitMQProducer;
import dev.nano.exception.domain.payment.PaymentNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final PaymentMapper paymentMapper;
	private final OrderClient orderClient;
	private final RabbitMQProducer rabbitMQProducer;

	@Override
	public PaymentDTO getPayment(Long paymentId) {
		PaymentEntity payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new PaymentNotFoundException(PAYMENT_NOT_FOUND_EXCEPTION_MESSAGE));
		return paymentMapper.toDTO(payment);
	}

	@Override
	public List<PaymentDTO> getAllPayments() {
		List<PaymentEntity> paymentList = paymentRepository.findAll();
		return paymentMapper.toListDTO(paymentList);
	}

	@Override
	public PaymentDTO createPayment(PaymentRequest payment) {
		// find order
		orderClient.getOrder(payment.getOrderId());
		// add new payment
		PaymentEntity paymentEntity = paymentRepository.save(PaymentEntity.builder().customerId(payment.getCustomerId())
				.orderId(payment.getOrderId()).createAt(LocalDateTime.now()).build());
		// create notification request
		NotificationRequest notificationRequest = NotificationRequest.builder().customerId(payment.getCustomerId())
				.customerName(payment.getCustomerName()).customerEmail(payment.getCustomerEmail()).sender("nanodev")
				.message("Your payment passed successfully. Thank you").build();
		// publishing notification to rabbitmq
		rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);

		return paymentMapper.toDTO(paymentEntity);
	}
}
