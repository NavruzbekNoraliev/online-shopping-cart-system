package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Role;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
@Builder
@Setter
@Getter
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Address shippingAddress;
    private Address billingAddress;

    private Account account;

}
