package com.example.payment.paymentservice.controller;

import com.example.payment.paymentservice.model.dto.TransactionDto;
import com.example.payment.paymentservice.rest.request.TransactionRequest;
import com.example.payment.paymentservice.service.CardService;
import com.example.payment.paymentservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {


    @Autowired
    private final TransactionService transactionService;



    @GetMapping
    public ResponseEntity readTransactions() {
        return ResponseEntity.ok(transactionService.readTransactions());
    }

    @GetMapping("customer/{id}")
    public ResponseEntity readTransactionsByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.readTransactionsByCustomerId(id));
    }
    @GetMapping("vendor/{id}")
    public ResponseEntity readTransactionsByVendorId(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.readTransactionsByVendorId(id));
    }

    @PostMapping("customer")
    public ResponseEntity processTransaction(@RequestBody TransactionRequest transactionRequest) throws ChangeSetPersister.NotFoundException, IOException {
        return ResponseEntity.ok(transactionService.utilPayment(transactionRequest , false));
    }
    @PostMapping("vendor")
    public ResponseEntity processTransactionVendor(@RequestBody TransactionRequest transactionRequest) throws ChangeSetPersister.NotFoundException, IOException {
        return ResponseEntity.ok(transactionService.utilPayment(transactionRequest , true));
    }


    @PostMapping("/kafka")
    public ResponseEntity<TransactionDto> notify(@RequestBody TransactionDto transactionDto){
        transactionService.broadcastPaymentComplete(transactionDto);
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping("/{id}")
    public Object getTransactionById(@PathVariable Long id){
        try {
            TransactionDto transaction = transactionService.getTransactionById(id);
            return ResponseEntity.ok(transaction);
        } catch (ChangeSetPersister.NotFoundException er){
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }
    }

}