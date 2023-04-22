package com.order.Service.DTO;


import com.order.Entity.CustomerComment;
import com.order.Service.Impl.Product;
import com.order.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCommentConverter {

    private final ProductService productService;

    public CustomerCommentDTO toDTO(CustomerComment customerComment) {
        CustomerCommentDTO customerCommentDTO = new CustomerCommentDTO();
        customerCommentDTO.setId(customerComment.getId());
        customerCommentDTO.setContent(customerComment.getContent());
        customerCommentDTO.setCustomerId(customerComment.getCustomerId());
        customerCommentDTO.setProductId(customerComment.getProduct().getId());
        return customerCommentDTO;
    }

    public CustomerComment toEntity(CustomerCommentDTO customerCommentDTO) {
        CustomerComment customerComment = new CustomerComment();
        customerComment.setId(customerCommentDTO.getId());
        customerComment.setContent(customerCommentDTO.getContent());
        customerComment.setCustomerId(customerCommentDTO.getCustomerId());
        Product product = productService.getProductById(customerCommentDTO.getProductId());
        customerComment.setProduct(product);
        return customerComment;
    }
}

