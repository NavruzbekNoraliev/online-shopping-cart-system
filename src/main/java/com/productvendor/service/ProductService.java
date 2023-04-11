package com.productvendor.service;


import com.productvendor.model.Category;
import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByVendor(Vendor vendor);
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Page<Product> getProductsByCategory(Category category, int pageNumber, int pageSize);
    Page<Product> getProductsByName(String name, int pageNumber, int pageSize);
    Page<Product> getProductsByPriceAndName(String name, double min, double max, int pageNumber, int pageSize);
    Page<Product> getProductsByNameAndCategory(String name, Category category, int pageNumber, int pageSize);
    Page<Product> getProductsByPriceAndCategory(Category category, double min, double max, int pageNumber, int pageSize);
    Page<Product> getProductsByNameAndPriceAndCategory(String name, Category category, double min, double max, int pageNumber, int pageSize);
    List<Product> sortProductsByPriceAsc(List<Product> products);
    List<Product> sortProductsByPriceDesc(List<Product> products);

}
