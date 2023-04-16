package com.shopping.shoppingcartmodule.Service.Impl;

import com.shopping.shoppingcartmodule.Entity.Category;
import com.shopping.shoppingcartmodule.Entity.Product;
import com.shopping.shoppingcartmodule.Repository.CategoryRepo;
import com.shopping.shoppingcartmodule.Repository.ProductRepo;
import com.shopping.shoppingcartmodule.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Product> getProductsByVendor(int vendorid) {
        return productRepository.findByVendorId(vendorid);
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
        existingProduct.setVendorId(product.getVendorId());
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