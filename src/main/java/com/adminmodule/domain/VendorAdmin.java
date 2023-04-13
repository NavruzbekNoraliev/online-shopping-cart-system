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
public class VendorAdmin {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_admin_id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "vendorId")
    private Vendor vendor;

    @OneToOne
    private Account account;

}
