package com.adminmodule.service.dto;

import com.adminmodule.domain.Employee;

public class EmployeeAdapter {

    public static Employee fromDTO(EmployeeDTO dto){
        return new Employee(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAccount());
    }

    public static EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getAccount());
    }

}
