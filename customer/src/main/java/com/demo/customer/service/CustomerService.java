package com.demo.customer.service;

import com.demo.clients.order.OrderRequest;
import com.demo.clients.order.OrderResponse;
import com.demo.clients.payment.PaymentRequest;
import com.demo.clients.payment.PaymentResponse;
import com.demo.customer.dto.CustomerDTO;

public interface CustomerService {

	CustomerDTO getCustomer(Long id);

	CustomerDTO createCustomer(CustomerDTO customer);

	CustomerDTO updateCustomer(Long id, CustomerDTO customer);

	OrderResponse customerOrders(OrderRequest orderRequest);

	PaymentResponse customerPayment(PaymentRequest paymentRequest);
}
