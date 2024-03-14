package com.demo.order.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "Order")
@Table(name = "orders")
public class OrderEntity {

	@Id
	@SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createAt;
}
