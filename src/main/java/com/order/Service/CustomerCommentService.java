package com.order.Service;


import com.order.Entity.CustomerComment;
import com.order.Service.DTO.CustomerCommentDTO;
import org.springframework.data.domain.Page;

public interface CustomerCommentService {
    Page<CustomerComment> getAllCommentsForProduct(long productId, int pageNumber, int pageSize);
    Page<CustomerComment> getAllCommentsForCustomer(long customerId, int pageNumber, int pageSize);
    void deleteAllCommentForProduct(long productId);
    void deleteAllCommentForCustomer(long customerId);
    void deleteComment(long commentId);
    CustomerCommentDTO addComment(CustomerCommentDTO commentDTO);
}
