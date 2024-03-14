package com.demo.customer.utils;

import org.mapstruct.Mapper;

import com.demo.customer.dao.CustomerEntity;
import com.demo.customer.dto.CustomerDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

        CustomerEntity toEntity(CustomerDTO dto);
        List<CustomerEntity> toListEntity(List<CustomerDTO> listDTO);
        CustomerDTO toDTO(CustomerEntity entity);
        List<CustomerDTO> toListDTO(List<CustomerEntity> listEntity);

}
