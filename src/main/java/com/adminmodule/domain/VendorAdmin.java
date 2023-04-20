package com.adminmodule.domain;

import javax.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendorAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_admin_id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "vendorId")
    private Vendor vendor;

    @OneToOne
    private Account account;

}
