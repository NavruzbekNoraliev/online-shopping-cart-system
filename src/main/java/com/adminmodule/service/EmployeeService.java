package com.adminmodule.service;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Employee;
import com.adminmodule.service.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);


    EmployeeDTO verifyEmployee(Account account);

    EmployeeDTO getEmployeeByEmail(String email);
}
