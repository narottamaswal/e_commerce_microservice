package com.demo.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.order.dao.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
