package com.order.Controller;


import com.order.Entity.Product;
import com.order.ExeceptionHandling.product.ResourceNotFoundException;
import com.order.Service.DTO.ProductDTO;
import com.order.Service.DTO.ProductDTOConverter;
import com.order.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductDTOConverter productDTOConverter;

    //get all products
    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                                           @RequestParam(defaultValue = "10") int pageSize,
                                                           @RequestParam(defaultValue = "true") boolean available) {
        try {
            Page<Product> productPage = productService.getAllProducts(pageNumber, pageSize, available);
            Page<ProductDTO> productDtoPage = productPage.map(product -> productDTOConverter.toDTO(product));
            return ResponseEntity.ok(productDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all-asc")
    public ResponseEntity<Page<ProductDTO>> getAllProductsPriceAsc(@RequestParam(defaultValue = "0") int pageNumber,
                                                                   @RequestParam(defaultValue = "10") int pageSize) {
        try {
            Page<Product> productPage = productService.getAllProductsSortedByPriceAsc(pageNumber, pageSize);
            Page<ProductDTO> productDtoPage = productPage.map(product -> productDTOConverter.toDTO(product));
            return ResponseEntity.ok(productDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all-desc")
    public ResponseEntity<Page<ProductDTO>> getAllProductsPriceDesc(@RequestParam(defaultValue = "0") int pageNumber,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        try {
            Page<Product> productPage = productService.getAllProductsSortedByPriceDesc(pageNumber, pageSize);
            Page<ProductDTO> productDtoPage = productPage.map(product -> productDTOConverter.toDTO(product));
            return ResponseEntity.ok(productDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //get all products by vendor
    @GetMapping("/vendor/{id}")
    public ResponseEntity<Page<ProductDTO>> getProductsByVendor(@RequestParam(defaultValue = "0") int pageNumber,
                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                @PathVariable int id) {
        try {
            Page<Product> productPage = productService.getProductsByVendor(id, pageNumber, pageSize);
            Page<ProductDTO> productDtoPage = productPage.map(product -> productDTOConverter.toDTO(product));
            return ResponseEntity.ok(productDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            ProductDTO productDTO = productDTOConverter.toDTO(product);
            return ResponseEntity.ok(productDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //add product
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO addProductDTO) {
        try {
            ProductDTO productDTO = productService.addProduct(addProductDTO);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update product
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO productDTO1 = productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(productDTO1);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //update available
    @PutMapping("/updateAvailable/{id}")
    public ResponseEntity<Product> updateAvailableProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.updateAvailableProduct(id, productDTO);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //delete product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(@RequestParam(defaultValue = "0") int pageNumber,
                                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                                  @PathVariable Long id) {
        try {
            Page<Product> productPage = productService.getProductsByCategory(id.longValue(), pageNumber, pageSize);
            Page<ProductDTO> productDtoPage = productPage.map(product -> productDTOConverter.toDTO(product));
            return ResponseEntity.ok(productDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get product by category name
    @GetMapping("/category/name/{name}")
    public ResponseEntity<?> getProductByCategoryName(@PathVariable String name) {
        try{
            Optional<Product> product = productService.findByCategoryName(name);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDTO>> getFilteredProducts(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "pmin", required = false) Double minPrice,
            @RequestParam(name = "pmax", required = false) Double maxPrice,
            @RequestParam(name = "category", required = false) Long categoryId) {

        Page<Product> products;

        if (name != null && minPrice == null && maxPrice == null && categoryId == null) {
            // Call getProductsByName
            products = productService.getProductsByName(name, pageNumber, pageSize);
        } else if (name != null && minPrice != null && maxPrice != null && categoryId == null) {
            // Call getProductsByPriceAndName
            products = productService.getProductsByPriceAndName(name, minPrice, maxPrice, pageNumber, pageSize);
        } else if (name != null && minPrice == null && maxPrice == null && categoryId != null) {
            // Call getProductsByNameAndCategory
            products = productService.getProductsByNameAndCategory(name, categoryId.longValue(), pageNumber, pageSize);
        } else if (name == null && minPrice != null && maxPrice != null && categoryId != null) {
            // Call getProductsByPriceAndCategory
            products = productService.getProductsByPriceAndCategory(categoryId.longValue(), minPrice, maxPrice, pageNumber, pageSize);
        } else if (name != null && minPrice != null && maxPrice != null && categoryId != null) {
            // Call getProductsByNameAndPriceAndCategory
            products = productService.getProductsByNameAndPriceAndCategory(name, categoryId.longValue(), minPrice, maxPrice, pageNumber, pageSize);
        } else {
            // Invalid combination of parameters
            return ResponseEntity.badRequest().build();
        }
        try {
            Page<ProductDTO> productDtoPage = products.map(product -> productDTOConverter.toDTO(product));
            return ResponseEntity.ok(productDtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
