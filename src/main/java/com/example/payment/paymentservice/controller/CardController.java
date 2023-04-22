package com.example.payment.paymentservice.controller;

import com.example.payment.paymentservice.model.TransactionStatus;
import com.example.payment.paymentservice.model.entity.CardEntity;
import com.example.payment.paymentservice.model.entity.TransactionEntity;
import com.example.payment.paymentservice.rest.response.BankResponse;
import com.example.payment.paymentservice.rest.response.TransactionResponse;
import com.example.payment.paymentservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cards")
public class CardController {


    @Autowired
    private final CardService cardService;

    @GetMapping
    public ResponseEntity readCards(Pageable pageable) {
        return ResponseEntity.ok(cardService.readCards(pageable));
    }

    @PostMapping
    public ResponseEntity addCard(@RequestBody CardEntity cardEntity) {
        return ResponseEntity.ok(cardService.addCard(cardEntity));
    }

    @GetMapping("/{number}")
    public Object getCardDetailsById(@PathVariable String number){
        try {
            CardEntity cardEntity = cardService.getCardByNumber(number);

            BankResponse response = new BankResponse();

            response.setCardNumber(cardEntity.getCardNumber());
            response.setId(cardEntity.getId());
            response.setCvv(cardEntity.getCvv());
            response.setNameOnCard(cardEntity.getNameOnCard());
            response.setOperationMode(cardEntity.getOperationMode());
            response.setCurrentValue(cardEntity.getCurrentValue());
            response.setIssuedValue(cardEntity.getIssuedValue());


            return response;

        }
        catch (NullPointerException error){
            BankResponse response = new BankResponse();
            response.setCardNumber("0");
            return response;

            //return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }


    }

}