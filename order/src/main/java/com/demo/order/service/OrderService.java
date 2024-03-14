package com.demo.order.service;

import java.util.List;

import com.demo.clients.order.OrderRequest;
import com.demo.order.dto.OrderDTO;

public interface OrderService {
	OrderDTO getOrder(Long id);

	List<OrderDTO> getAllOrders();

	OrderDTO createOrder(OrderRequest order);
}
