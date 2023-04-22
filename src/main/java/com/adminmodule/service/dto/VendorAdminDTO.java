package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Role;
import com.adminmodule.domain.Vendor;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorAdminDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Vendor vendor;
    private Account account;

}
