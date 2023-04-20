package com.adminmodule.controller;

import com.adminmodule.domain.Customer;
import com.adminmodule.service.CustomerService;
import com.adminmodule.service.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerDTO customerDTO ;
    private Customer customer;

    @BeforeEach
    void setup(){
        customer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("jhon@gmail.com")
                .phone("123456789")
                .build();
        customerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("jhon@gmail.com")
                .phone("123456789")
                .build();
    }
    @Test
    void getAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of(customer));
        mockMvc.perform(get("/api/v1/customer"))
                .andExpect(status().isOk());
    }
    @Test
    void getCustomerById() throws Exception {
        when(customerService.getCustomerById(2L)).thenReturn(customerDTO);
        mockMvc.perform(get("/api/v1/customer/2"))
                .andExpect(status().isOk());
    }
    @Test
    void addCustomer() throws Exception {
        when(customerService.addCustomer(customerDTO)).thenReturn(customerDTO);
        mockMvc.perform(post("/api/v1/customer")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"id\": 1,\n" +
                                "    \"firstName\": \"John\",\n" +
                                "    \"lastName\": \"Doe\",\n" +
                                "    \"email\": \"jjj.com\",\n" +
                                "    \"phone\": \"123456789\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }
    @Test
    void updateCustomer() throws Exception {
        when(customerService.updateCustomer(customerDTO, 1L)).thenReturn(customerDTO);
        mockMvc.perform(put("/api/v1/customer/1")
                        .contentType("application/json")
                        .content("{\n" +
                                "    \"id\": 1,\n" +
                                "    \"firstName\": \"John\",\n" +
                                "    \"lastName\": \"Doe\",\n" +
                                "    \"email\": \"jjj.com\",\n" +
                                "    \"phone\": \"123456789\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }
    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customer/2"))
                .andExpect(status().isNoContent());
    }


}
