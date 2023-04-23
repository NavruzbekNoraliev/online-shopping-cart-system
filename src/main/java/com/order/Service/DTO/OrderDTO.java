package com.order.Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private double transactionAmount;
    private String customerEmail;
    private Long customerId;
    private String customerName;
    private List<OrderItemDto> orderItem;
    private Long productId;
    private Date date;
}
