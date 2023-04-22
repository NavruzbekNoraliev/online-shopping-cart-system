package com.order.Service.Impl;


import com.order.Entity.CustomerComment;
import com.order.Repository.CustomerCommentRepo;
import com.order.Repository.ProductRepo;
import com.order.Service.CustomerCommentService;
import com.order.Service.DTO.CustomerCommentConverter;
import com.order.Service.DTO.CustomerCommentDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerCommentServiceImpl implements CustomerCommentService {
    private final CustomerCommentRepo commentRepo;
    private final CustomerCommentConverter commentConverter;
    private final ProductRepo productRepo;


    @Override
    public Page<CustomerComment> getAllCommentsForProduct(long productId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return commentRepo.findCustomerCommentByProductId(productId, pageable);
    }

    @Override
    public Page<CustomerComment> getAllCommentsForCustomer(long customerId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return commentRepo.findCustomerCommentByCustomerId(customerId, pageable);
    }

    @Override
    public void deleteAllCommentForProduct(long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        commentRepo.deleteCustomerCommentsByProduct(product);
    }

    @Override
    public void deleteAllCommentForCustomer(long customerId) {
        commentRepo.deleteCustomerCommentsByCustomerIdEquals(customerId);
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
