package com.adminmodule.service.dto;

import com.adminmodule.domain.Customer;

public class CustomerAdapter {

    public static Customer fromDTO(CustomerDTO dto){

        return new Customer(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAccount().getEmail(),
                dto.getPhone(),
                dto.getBillingAddress(),
                dto.getShippingAddress(),
                dto.getAccount());
    }

    public static CustomerDTO toDTO(Customer customer){
        return new CustomerDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getBillingAddress(),
                customer.getShippingAddress(),
                customer.getAccount());

    }

    public static CustomerDTO1 toDTO1(Customer customer){
        return new CustomerDTO1(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getBillingAddress(),
                customer.getShippingAddress(),
                AccountAdapter.toDTO(customer.getAccount()));

    }

}
