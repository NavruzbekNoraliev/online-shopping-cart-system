//package com.adminmodule.controller;
//
//import com.adminmodule.domain.Employee;
//import com.adminmodule.service.EmployeeService;
//import com.adminmodule.service.dto.EmployeeDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(EmployeeController.class)
//public class EmployeeControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private EmployeeService employeeService;
//
//  private EmployeeDTO employeeDTO;
//  private Employee employee;
//
//  @BeforeEach
//  void setup(){
//      employee = Employee.builder()
//              .id(1L)
//              .firstName("John")
//              .lastName("Doe")
//              .email("jhon@gmail.com")
//              .phone("123456789")
//              .build();
//    employeeDTO = EmployeeDTO.builder()
//            .id(1L)
//            .firstName("John")
//            .lastName("Doe")
//            .email("jhon@gmail.com")
//            .phone("123456789")
//            .build();
//  }
//
//  @Test
//    void getAllEmployees() throws Exception {
//      when(employeeService.getAllEmployees()).thenReturn(List.of(employee));
//    mockMvc.perform(get("/api/v1/employee"))
//              .andExpect(status().isOk());
//  }
//    @Test
//        void getEmployeeById() throws Exception {
//        when(employeeService.getEmployeeById(2L)).thenReturn(employeeDTO);
//        mockMvc.perform(get("/api/v1/employee/2"))
//                .andExpect(status().isOk());
//    }
//
////employee add test
//    @Test
//    void addEmployee() throws Exception {
//      when(employeeService.addEmployee(employeeDTO)).thenReturn(employeeDTO);
//      mockMvc.perform(post("/api/v1/employee")
//                      .contentType("application/json")
//                      .content("{\n" +
//                              "    \"id\": 1,\n" +
//                              "    \"firstName\": \"John\",\n" +
//                              "    \"lastName\": \"Doe\",\n" +
//                              "    \"email\": \"jjj.com\",\n" +
//                              "    \"phone\": \"123456789\"\n" +
//                              "}"))
//              .andExpect(status().isOk());
//    }
//    //employee update test
//
//    @Test
//    void updateEmployee() throws Exception {
//      when(employeeService.updateEmployee(1L, employeeDTO)).thenReturn(employeeDTO);
//      mockMvc.perform(put("/api/v1/employee/1")
//                      .contentType("application/json")
//                      .content("{\n" +
//                              "    \"id\": 1,\n" +
//                              "    \"firstName\": \"John\",\n" +
//                              "    \"lastName\": \"Doe\",\n" +
//                              "    \"email\": \"jjj.com\",\n" +
//                              "    \"phone\": \"123456789\"\n" +
//                              "}"))
//              .andExpect(status().isOk());
//    }
//    @Test
//    void deleteEmployee() throws Exception {
//      mockMvc.perform(delete("/api/v1/employee/1"))
//              .andExpect(status().isOk());
//    }
//
//}
//
