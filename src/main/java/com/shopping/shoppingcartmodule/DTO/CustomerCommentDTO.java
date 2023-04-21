package com.shopping.shoppingcartmodule.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCommentDTO {
    private Long id;
    private String content;
    private Long customerId;
    private Long productId;
}
