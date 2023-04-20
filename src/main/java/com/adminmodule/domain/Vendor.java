package com.adminmodule.domain;

import com.adminmodule.domain.Enum.Status;
import javax.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
    private Set<VendorAdmin> vendorAdmins = new LinkedHashSet<>();

    public void addVendorAdmin(VendorAdmin vendorAdmin) {
        vendorAdmin.setVendor(this);
        vendorAdmins.add(vendorAdmin);
    }

    public void removeVendorAdmin(VendorAdmin vendorAdmin) {
        vendorAdmins.remove(vendorAdmin);
    }

}
