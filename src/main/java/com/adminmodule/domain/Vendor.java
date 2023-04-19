package com.adminmodule.domain;

import com.adminmodule.domain.Enum.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    //add billing address
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

    @OneToOne
    @JoinColumn(name = "account_detail_id")
    private AccountDetails accountDetails;

    private Status status;

}
