package com.adminmodule.service.dto;

import com.adminmodule.domain.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Customers {
    private List<CustomerDTO> customers = new ArrayList<>();

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

}
