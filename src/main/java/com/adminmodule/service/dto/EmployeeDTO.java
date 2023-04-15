package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Role;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Account account;

}
