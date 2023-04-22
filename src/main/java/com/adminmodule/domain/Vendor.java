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

//    @OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
//    private Set<VendorAdmin> vendorAdmins = new LinkedHashSet<>();

    public Vendor(Long id, String name, String email, String phone,
                  Address address, Address billingAddress,
                  AccountDetails accountDetails) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.billingAddress = billingAddress;
        this.accountDetails = accountDetails;
    }
}
