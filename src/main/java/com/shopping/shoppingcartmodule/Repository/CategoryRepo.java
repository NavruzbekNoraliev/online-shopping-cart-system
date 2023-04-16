package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
