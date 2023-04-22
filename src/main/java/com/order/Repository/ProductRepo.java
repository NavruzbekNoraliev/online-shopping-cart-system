package com.order.Repository;


import com.order.Entity.Category;
import com.order.Service.Impl.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    //findByVendor
    Page<Product> findAllByVendorId(int vendorId, Pageable page);

    Page<Product> findByCategoryAndAvailable(Category category, boolean available, Pageable page);

    Page<Product> findByNameAndAvailable(String name,boolean available, Pageable page);

    Page<Product> findByPriceBetweenAndNameAndAvailable(double min, double max, String name, boolean available, Pageable page);

    Page<Product> findByNameAndCategoryAndAvailable(String name, Category category, boolean available, Pageable page);

    Page<Product> findByCategoryAndPriceBetweenAndAvailable(Category category, double min, double max, boolean available, Pageable page);

    Page<Product> findByNameAndCategoryAndPriceBetweenAndAvailable(String name, Category category, double min, double max, boolean available, Pageable page);

    Optional<Product> findById(Long id);

    Page<Product> findAllByAvailable(Pageable page, boolean available);

//    Page<Product> findAll(Sort sort, Pageable page);
}
