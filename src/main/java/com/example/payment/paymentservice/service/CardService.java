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
        CardEntity optCard = cardRepository.save(cardEntity);
        return ResponseEntity.ok("card has been added successfully, CARD ID : " + optCard.getCardId());
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
