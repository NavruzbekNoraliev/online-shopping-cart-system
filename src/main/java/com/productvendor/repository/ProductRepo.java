package com.productvendor.repository;

import com.productvendor.model.Category;
import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    //findByVendor
    List<Product> findByVendor(Vendor vendor);

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
