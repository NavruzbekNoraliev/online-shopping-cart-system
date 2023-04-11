package com.productvendor.controller;

import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import com.productvendor.service.ProductService;
import com.productvendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
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

}
