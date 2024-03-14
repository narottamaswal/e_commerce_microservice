package com.demo.product.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Entity(name = "Product")
@Table(name = "product")
public class ProductEntity {

	@Id
	@SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	private String name;

	@Column(name = "image", columnDefinition = "TEXT")
	private String image;

	@Column(name = "price", nullable = false, columnDefinition = "INTEGER")
	private Integer price;

	@Column(name = "quantity", nullable = false)
	private long quantity;
}
