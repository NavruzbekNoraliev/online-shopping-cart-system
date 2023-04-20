package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class CustomerDTO1 {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private Address shippingAddress;
    private Address billingAddress;

    private AccountDTO accountDTO;

}
