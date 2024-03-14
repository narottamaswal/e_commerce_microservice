package com.demo.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
	private long id;
	private String name;
	private String email;
	private String phone;
	private String address;
}
