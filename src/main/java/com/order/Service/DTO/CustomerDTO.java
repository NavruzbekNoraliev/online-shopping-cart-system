package com.order.Service.DTO;

import com.order.Entity.OrderAddress;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String customerEmail;
    private String customerPhone;
    private OrderAddress shippingAddress;
    private OrderAddress billingAddress;


    public CustomerDTO(String firstName, String lastName, String customerEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerEmail = customerEmail;

    }
}
