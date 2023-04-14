package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
}