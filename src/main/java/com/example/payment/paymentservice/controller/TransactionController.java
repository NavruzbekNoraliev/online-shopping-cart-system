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
@RequestMapping(value = "api/v1/transactions")
public class TransactionController {


    @Autowired
    private final TransactionService transactionService;



    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity readTransactions() {
        return ResponseEntity.ok(transactionService.readTransactions());
    }
    @GetMapping("vendor/revenue")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Object getAllVendorRevenues() {
        return transactionService.readAllVendorsRevenues();
    }
    @GetMapping("vendor/revenue/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Object getVendorRevenuesById(@PathVariable Long id) {
        return transactionService.readVendorsRevenuesById(id);
    }

    @GetMapping("client/revenue")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Object getClientRevenues() {
        return transactionService.readClientRevenue();
    }


    @GetMapping("customer/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity readTransactionsByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.readTransactionsByCustomerId(id));
    }
    @GetMapping("vendor/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity readTransactionsByVendorId(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.readTransactionsByVendorId(id));
    }

    @PostMapping("customer")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity processTransaction(@RequestBody TransactionRequest transactionRequest) throws ChangeSetPersister.NotFoundException, IOException {
        return ResponseEntity.ok(transactionService.utilPayment(transactionRequest , false));
    }
    @PostMapping("vendor")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity processTransactionVendor(@RequestBody TransactionRequest transactionRequest) throws ChangeSetPersister.NotFoundException, IOException {
        return ResponseEntity.ok(transactionService.utilPayment(transactionRequest , true));
    }


//    @PostMapping("/kafka")
//    public ResponseEntity<TransactionDto> notify(@RequestBody TransactionDto transactionDto){
//        transactionService.broadcastPaymentComplete(transactionDto);
//        return ResponseEntity.ok(transactionDto);
//    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Object getTransactionById(@PathVariable Long id){
        try {
            TransactionDto transaction = transactionService.getTransactionById(id);
            return ResponseEntity.ok(transaction);
        } catch (ChangeSetPersister.NotFoundException er){
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }
    }

}