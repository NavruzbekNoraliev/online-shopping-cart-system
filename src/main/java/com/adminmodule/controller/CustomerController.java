package com.adminmodule.controller;

import com.adminmodule.domain.Customer;
import com.adminmodule.security.AccountDetailsService;
import com.adminmodule.security.JWTUtility;
import com.adminmodule.service.CustomerService;
import com.adminmodule.service.dto.AddressDTO;
import com.adminmodule.service.dto.CustomerDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final JWTUtility jwtUtility;

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, JWTUtility jwtUtility) {
        this.customerService = customerService;
        this.jwtUtility = jwtUtility;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        CustomerDTO customer = customerService.getCustomerById(id);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (customer.getId()==customerDTO.getId() || roles.contains("ROLE_ADMIN")){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping()
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO){
       CustomerDTO newCustomer = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id,@RequestBody CustomerDTO customerDTO, @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO1 = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (customerDTO1.getId()!=id || !roles.contains("ROLE_ADMIN")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        CustomerDTO newCustomer =  customerService.updateCustomer(customerDTO, id);
        return new ResponseEntity<>(newCustomer ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (customerDTO.getId()!=id || !roles.contains("ROLE_ADMIN")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/shipping-address/{id}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable("id") Long id,
                                                   @RequestBody AddressDTO addressDTO,
                                                   @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (customerDTO.getId()!=id || !roles.contains("ROLE_ADMIN")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(customerService.updateShippingAddress(addressDTO, id));
    }

    @PutMapping("/billing-address/{id}")
    public ResponseEntity<?> updateBillingAddress(@PathVariable("id") Long id,
                                                   @RequestBody AddressDTO addressDTO,
                                                  @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (customerDTO.getId()!=id || !roles.contains("ROLE_ADMIN")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(customerService.updateBillingAddress(addressDTO, id));
    }
}
