package com.adminmodule.service.dto;

import com.adminmodule.domain.AccountDetails;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Enum.Status;
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
