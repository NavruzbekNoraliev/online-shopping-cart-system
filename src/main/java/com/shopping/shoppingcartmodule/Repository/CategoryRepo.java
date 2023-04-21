package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
