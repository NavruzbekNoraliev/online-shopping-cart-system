package com.shopping.shoppingcartmodule.Service.Impl;

import com.shopping.shoppingcartmodule.DTO.CustomerCommentDTO;
import com.shopping.shoppingcartmodule.Entity.CustomerComment;
import com.shopping.shoppingcartmodule.Repository.CustomerCommentRepo;
import com.shopping.shoppingcartmodule.Service.CustomerCommentService;
import com.shopping.shoppingcartmodule.Service.DTO.CustomerCommentConverter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CustomerCommentServiceImpl implements CustomerCommentService {
    private final CustomerCommentRepo commentRepo;
    private final CustomerCommentConverter commentConverter;


    @Override
    public Page<CustomerComment> getAllCommentsForProduct(long productId, int pageNumber, int pageSize) {
        return commentRepo.findCustomerCommentByProductId(productId, pageNumber, pageSize);
    }

    @Override
    public Page<CustomerComment> getAllCommentsForCustomer(long customerId, int pageNumber, int pageSize) {
        return commentRepo.findCustomerCommentByCustomerId(customerId, pageNumber, pageSize);
    }

    @Override
    public void deleteAllCommentForProduct(long productId) {
        commentRepo.deleteAllByProductId(productId);
    }

    @Override
    public void deleteAllCommentForCustomer(long customerId) {
        commentRepo.deleteAllByCustomerId(customerId);
    }

    @Override
    public void deleteComment(long commentId) {
        CustomerComment comment = commentRepo.findById(commentId)
                        .orElseThrow(() -> new EntityNotFoundException("Entity with ID " + commentId + " not found"));
        commentRepo.delete(comment);
    }

    @Override
    public CustomerCommentDTO addComment(CustomerCommentDTO comment) {
        if(comment == null || StringUtils.isEmpty(comment.getContent())) {
            throw new IllegalArgumentException("Invalid input");
        }
        CustomerComment newComment = commentConverter.toEntity(comment);
        commentRepo.save(newComment);
        return comment;
    }
}
