package com.demo.product.mapper;

import org.mapstruct.Mapper;

import com.demo.product.dao.ProductEntity;
import com.demo.product.dto.ProductDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(ProductDTO dto);
    List<ProductEntity> toListEntity(List<ProductDTO> listDTO);
    ProductDTO toDTO(ProductEntity entity);
    List<ProductDTO> toListDTO(List<ProductEntity> listEntity);

}
