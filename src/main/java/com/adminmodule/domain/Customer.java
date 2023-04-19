package com.adminmodule.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Customer{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;


    @OneToOne
    private Address shippingAddress;
    @OneToOne
    private Address billingAddress;

    @OneToOne
    private Account account;

    public Customer(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
