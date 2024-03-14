package com.demo.product.service;

import java.util.List;

import com.demo.product.dto.ProductDTO;

public interface ProductService {
    ProductDTO create(ProductDTO product);
    ProductDTO getProduct(long productId);
    List<ProductDTO> getAllProducts(int page, int limit, String search);
    ProductDTO update(long id, ProductDTO request);
    void reduceQuantity(long productId, long quantity);
}
