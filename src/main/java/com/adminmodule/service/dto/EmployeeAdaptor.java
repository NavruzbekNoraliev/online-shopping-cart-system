//package com.adminmodule.service.dto;
//
//import com.adminmodule.domain.Employee;
//
//public class EmployeeAdaptor {
//
//    public static Employee fromDTO(EmployeeDTO dto){
//        Employee employee = new Employee(dto.getId(),
//                dto.getFirstName(),
//                dto.getLastName(),
//                dto.getEmail(),
//                dto.getPhone(),
//                dto.getUsername(),
//                dto.getPassword(),
//                dto.getRole());
//
//        return employee;
//    }
//
//    public static EmployeeDTO toDTO(Employee employee){
//        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getPhone(),
//                employee.getUsername(),
//                employee.getPassword(),
//                employee.getRole());
//        return employeeDTO;
//    }
//
//}
