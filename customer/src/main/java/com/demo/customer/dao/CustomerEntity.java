package com.demo.customer.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Entity(name = "Customer")
@Table(name = "customer", uniqueConstraints = {
		/* customize unique constraint */
		@UniqueConstraint(name = "customer_email_unique", columnNames = "email") })
public class CustomerEntity {

	@Id
	@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", columnDefinition = "TEXT")
	private String phone;

	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
}
