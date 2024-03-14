package com.demo.clients.product;

import lombok.Data;

@Data
public class ProductResponse {
    private String name;
    private String image;
    private Integer price;
    private long quantity;
}
