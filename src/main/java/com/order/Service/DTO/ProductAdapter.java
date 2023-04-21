package com.order.Service.DTO;

import com.order.Entity.Product;

public class ProductAdapter {

    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setVendorId(product.getVendorId());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    }

    public static Product fromDTO(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setVendorId(productDTO.getVendorId());
        product.setCategory(productDTO.getCategory());
        return product;
    }
}
