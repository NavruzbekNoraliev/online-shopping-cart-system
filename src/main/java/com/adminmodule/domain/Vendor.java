package com.adminmodule.domain;

import com.adminmodule.domain.Enum.Status;
import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    //add billing address
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address billingAddress;

    @OneToOne
    private AccountDetails accountDetails;

    private Status status;

//    @OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
//    private Set<VendorAdmin> vendorAdmins = new LinkedHashSet<>();

    public Vendor(String name, String email, String phone,
                  Address address, Address billingAddress,
                  AccountDetails accountDetails, Status status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.billingAddress = billingAddress;
        this.accountDetails = accountDetails;
        this.status = status;
    }

    //create a constructor without id, with name, email, phone
    public Vendor(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
