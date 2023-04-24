package com.order.Service.Impl;


import com.order.Entity.Category;
import com.order.Entity.Product;
import com.order.Repository.CategoryRepo;
import com.order.Repository.ProductRepo;
import com.order.Service.DTO.ProductDTO;
import com.order.Service.DTO.ProductDTOConverter;
import com.order.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepo productRepository;
    private final CategoryRepo categoryRepo;
    private final ProductDTOConverter productDTOConverter;

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, boolean available) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAllByAvailable(pageable, available);
    }
    //make pageable
    @Override
    public Page<Product> getProductsByVendor(int vendorid,int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAllByVendorId(vendorid, pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        if(productDTO == null || StringUtils.isEmpty(productDTO.getName())) {
            throw new IllegalArgumentException("Invalid input");
        }
        Product product = productDTOConverter.toEntity(productDTO);
        productRepository.save(product);
        return productDTOConverter.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        existingProduct.setName(product.getName()!=null?product.getName(): existingProduct.getName());
        existingProduct.setDescription(product.getDescription()!=null?product.getDescription(): existingProduct.getDescription());
        existingProduct.setPrice(product.getPrice()!=0 ? product.getPrice(): existingProduct.getPrice());
        //always needed
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setImageUrl(product.getImageUrl());
        productRepository.save(existingProduct);
        return productDTOConverter.toDTO(existingProduct);
    }
    @Override
    public Product updateProductImage(Long id, ProductDTO product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        existingProduct.setImageUrl(product.getImageUrl());
        return productRepository.save(existingProduct);
    }
    @Override
    public Product updateAvailableProduct(Long id, ProductDTO product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        existingProduct.setAvailable(product.isAvailable());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getProductsByCategory(long categoryId, int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategoryAndAvailable(category, true, pageable);
    }

    @Override
    public Page<Product> getProductsByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndAvailable(name, true, pageable);
    }

    @Override
    public Page<Product> getProductsByPriceAndName(String name, double min, double max, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByPriceBetweenAndNameAndAvailable(min, max, name, true, pageable);
    }

    @Override
    public Page<Product> getProductsByNameAndCategory(String name, long categoryId,  int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndCategoryAndAvailable(name, category, true, pageable);
    }

    @Override
    public Page<Product> getProductsByPriceAndCategory(long categoryId,  double min, double max, int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByCategoryAndPriceBetweenAndAvailable(category, min, max, true, pageable);
    }

    @Override
    public Page<Product> getProductsByNameAndPriceAndCategory(String name, long categoryId,  double min, double max, int pageNumber, int pageSize) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findByNameAndCategoryAndPriceBetweenAndAvailable(name, category, min, max, true, pageable);
    }

    @Override
    public Optional<Product> findByCategoryName(String name) {
        return productRepository.findByCategoryName(name);
    }

    public Page<Product> getAllProductsSortedByPriceAsc(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("price").ascending());
        return productRepository.findAllByAvailable(pageable, true);
    }

    public Page<Product> getAllProductsSortedByPriceDesc(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("price").descending());
        return productRepository.findAllByAvailable(pageable, true);
    }



}