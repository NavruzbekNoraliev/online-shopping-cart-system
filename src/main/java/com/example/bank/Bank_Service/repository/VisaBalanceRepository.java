package com.example.bank.Bank_Service.repository;

import com.example.bank.Bank_Service.model.entity.VisaBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaBalanceRepository extends JpaRepository<VisaBalanceEntity, Long> {
}

