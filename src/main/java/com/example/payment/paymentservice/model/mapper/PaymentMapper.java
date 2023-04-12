package com.example.payment.paymentservice.model.mapper;

import com.example.payment.paymentservice.model.dto.PaymentDto;
import com.example.payment.paymentservice.model.entity.PaymentEntity;
import org.springframework.beans.BeanUtils;

public class PaymentMapper extends BaseMapper<PaymentEntity, PaymentDto> {
    @Override
    public PaymentEntity convertToEntity(PaymentDto dto, Object... args) {
        PaymentEntity entity = new PaymentEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public PaymentDto convertToDto(PaymentEntity entity, Object... args) {
        PaymentDto dto = new PaymentDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}