package com.demo.payment.utils;

import org.mapstruct.Mapper;

import com.demo.payment.dao.PaymentEntity;
import com.demo.payment.dto.PaymentDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity toEntity(PaymentDTO payment);
    List<PaymentEntity> toListEntity(List<PaymentDTO> paymentDTOList);
    PaymentDTO toDTO(PaymentEntity entity);
    List<PaymentDTO> toListDTO(List<PaymentEntity> paymentEntityList);
}
