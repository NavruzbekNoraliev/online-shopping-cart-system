package com.adminmodule.service.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public class VendorDTO1 {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
