package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.dto.PaymentDto;
import com.example.payment.paymentservice.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<PaymentEntity, PaymentDto> {
}
