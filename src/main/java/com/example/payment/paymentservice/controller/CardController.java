package com.example.payment.paymentservice.controller;

import com.example.payment.paymentservice.model.entity.CardEntity;
import com.example.payment.paymentservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Object getCardId(@PathVariable Long id){
        try {
            CardEntity cardEntity = cardService.getCardById(id);
            return ResponseEntity.ok(cardEntity);
        } catch (ChangeSetPersister.NotFoundException er){
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }

    }

}