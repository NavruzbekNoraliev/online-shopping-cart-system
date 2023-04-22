package com.order.Service.DTO;


import com.order.Entity.Category;
import com.order.Service.Impl.Product;
import com.order.Repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDTOConverter {
    private final CategoryRepo categoryRepo;

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        //Vendor should use resttemplate
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(product.getVendorId());
        productDTO.setVendorDTO(vendorDTO);
        //
        productDTO.setColor(product.getColor());
        productDTO.setAvailable(product.isAvailable());
        //Get Category
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }


    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setVendorId(productDTO.getVendorDTO().getId());
        product.setColor(productDTO.getColor());
        product.setAvailable(productDTO.isAvailable());
        //Set category by id can check for exception
        Long categoryId = Long.valueOf(productDTO.getCategoryId());
        Category category = categoryRepo.findById(categoryId).get();
        product.setCategory(category);
//        List<CustomerComment> commentList = productDTO.getComments().stream().map(commentText -> {
//            CustomerComment comment = new CustomerComment();
//            comment.setText(commentText);
//            comment.setProduct(product);
//            return comment;
//        }).collect(Collectors.toList());
//        product.setCommentList(commentList);
        return product;
    }

}

