package com.adminmodule.service.dto;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Account account;

}
