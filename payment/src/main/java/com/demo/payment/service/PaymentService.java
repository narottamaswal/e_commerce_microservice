package com.demo.payment.service;

import java.util.List;

import com.demo.clients.payment.PaymentRequest;
import com.demo.payment.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO getPayment(Long paymentId);
    List<PaymentDTO> getAllPayments();
    PaymentDTO createPayment(PaymentRequest payment);
}
