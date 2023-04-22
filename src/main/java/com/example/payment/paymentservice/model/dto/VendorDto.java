package com.example.payment.paymentservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendorDto {
    private int id;
    private String name;
    private String phone;
    private String email;
}