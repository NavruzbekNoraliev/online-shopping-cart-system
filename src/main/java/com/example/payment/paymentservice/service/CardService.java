package com.example.payment.paymentservice.service;


import com.example.payment.paymentservice.model.TransactionStatus;
import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.model.entity.CardEntity;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import com.example.payment.paymentservice.repository.CardRepository;
import com.example.payment.paymentservice.rest.request.TransactionRequest;
import com.example.payment.paymentservice.rest.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {


    private final CardRepository cardRepository;


    public List<CardEntity> readCards(Pageable pageable) {
        Page<CardEntity> allCards = cardRepository.findAll(pageable);
        return allCards.getContent();
    }


    public ResponseEntity addCard(CardEntity cardEntity) {

        if(cardEntity.getCardNumber() == null || cardEntity.getCardNumber().isEmpty()) {
            // return error response with message indicating card number is required
            return ResponseEntity.badRequest().body("Card number is required");
        }

        if(cardEntity.getCardNumber().length() != 16 ) {
            // return error response with message indicating card number is required
            return ResponseEntity.badRequest().body("Card number must be 16 digits");
        }

        if(cardEntity.getNameOnCard() == null || cardEntity.getNameOnCard().isEmpty()) {
            // return error response with message indicating name on card is required
            return ResponseEntity.badRequest().body("Name on card is required");
        }

        if(cardEntity.getCvv() == null || cardEntity.getCvv() < 100 || cardEntity.getCvv() > 999) {
            // return error response with message indicating cvv must be 3 digits
            return ResponseEntity.badRequest().body("CVV must be 3 digits");
        }

        if(cardEntity.getIssuedValue() == null || cardEntity.getIssuedValue().compareTo(BigDecimal.ZERO) <= 0) {
            // return error response with message indicating issued value must be greater than 0
            return ResponseEntity.badRequest().body("Issued value must be greater than 0");
        }

        if(cardEntity.getCurrentValue() == null || cardEntity.getCurrentValue().compareTo(BigDecimal.ZERO) < 0) {
            // return error response with message indicating current value must be greater than or equal to 0
            return ResponseEntity.badRequest().body("Current value must be greater than or equal to 0");
        }

        if(cardEntity.getOperationMode() == null || cardEntity.getOperationMode().isEmpty()) {
            // return error response with message indicating operation mode is required
            return ResponseEntity.badRequest().body("Operation mode is required");
        }

        if(cardEntity.getExpYear() == null || cardEntity.getExpYear().isEmpty()) {
            // return error response with message indicating expiration year is required
            return ResponseEntity.badRequest().body("Expiration year is required");
        }

        if(cardEntity.getExpYear().length() != 2) {
            // return error response with message indicating expiration year is required
            return ResponseEntity.badRequest().body("Expiration year Must be 3 digits only");
        }

        if(cardEntity.getExpMonth().length() != 2) {
            // return error response with message indicating expiration year is required
            return ResponseEntity.badRequest().body("Expiration Month Must be 3 digits only");
        }

        if(cardEntity.getExpMonth() == null || cardEntity.getExpMonth().isEmpty()) {
            // return error response with message indicating expiration month is required
            return ResponseEntity.badRequest().body("Expiration month is required");
        }

        CardEntity optCard = cardRepository.save(cardEntity);
        return ResponseEntity.ok("Card has been added successfully, CARD ID: " + optCard.getCardId());
//        CardEntity optCard = cardRepository.save(cardEntity);
//        return ResponseEntity.ok("card has been added successfully, CARD ID : " + optCard.getCardId());
    }


    public boolean deductFromCardTotalAmount(String cardNumber , BigDecimal deduct) {
        Optional<CardEntity> cardOptional = cardRepository.findByCardNumber(cardNumber);
        if (cardOptional.isPresent()) {
            CardEntity cardEntity = cardOptional.get();
            BigDecimal currentTotalAmount = cardEntity.getCurrentValue();
                cardEntity.setCurrentValue(currentTotalAmount.subtract(deduct));
                cardRepository.save(cardEntity);
                return true;
        }
        return false;
    }

    public ResponseEntity removeCard(Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.ok("card has been deleted successfully");
    }




    public CardEntity getCardById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<CardEntity> optionalCardEntity = cardRepository.findById(id);
        if (optionalCardEntity.isPresent()) {
            return optionalCardEntity.get();
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public CardEntity getCardByNumber(String number)  {
        Optional<CardEntity> optionalCardEntity = cardRepository.findByCardNumber(number);
        if (optionalCardEntity.isPresent()) {
            return optionalCardEntity.get();
        }else {
            return null;
        }
    }
}
