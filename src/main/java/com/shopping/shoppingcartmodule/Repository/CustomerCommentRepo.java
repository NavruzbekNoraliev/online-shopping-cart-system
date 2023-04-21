package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.CustomerComment;
import com.shopping.shoppingcartmodule.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCommentRepo extends JpaRepository<CustomerComment, Long> {
    Page<CustomerComment> findCustomerCommentByProductId(Long id, Pageable page);
    Page<CustomerComment> findCustomerCommentByCustomerId(Long id, Pageable page);

    void deleteCustomerCommentsByCustomerIdEquals(Long id);
    void deleteCustomerCommentsByProduct(Product product);
}
