package com.example.bank.Bank_Service.repository;

import com.example.bank.Bank_Service.model.entity.MasterBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterBalanceRepository extends JpaRepository<MasterBalanceEntity, Long> {

}