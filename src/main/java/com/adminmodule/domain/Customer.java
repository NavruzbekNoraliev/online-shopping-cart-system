package com.adminmodule.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer extends User{

    @OneToOne
    private Address shippingAddress;
    @OneToOne
    private Address billingAddress;

}
