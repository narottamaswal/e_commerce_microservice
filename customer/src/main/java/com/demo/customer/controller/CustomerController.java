package com.demo.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.clients.order.OrderRequest;
import com.demo.clients.order.OrderResponse;
import com.demo.clients.payment.PaymentRequest;
import com.demo.clients.payment.PaymentResponse;
import com.demo.customer.dto.CustomerDTO;
import com.demo.customer.service.CustomerService;

import dev.nano.exception.domain.customer.CustomerNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/customers")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping(path = "/{customerId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") Long customerId)
			throws CustomerNotFoundException {

		log.info("Retrieving customer with id {}", customerId);
		return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
	}

	@PostMapping(path = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
		log.info("Add new customer {}", customerDTO);
		return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
	}

	@PostMapping(path = "/orders", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@CircuitBreaker(name = "customerOrdersDetails")
	public ResponseEntity<OrderResponse> customerOrders(@RequestBody OrderRequest orderRequest) {
		log.info("Customer orders {}", orderRequest);
		return new ResponseEntity<>(customerService.customerOrders(orderRequest), HttpStatus.CREATED);
	}

	@PostMapping(path = "/payment", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PaymentResponse> customerPayment(@RequestBody PaymentRequest paymentRequest) {
		log.info("Customer payment {}", paymentRequest);
		return new ResponseEntity<>(customerService.customerPayment(paymentRequest), HttpStatus.CREATED);
	}
}