package com.adminmodule.controller;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Customer;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.security.AccountDetailsService;
import com.adminmodule.security.AuthService;
import com.adminmodule.security.JWTUtility;
import com.adminmodule.service.CustomerService;
import com.adminmodule.service.dto.AddressDTO;
import com.adminmodule.service.dto.CustomerDTO;
import com.adminmodule.service.dto.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final AuthService authService;
    private final AccountDetailsService accountDetailsService;

    @Autowired
    public CustomerController(CustomerService customerService, AuthService authService, AccountDetailsService accountDetailsService) {
        this.customerService = customerService;
        this.accountDetailsService = accountDetailsService;
        this.authService = authService;
    }
    @GetMapping
    public ResponseEntity<?> getAllCustomers(){

        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id){
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    //insert into account table for testing purpose
    @PostMapping("/account")
    public ResponseEntity<?> addAccount(@RequestBody Account account){
        Account newAccount = accountDetailsService.addAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO){
       CustomerDTO newCustomer = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id,@RequestBody CustomerDTO customerDTO){
       CustomerDTO newCustomer =  customerService.updateCustomer(customerDTO, id);
        return new ResponseEntity<>(newCustomer ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/shipping-address/{id}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable("id") Long id,
                                                   @RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(customerService.updateShippingAddress(addressDTO, id));
    }

    @PutMapping("/billing-address/{id}")
    public ResponseEntity<?> updateBillingAddress(@PathVariable("id") Long id,
                                                   @RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(customerService.updateBillingAddress(addressDTO, id));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCustomer(@RequestBody Account account){

        CustomerDTO newCustomer = customerService.verifyCustomer(account);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    //authenticate and generate token
    @PostMapping("/authenticate")
    public ResponseEntity<Object> generateToken(@RequestBody Account account) throws Exception {
        return authService.generateToken(account);
    }
}
