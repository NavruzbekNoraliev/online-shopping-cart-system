package com.order.Repository;

import com.order.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
