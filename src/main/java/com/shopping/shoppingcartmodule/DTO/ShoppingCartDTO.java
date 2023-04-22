package com.shopping.shoppingcartmodule.DTO;

import com.shopping.shoppingcartmodule.Service.DTO.ProductCartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private long id;
    private String userId;
    private double totalPrice;
    private int quantity;
    private List<ProductCartDTO> products =new ArrayList<>();

    public ShoppingCartDTO(long id, String userId, double totalPrice, int quantity) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }
}
