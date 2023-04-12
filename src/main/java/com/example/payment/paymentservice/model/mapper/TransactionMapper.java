package com.example.payment.paymentservice.model.mapper;

import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import org.springframework.beans.BeanUtils;

public class TransactionMapper extends BaseMapper<TransactionEntity, TransactionDto> {
    @Override
    public TransactionEntity convertToEntity(TransactionDto dto, Object... args) {
        TransactionEntity entity = new TransactionEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public TransactionDto convertToDto(TransactionEntity entity, Object... args) {
        TransactionDto dto = new TransactionDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}