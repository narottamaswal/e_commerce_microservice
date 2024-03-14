package com.demo.order.controller;

import java.util.List;

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
import com.demo.order.dto.OrderDTO;
import com.demo.order.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class OrderController {

	private final OrderService orderService;

	@PostMapping(path = "/create")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest) {
		log.info("Retrieving all orders");
		return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
	}

	@GetMapping(path = "/status/{orderId}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderId") Long orderId) {
		log.info("Retrieving order with id {}", orderId);
		return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		log.info("Retrieving all orders");
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}
}