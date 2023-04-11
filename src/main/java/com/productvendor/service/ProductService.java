package com.productvendor.service;


import com.productvendor.model.Product;
import com.productvendor.model.Vendor;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByVendor(Vendor vendor);
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
