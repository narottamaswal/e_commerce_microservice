package com.demo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private long id;
	private String name;
	private String image;
	private int price;
	private long quantity;
}
