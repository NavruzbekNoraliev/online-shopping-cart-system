package com.productvendor.service.Impl;

import com.productvendor.model.Category;
import com.productvendor.model.Product;
import com.productvendor.model.Vendor;
import com.productvendor.repository.CategoryRepo;
import com.productvendor.repository.ProductRepo;
import com.productvendor.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepo productRepository;
    private final CategoryRepo categoryRepo;

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
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
    public Page<Product> getProductsByCategory(long categoryId, int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
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
    public Page<Product> getProductsByNameAndCategory(String name, long categoryId,  int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndCategory(name, category, pageable);
    }

    @Override
    public Page<Product> getProductsByPriceAndCategory(long categoryId,  double min, double max, int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategoryAndPriceBetween(category, min, max, pageable);
    }

    @Override
    public Page<Product> getProductsByNameAndPriceAndCategory(String name, long categoryId,  double min, double max, int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndCategoryAndPriceBetween(name, category, min, max, pageable);
    }
    public Page<Product> getAllProductsSortedByPriceAsc(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("price").ascending());
        return productRepository.findAll(pageable);
    }

    public Page<Product> getAllProductsSortedByPriceDesc(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("price").descending());
        return productRepository.findAll(pageable);
    }

}