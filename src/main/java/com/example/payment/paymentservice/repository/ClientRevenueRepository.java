package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.entity.ClientRevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRevenueRepository extends JpaRepository<ClientRevenueEntity, Long> {
}
