package com.productvendor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @OneToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

}
