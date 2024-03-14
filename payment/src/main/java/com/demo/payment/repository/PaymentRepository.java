package com.demo.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.payment.dao.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
