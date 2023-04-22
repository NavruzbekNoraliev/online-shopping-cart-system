package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByVendorId(Long vendorId);

    List<TransactionEntity> findByUserId(Long userId);
}
