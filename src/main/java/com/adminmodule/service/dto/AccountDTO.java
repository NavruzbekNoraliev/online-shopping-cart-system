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
    private String username;
    private Set<Role> role;
}
