package com.shopping.shoppingcartmodule.Repository;

import com.shopping.shoppingcartmodule.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

}
