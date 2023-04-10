package com.adminmodule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    private int customerId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    private String username;
    private String password;
    private Role role;



}
