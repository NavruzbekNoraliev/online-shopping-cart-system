package com.shopping.shoppingcartmodule.DTO;

import com.shopping.shoppingcartmodule.Entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
        private Long id;
        private String name;
        private String description;
        private double price;
        private int quantity;
        private VendorDTO vendorDTO;
        private String color;
        private boolean available;
        private int categoryId;

}
