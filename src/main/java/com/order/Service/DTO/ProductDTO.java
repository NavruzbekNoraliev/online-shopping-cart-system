package com.order.Service.DTO;

import com.order.Entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

        private Long id;
        private String name;
        private String description;
        private double price;
        private int quantity;
        private int vendorId;
        private Category category;

}

