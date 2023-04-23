package com.example.bank.Bank_Service.service;

import com.example.bank.Bank_Service.TestObjectTest;
import com.example.bank.Bank_Service.client.PaymentClient;
import com.example.bank.Bank_Service.model.OrderItemDto;
import com.example.bank.Bank_Service.model.TransactionStatus;
import com.example.bank.Bank_Service.model.entity.MasterBalanceEntity;
import com.example.bank.Bank_Service.model.entity.VisaBalanceEntity;
import com.example.bank.Bank_Service.repository.MasterBalanceRepository;
import com.example.bank.Bank_Service.repository.VisaBalanceRepository;
import com.example.bank.Bank_Service.rest.request.RequestedCard;
import com.example.bank.Bank_Service.rest.request.TransactionRequest;
import com.example.bank.Bank_Service.rest.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankService {


    private final VisaBalanceRepository visaBalanceRepository;
    private final MasterBalanceRepository masterBalanceRepository;


    @Autowired
    private PaymentClient paymentClient;

    @KafkaListener(topics = "test-topic", groupId = "groupId")
    public void receiveTestObject(String id, TestObjectTest testObject) {
        System.out.println("Received TestObjectTest with id " + id + " and name " + testObject.getName());
    }


    public RequestedCard getCardDetails(String cardNumber) {
        RequestedCard card = paymentClient.getCardDetails(cardNumber);
        return card;
        // do something with the balance
    }
    public List<VisaBalanceEntity> readVisaBalances(Pageable pageable) {
        Page<VisaBalanceEntity> allVisaBalance = visaBalanceRepository.findAll(pageable);
        return allVisaBalance.getContent();
    }
    public List<MasterBalanceEntity> readMasterBalances(Pageable pageable) {
        Page<MasterBalanceEntity> allMasterBalance = masterBalanceRepository.findAll(pageable);
        return allMasterBalance.getContent();
    }

    public static boolean isVisaCard(String cardNumber) {
        // Visa card numbers start with 4 and are 13 or 16 digits long
        return cardNumber.matches("^4[0-9]{12}(?:[0-9]{3})?$");
    }

    public static boolean isMasterCard(String cardNumber) {
        // Mastercard numbers start with 51 through 55, or 2221 through 2720 and are 16 digits long
        return cardNumber.matches("^5[1-5][0-9]{14}$|^2(2(2[1-9]|[3-9][0-9])|[3-6][0-9]{2}|7(0[0-9]|1[0-9]|2[0-0]))[0-9]{11}$");
    }




    public TransactionResponse checkCard(RequestedCard request , TransactionRequest transactionRequest){
        String transactionNum = UUID.randomUUID().toString();
        TransactionStatus transactionStatus;

        if(request.getCurrentValue().compareTo(transactionRequest.getTransactionAmount()) >= 0) {
            transactionStatus = TransactionStatus.TS;
        }else {
            transactionStatus = TransactionStatus.TF;
        }

        if(isVisaCard(request.getCardNumber())){
            VisaBalanceEntity entity = new VisaBalanceEntity();
            entity.setCardNumber(request.getCardNumber());
            entity.setCardBalance(request.getCurrentValue());
            entity.setTransactionValue(transactionRequest.getTransactionAmount().toString());
            entity.setTransactionNumber(transactionNum);
            entity.setDate(LocalDate.now());
            entity.setTransactionStatus(transactionStatus);


            visaBalanceRepository.save(entity);
        }else if(isMasterCard(request.getCardNumber())) {
            MasterBalanceEntity entity = new MasterBalanceEntity();
            entity.setCardNumber(request.getCardNumber());
            entity.setCardBalance(request.getCurrentValue());
            entity.setTransactionValue(transactionRequest.getTransactionAmount().toString());
            entity.setTransactionNumber(transactionNum);
            entity.setDate(LocalDate.now());
            entity.setTransactionStatus(transactionStatus);


            masterBalanceRepository.save(entity);
        }

        return TransactionResponse.builder().transactionStatus(transactionStatus).transactionId(transactionNum).build();


    }
}
