package com.order.Service;



import com.order.Entity.Product;
import com.order.Service.DTO.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getAllProducts(int pageNumber, int pageSize);
    Page<Product> getAllProductsSortedByPriceAsc(int pageNumber, int pageSize);
    Page<Product> getAllProductsSortedByPriceDesc(int pageNumber, int pageSize);
    Page<Product> getProductsByVendor(int vendorId, int pageNumber, int pageSize);
    Product getProductById(Long id);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    Product updateAvailableProduct(Long id, ProductDTO productDTO);
    Product updateProductImage(Long id, ProductDTO product);
    void deleteProduct(Long id);
    Page<Product> getProductsByCategory(long categoryId, int pageNumber, int pageSize);
    Page<Product> getProductsByName(String name, int pageNumber, int pageSize);
    Page<Product> getProductsByPriceAndName(String name, double min, double max, int pageNumber, int pageSize);
    Page<Product> getProductsByNameAndCategory(String name, long categoryId, int pageNumber, int pageSize);
    Page<Product> getProductsByPriceAndCategory(long categoryId, double min, double max, int pageNumber, int pageSize);
    Page<Product> getProductsByNameAndPriceAndCategory(String name, long categoryId, double min, double max, int pageNumber, int pageSize);

}
