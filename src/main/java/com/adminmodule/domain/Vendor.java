package com.adminmodule.domain;

import com.adminmodule.domain.Enum.VendorStatus;
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

    private VendorStatus vendorStatus;


//    @OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER)
//    private Set<VendorAdmin> vendorAdmins = new LinkedHashSet<>();



    public Vendor(String name, String phone, String email, VendorStatus vendorStatus) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.vendorStatus = vendorStatus;
    }
}
