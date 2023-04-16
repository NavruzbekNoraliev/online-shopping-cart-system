package com.shopping.shoppingcartmodule.Service;


import com.shopping.shoppingcartmodule.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getAllProducts(int pageNumber, int pageSize);
    Page<Product> getAllProductsSortedByPriceAsc(int pageNumber, int pageSize);
    Page<Product> getAllProductsSortedByPriceDesc(int pageNumber, int pageSize);
    List<Product> getProductsByVendor(int vendorId);
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Page<Product> getProductsByCategory(long categoryId, int pageNumber, int pageSize);
    Page<Product> getProductsByName(String name, int pageNumber, int pageSize);
    Page<Product> getProductsByPriceAndName(String name, double min, double max, int pageNumber, int pageSize);
    Page<Product> getProductsByNameAndCategory(String name, long categoryId, int pageNumber, int pageSize);
    Page<Product> getProductsByPriceAndCategory(long categoryId, double min, double max, int pageNumber, int pageSize);
    Page<Product> getProductsByNameAndPriceAndCategory(String name, long categoryId, double min, double max, int pageNumber, int pageSize);

}
