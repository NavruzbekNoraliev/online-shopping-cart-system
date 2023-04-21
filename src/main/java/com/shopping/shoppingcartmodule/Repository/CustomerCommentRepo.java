package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.CustomerComment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCommentRepo extends JpaRepository<CustomerComment, Long> {
    Page<CustomerComment> findCustomerCommentByProductId(Long id, int pageNumber, int pageSize);
    Page<CustomerComment> findCustomerCommentByCustomerId(Long id, int pageNumber, int pageSize);
    void deleteAllByCustomerId(Long id);
    void deleteAllByProductId(Long id);
}
