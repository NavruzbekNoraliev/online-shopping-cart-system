package com.shopping.shoppingcartmodule.Service;

import com.shopping.shoppingcartmodule.DTO.CustomerCommentDTO;
import com.shopping.shoppingcartmodule.Entity.CustomerComment;
import org.springframework.data.domain.Page;

public interface CustomerCommentService {
    Page<CustomerComment> getAllCommentsForProduct( long productId, int pageNumber, int pageSize);
    Page<CustomerComment> getAllCommentsForCustomer(long customerId, int pageNumber, int pageSize);
    void deleteAllCommentForProduct(long productId);
    void deleteAllCommentForCustomer(long customerId);
    void deleteComment(long commentId);
    CustomerCommentDTO addComment(CustomerCommentDTO commentDTO);
}
