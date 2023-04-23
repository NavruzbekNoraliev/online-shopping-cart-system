package com.adminmodule.domain;


import javax.persistence.*;
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

    public AccountDetails(String accountNumber, String routingNumber, String bankName) {
    }


//    @OneToOne
//    @JoinColumn(name = "address_id")
//    private Address bankAddress;



}