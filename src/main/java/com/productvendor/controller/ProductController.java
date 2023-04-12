package com.productvendor.controller;

import com.productvendor.model.Category;
import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import com.productvendor.service.ProductService;
import com.productvendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private VendorService vendorService;

    //get all products
    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> products = productService.getAllProducts(pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/all-asc")
    public ResponseEntity<Page<Product>> getAllProductsPriceAsc(@RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> products = productService.getAllProductsSortedByPriceAsc(pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/all-desc")
    public ResponseEntity<Page<Product>> getAllProductsPriceDesc(@RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> products = productService.getAllProductsSortedByPriceDesc(pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }

    //get all products by vendor
    @GetMapping("/vendor/{id}")
    public List<Product> getProductsByVendor(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        return productService.getProductsByVendor(vendor);
    }

    //get product by id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    //add product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    //update product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    //delete product
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/category")
    public ResponseEntity<Page<Product>> getProductsByCategory(@RequestParam(defaultValue = "0") int pageNumber,
                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                               @RequestBody Long categoryId) {
        Page<Product> products = productService.getProductsByCategory(categoryId, pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }
//
//    @GetMapping("/filter6")
//    public ResponseEntity<Page<Product>> getProductsByName(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
//                                                           @RequestParam(name = "size", defaultValue = "10") int pageSize,
//                                                           @RequestParam(name = "name") String name) {
//        Page<Product> products = productService.getProductsByName(name, pageNumber, pageSize);
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/filter2")
//    public ResponseEntity<Page<Product>> getProductsByNameAndPrice(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
//                                                                   @RequestParam(name = "size", defaultValue = "10") int pageSize,
//                                                                   @RequestParam(name = "name") String name,
//                                                                   @RequestParam(name = "pmin") double min,
//                                                                   @RequestParam(name = "pmax") double max) {
//        Page<Product> products = productService.getProductsByPriceAndName(name, min, max, pageNumber, pageSize);
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/filter3")
//    public ResponseEntity<Page<Product>> getProductsByNameAndCategory(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
//                                                                      @RequestParam(name = "size", defaultValue = "10") int pageSize,
//                                                                      @RequestParam(name = "name") String name,
//                                                                      @RequestBody Category category) {
//        Page<Product> products = productService.getProductsByNameAndCategory(name, category, pageNumber, pageSize);
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/filter4")
//    public ResponseEntity<Page<Product>> getProductsByCategoryAndPrice(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
//                                                                       @RequestParam(name = "size", defaultValue = "10") int pageSize,
//                                                                       @RequestParam(name = "pmin") double min,
//                                                                       @RequestParam(name = "pmax") double max,
//                                                                       @RequestBody Category category) {
//        Page<Product> products = productService.getProductsByPriceAndCategory(category, min, max, pageNumber, pageSize);
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/filter5")
//    public ResponseEntity<Page<Product>> getProductsByNameAndCategoryAndPrice(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
//                                                                              @RequestParam(name = "size", defaultValue = "10") int pageSize,
//                                                                              @RequestParam(name = "pmin") double min,
//                                                                              @RequestParam(name = "pmax") double max,
//                                                                              @RequestParam(name = "name") String name,
//                                                                              @RequestBody Category category) {
//        Page<Product> products = productService.getProductsByNameAndPriceAndCategory(name, category, min, max, pageNumber, pageSize);
//        return ResponseEntity.ok(products);
//    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Product>> getFilteredProducts(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "pmin", required = false) Double minPrice,
            @RequestParam(name = "pmax", required = false) Double maxPrice,
            @RequestBody(required = false) Long categoryId) {

        if (name != null && minPrice == null && maxPrice == null && categoryId == null) {
            // Call getProductsByName
            Page<Product> products = productService.getProductsByName(name, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } else if (name != null && minPrice != null && maxPrice != null && categoryId == null) {
            // Call getProductsByPriceAndName
            Page<Product> products = productService.getProductsByPriceAndName(name, minPrice, maxPrice, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } else if (name != null && minPrice == null && maxPrice == null && categoryId != null) {
            // Call getProductsByNameAndCategory
            Page<Product> products = productService.getProductsByNameAndCategory(name, categoryId, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } else if (name == null && minPrice != null && maxPrice != null && categoryId != null) {
            // Call getProductsByPriceAndCategory
            Page<Product> products = productService.getProductsByPriceAndCategory(categoryId, minPrice, maxPrice, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } else if (name != null && minPrice != null && maxPrice != null && categoryId != null) {
            // Call getProductsByNameAndPriceAndCategory
            Page<Product> products = productService.getProductsByNameAndPriceAndCategory(name, categoryId, minPrice, maxPrice, pageNumber, pageSize);
            return ResponseEntity.ok(products);
        } else {
            // Invalid combination of parameters
            return ResponseEntity.badRequest().build();
        }
    }
}
