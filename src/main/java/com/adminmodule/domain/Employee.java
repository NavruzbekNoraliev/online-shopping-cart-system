package com.adminmodule.domain;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User{
    private String employeeType;
}
