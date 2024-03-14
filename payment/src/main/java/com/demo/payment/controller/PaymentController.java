package com.demo.payment.controller;

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

import com.demo.clients.payment.PaymentRequest;
import com.demo.payment.dto.PaymentDTO;
import com.demo.payment.service.PaymentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/payments")
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping(path = "/{paymentId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PaymentDTO> getPayment(@PathVariable("paymentId") Long paymentId) {
		log.info("Retrieving payment with id {}", paymentId);
		return new ResponseEntity<>(paymentService.getPayment(paymentId), HttpStatus.OK);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PaymentDTO>> getAllPayments() {
		log.info("Retrieving all payments");
		return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
	}

	@PostMapping(path = "/make-new-payment")
	public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentRequest payment) {
		log.info("Retrieving all payments");
		return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
	}
}
