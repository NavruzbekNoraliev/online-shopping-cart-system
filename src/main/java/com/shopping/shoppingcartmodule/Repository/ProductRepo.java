package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.Category;
import com.shopping.shoppingcartmodule.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    //findByVendor
    List<Product> findByVendorId(int vendorId);

    Page<Product> findByCategory(Category category, Pageable page);

    Page<Product> findByName(String name, Pageable page);

    Page<Product> findByPriceBetweenAndName(double min, double max, String name, Pageable page);

    Page<Product> findByNameAndCategory(String name, Category category, Pageable page);

    Page<Product> findByCategoryAndPriceBetween(Category category, double min, double max, Pageable page);

    Page<Product> findByNameAndCategoryAndPriceBetween(String name, Category category, double min, double max, Pageable page);

    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable page);

//    Page<Product> findAll(Sort sort, Pageable page);
}
