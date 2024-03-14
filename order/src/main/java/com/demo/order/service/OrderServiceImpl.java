package com.demo.order.service;

import static dev.nano.exception.constant.ExceptionConstant.ORDER_NOT_FOUND_EXCEPTION_MESSAGE;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.clients.notification.NotificationRequest;
import com.demo.clients.order.OrderRequest;
import com.demo.clients.product.ProductClient;
import com.demo.order.dao.OrderEntity;
import com.demo.order.dto.OrderDTO;
import com.demo.order.repository.OrderRepository;
import com.demo.order.utils.OrderMapper;

import dev.nano.amqp.RabbitMQProducer;
import dev.nano.exception.domain.order.OrderNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final ProductClient productClient;
	private final RabbitMQProducer rabbitMQProducer;

	@Override
	public OrderDTO getOrder(Long id) {
		return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_EXCEPTION_MESSAGE)));
	}
	@Override
	public List<OrderDTO> getAllOrders() {
		return orderMapper.toListDTO(orderRepository.findAll());
	}

	@Override
	public OrderDTO createOrder(OrderRequest order) {
		// Check if product is exist
		productClient.getProduct(order.getProductId());
		// Create order
		OrderEntity orderEntity = orderRepository.save(OrderEntity.builder().customerId(order.getCustomerId())
				.productId(order.getProductId()).amount(order.getAmount()).createAt(LocalDateTime.now()).build());
		// Create notificationRequest
		NotificationRequest notificationRequest = NotificationRequest.builder().customerId(order.getCustomerId())
				.customerName(order.getCustomerName()).customerEmail(order.getCustomerEmail()).sender("NanoDev")
				.message("Your order has been success.").build();
		// Send notification
		rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
		return orderMapper.toDTO(orderEntity);
	}
}