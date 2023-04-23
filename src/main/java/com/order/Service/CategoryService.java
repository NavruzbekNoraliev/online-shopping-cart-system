package com.order.Service;

import com.order.Entity.Category;

import java.util.List;

public interface CategoryService {

    //get all categories
    public List<Category> getAllCategories();

    //get category by id
    public Category getCategoryById(Long id);
}
