package com.adminmodule.service.dto;

import com.adminmodule.domain.AccountDetails;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Enum.Status;
import com.adminmodule.domain.VendorAdmin;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public class VendorDTO {
    private int id;
    private String name;
    private String phone;
    private String email;
    private Address address;
    private Address billingAddress;
    private AccountDetails accountDetails;
}
