package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.dto.UtilityPayment;
import com.example.payment.paymentservice.model.entity.UtilityPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UtilityPaymentRepository extends JpaRepository<UtilityPaymentEntity, UtilityPayment> {
}
