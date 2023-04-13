package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Role;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private Address shippingAddress;
    private Address billingAddress;

    private Account account;

    public CustomerDTO(Long id,
                       String firstName,
                       String lastName,
                       String email,
                       String phone,


                       Address shippingAddress,
                       Address billingAddress,
                       Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;

        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.account = account;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +

                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", account=" + account +
                '}';
    }
}
