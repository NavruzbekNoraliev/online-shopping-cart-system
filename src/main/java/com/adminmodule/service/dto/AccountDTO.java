package com.adminmodule.service.dto;

import com.adminmodule.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private String email;
    private Set<Role> role;
}
