package com.shopping.shoppingcartmodule.Service.DTO;

import com.shopping.shoppingcartmodule.DTO.ProductDTO;
import com.shopping.shoppingcartmodule.DTO.VendorDTO;
import com.shopping.shoppingcartmodule.Entity.Category;
import com.shopping.shoppingcartmodule.Entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDTOConverter {

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        //Vendor should use resttemplate
        productDTO.setVendorDTO(new VendorDTO());
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
        //Set category by id
        Category category = new Category();
        product.setCategory(category);
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

