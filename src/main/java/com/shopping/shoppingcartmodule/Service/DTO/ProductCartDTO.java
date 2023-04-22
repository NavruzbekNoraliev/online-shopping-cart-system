package com.shopping.shoppingcartmodule.Service.DTO;

import com.shopping.shoppingcartmodule.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDTO {


    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String color;
    private Category category;

}
