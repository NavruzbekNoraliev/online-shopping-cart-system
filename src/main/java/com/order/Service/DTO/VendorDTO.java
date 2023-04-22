package com.order.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendorDTO {
    private int id;
    private String name;
    private String phone;
    private String email;
}
