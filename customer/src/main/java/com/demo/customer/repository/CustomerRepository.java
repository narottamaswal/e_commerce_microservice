package com.demo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.customer.dao.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
