package com.adminmodule.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNumber;
    private String routingNumber;
    private String bankName;

//    @OneToOne
//    @JoinColumn(name = "address_id")
//    private Address bankAddress;



}