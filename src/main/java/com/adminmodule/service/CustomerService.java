package com.adminmodule.service;



import com.adminmodule.domain.Account;
import com.adminmodule.domain.Customer;
import com.adminmodule.service.dto.AddressDTO;
import com.adminmodule.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO, Long id);
    void deleteCustomer(Long id);

    AddressDTO updateShippingAddress(AddressDTO addressDTO, Long id);

    AddressDTO updateBillingAddress(AddressDTO addressDTO, Long id);

    CustomerDTO verifyCustomer(Account account);

    CustomerDTO getCustomerByUsername(String email);
}
