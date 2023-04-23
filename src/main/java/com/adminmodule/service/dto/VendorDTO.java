package com.adminmodule.service.dto;

import com.adminmodule.domain.AccountDetails;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Enum.VendorStatus;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public class VendorDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private Address billingAddress;
    private AccountDetails accountDetails;
    private VendorStatus status;
    
    public VendorDTO(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
