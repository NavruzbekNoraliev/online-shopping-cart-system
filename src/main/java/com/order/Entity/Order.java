package com.order.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Date orderDate;
    private double totalPrice;
    @OneToMany(cascade= CascadeType.ALL)
    private List<OrderItem> orderItems;
    private OrderStatus status;
    @OneToOne(cascade= CascadeType.ALL)
    private OrderAddress shippingAddress;
    @OneToOne(cascade= CascadeType.ALL)
    private OrderAddress billingAddress;
    private PaymentMethod paymentMethod;

}
