package com.demo.order.utils;

import org.mapstruct.Mapper;

import com.demo.order.dao.OrderEntity;
import com.demo.order.dto.OrderDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity toEntity(OrderDTO dto);
    List<OrderEntity> toListEntity(List<OrderDTO> listDTO);
    OrderDTO toDTO(OrderEntity entity);
    List<OrderDTO> toListDTO(List<OrderEntity> listEntity);

}
