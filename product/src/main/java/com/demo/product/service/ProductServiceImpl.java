package com.demo.product.service;

import static dev.nano.exception.constant.ExceptionConstant.INSUFFICIENT_PRODUCT_QUANTITY_EXCEPTION_MESSAGE;
import static dev.nano.exception.constant.ExceptionConstant.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.product.dao.ProductEntity;
import com.demo.product.dto.ProductDTO;
import com.demo.product.mapper.ProductMapper;
import com.demo.product.repository.ProductRepository;

import dev.nano.exception.domain.product.InsufficientProductQuantityException;
import dev.nano.exception.domain.product.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductMapper productMapper;
	private final ProductRepository productRepository;

	@Override
	public ProductDTO getProduct(long productId) {
		ProductEntity product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
		return productMapper.toDTO(product);
	}

	@Override
	public List<ProductDTO> getAllProducts(int page, int limit, String search) {
		if (page > 0) {
			page = page - 1;
		}

		List<ProductDTO> productDTOList = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ProductEntity> productDTOPage;

		if (search == null || search.isEmpty()) {
			productDTOPage = productRepository.findAllProducts(pageableRequest);
		} else {
			productDTOPage = productRepository.findAllProductsByCriteria(pageableRequest, search);
		}

		List<ProductEntity> products = productDTOPage.getContent();
		productDTOList.addAll(productMapper.toListDTO(products));

		return productDTOList;
	}

	@Override
	public ProductDTO create(ProductDTO product) {
		ProductEntity productEntity = ProductEntity.builder().image(product.getImage()).name(product.getName())
				.price(product.getPrice()).quantity(product.getQuantity()).build();
		productRepository.save(productEntity);
		product.setId(productEntity.getId());
		return product;
	}

	@Override
	public ProductDTO update(long id, ProductDTO product) {
		Optional<ProductEntity> productEntityObj = productRepository.findById(id);
		if (productEntityObj.isEmpty()) {
			throw new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		ProductEntity productEntity = productEntityObj.get();
		productEntity.setImage(product.getImage());
		productEntity.setName(product.getName());
		productEntity.setPrice(product.getPrice());
		productEntity.setQuantity(product.getQuantity());
		productRepository.save(productEntity);
		return product;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Reduce Quantity {} for Id: {}", quantity, productId);
		ProductEntity product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
		if (product.getQuantity() < quantity) {
			throw new InsufficientProductQuantityException(INSUFFICIENT_PRODUCT_QUANTITY_EXCEPTION_MESSAGE);
		}
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		log.info("Quantity reduced successfully");
	}

}
