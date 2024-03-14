package com.demo.customer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.clients.notification.NotificationRequest;
import com.demo.clients.order.OrderClient;
import com.demo.clients.order.OrderRequest;
import com.demo.clients.order.OrderResponse;
import com.demo.clients.payment.PaymentClient;
import com.demo.clients.payment.PaymentRequest;
import com.demo.clients.payment.PaymentResponse;
import com.demo.clients.product.ProductClient;
import com.demo.customer.dao.CustomerEntity;
import com.demo.customer.dto.CustomerDTO;
import com.demo.customer.repository.CustomerRepository;
import com.demo.customer.utils.CustomerMapper;

import dev.nano.amqp.RabbitMQProducer;
import dev.nano.exception.domain.customer.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private final OrderClient orderClient;
	private final ProductClient productClient;
	private final PaymentClient paymentClient;
	private final RabbitMQProducer rabbitMQProducer;

	@Override
	public CustomerDTO getCustomer(Long id) throws CustomerNotFoundException {
		CustomerEntity customer = getCustomerFromId(id);
		return customerMapper.toDTO(customer);
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		// save customer
		CustomerEntity customer = customerRepository.save(customerMapper.toEntity(customerDTO));
		// send notification to notification microservice
		NotificationRequest notificationRequest = NotificationRequest.builder().customerId(customer.getId())
				.customerName(customer.getName()).customerEmail(customer.getEmail()).sender("nanodev")
				.message("Hello " + customer.getName() + ". Welcome to nanodev demo project on microservices").build();
		rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
		log.info("New customer created successfully {}", customer);
		return customerMapper.toDTO(customer);
	}

	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO customer) {
		CustomerEntity customerEntity = getCustomerFromId(customer.getId());
		customerEntity.setAddress(customer.getAddress());
		customerEntity.setName(customer.getName());
		customerEntity.setPhone(customer.getPhone());
		customerEntity.setAddress(customer.getAddress());
		customerRepository.save(customerEntity);
		return customer;
	}

	@Override
	public OrderResponse customerOrders(OrderRequest orderRequest) {
		getCustomerFromId(orderRequest.getCustomerId());
		productClient.getProduct(orderRequest.getProductId());
		return orderClient.createOrder(orderRequest);
	}

	@Override
	public PaymentResponse customerPayment(PaymentRequest paymentRequest) {
		getCustomerFromId(paymentRequest.getCustomerId());
		return paymentClient.createPayment(paymentRequest);
	}

	public CustomerEntity getCustomerFromId(Long id) {
		Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
		if (customerEntity.isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		return customerEntity.get();
	}
}