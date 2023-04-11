package com.productvendor.service.Impl;

import com.productvendor.model.Category;
import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import com.productvendor.repository.ProductRepo;
import com.productvendor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByVendor(Vendor vendor) {
        return productRepository.findByVendor(vendor);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setVendor(product.getVendor());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProductsByCategory(Category category, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategory(category, pageable);
    }

    @Override
    public Page<Product> getProductsByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByName(name, pageable);
    }

    @Override
    public Page<Product> getProductsByPriceAndName(String name, double min, double max, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByPriceBetweenAndName(min, max, name, pageable);
    }

    @Override
    public Page<Product> getProductsByNameAndCategory(String name, Category category, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndCategory(name, category, pageable);
    }

    @Override
    public Page<Product> getProductsByPriceAndCategory(Category category, double min, double max, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategoryAndPriceBetween(category, min, max, pageable);
    }

    @Override
    public Page<Product> getProductsByNameAndPriceAndCategory(String name, Category category, double min, double max, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndCategoryAndPriceBetween(name, category, min, max, pageable);
    }


//    @Override
//    public List<Product> sortProductsByPriceAsc(List<Product> products) {
//        Comparator<Product> compAsc = (p, q)-> (int) (p.getPrice()- q.getPrice());
//        return products.stream().sorted(compAsc).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Product> sortProductsByPriceDesc(List<Product> products) {
//        Comparator<Product> compDesc = (p, q)-> (int) (q.getPrice() - p.getPrice());
//        return products.stream().sorted(compDesc).collect(Collectors.toList());
//    }
}