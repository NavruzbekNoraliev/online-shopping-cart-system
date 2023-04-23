package com.order.Controller;


import com.order.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    //get all categories
    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        if (categoryService.getAllCategories().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        if (categoryService.getCategoryById(id)==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
