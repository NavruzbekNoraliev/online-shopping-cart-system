package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
