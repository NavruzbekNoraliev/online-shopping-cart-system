package com.productvendor.repository;

import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    //findByVendor
    List<Product> findByVendor(Vendor vendor);
}
