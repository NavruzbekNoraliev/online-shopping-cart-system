package com.adminmodule.controller;

import com.adminmodule.domain.Customer;
import com.adminmodule.security.JWTUtility;
import com.adminmodule.service.CustomerService;
import com.adminmodule.service.dto.AddressDTO;
import com.adminmodule.service.dto.CustomerDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id,
                                             @RequestHeader("Authorization") String authorizationHeader){
        try{
            String jwtToken = authorizationHeader.substring(7);
            String username = jwtUtility.getUsernameFromToken(jwtToken);
            CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
            CustomerDTO customer = customerService.getCustomerById(id);
            List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
            if (customer.getId()==customerDTO.getId()){
                return new ResponseEntity<>(customer, HttpStatus.OK);
            } else if (roles.contains("ROLE_ADMIN")) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
            else{
                throw new AuthenticationException("Unauthorized access") {
                };
            }
        }catch (AuthenticationException e){
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Unauthorized access");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
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
        if(roles.contains("ROLE_ADMIN") || customerDTO1.getId()==id){
            CustomerDTO newCustomer =  customerService.updateCustomer(customerDTO, id);
            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if(roles.contains("ROLE_ADMIN") || customerDTO.getId()==id){
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/shipping-address/{id}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable("id") Long id,
                                                   @RequestBody AddressDTO addressDTO,
                                                   @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if(roles.contains("ROLE_ADMIN") || customerDTO.getId()==id){
            return ResponseEntity.ok(customerService.updateShippingAddress(addressDTO, id));
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/billing-address/{id}")
    public ResponseEntity<?> updateBillingAddress(@PathVariable("id") Long id,
                                                   @RequestBody AddressDTO addressDTO,
                                                  @RequestHeader("Authorization") String authorizationHeader){
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        CustomerDTO customerDTO = customerService.getCustomerByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if(roles.contains("ROLE_ADMIN") || customerDTO.getId()==id){
            return ResponseEntity.ok(customerService.updateBillingAddress(addressDTO, id));
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
