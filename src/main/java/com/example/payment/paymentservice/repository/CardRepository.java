package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findByCardNumber(String cardNumber);
    void deleteById(Long id);

}