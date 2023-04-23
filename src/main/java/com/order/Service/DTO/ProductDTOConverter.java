package com.order.Service.DTO;


import com.order.Entity.Category;
import com.order.Entity.Product;
import com.order.Repository.CategoryRepo;
import com.order.Service.Impl.GetVendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDTOConverter {
    private final CategoryRepo categoryRepo;
    private final GetVendorService getVendorService;

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        //Vendor should use rest template
        String vendorId = String.valueOf(product.getVendorId());
        VendorDTO vendorDTO = null;
        try {
            vendorDTO = getVendorService.getById(vendorId);
        } catch (Exception e) {
            vendorDTO = new VendorDTO();
            vendorDTO.setId(product.getId());
        }
        productDTO.setVendorDTO(vendorDTO);
        //
        productDTO.setColor(product.getColor());
        productDTO.setAvailable(product.isAvailable());
        productDTO.setImageUrl(product.getImageUrl());
        //Get Category
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }


    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setVendorId(productDTO.getVendorDTO().getId());
        product.setColor(productDTO.getColor());
        product.setAvailable(productDTO.isAvailable());
        product.setImageUrl(productDTO.getImageUrl());
        //Set category by id can check for exception
        Long categoryId = Long.valueOf(productDTO.getCategoryId());
        Category category = categoryRepo.findById(categoryId).get();
        product.setCategory(category);
        return product;
    }

}

