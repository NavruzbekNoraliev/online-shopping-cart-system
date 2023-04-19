package com.shopping.shoppingcartmodule.DTO;

import com.shopping.shoppingcartmodule.Entity.Category;
import jakarta.persistence.*;

public class ProductDTO {

        private Long id;
        private String name;
        private String description;
        private double price;
        private int quantity;
        private int vendorId;

        private Category category;

}

