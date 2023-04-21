package com.shopping.shoppingcartmodule.Controller;


import com.shopping.shoppingcartmodule.Entity.Product;
import com.shopping.shoppingcartmodule.Entity.ShoppingCart;

import com.shopping.shoppingcartmodule.Service.Impl.ShoppingCartService;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {
   //@Autowired
   private ShoppingCartService shoppingCartService;
   public ShoppingCartController(ShoppingCartService shoppingCartService){
       this.shoppingCartService=shoppingCartService;
   }
    @PostMapping(value = "/add-cart/{cartid}/{productid}")
    public ShoppingCart addProductToCart(@PathVariable long productid, @PathVariable long cartid){

        return shoppingCartService.addToCart(productid,cartid);
    }

    @PostMapping(value = "/create-cart/{email}")
    public ShoppingCart createCart(@PathVariable String email){
       return shoppingCartService.createCart(email);
   }

  @DeleteMapping(value="/removeproduct/{cartid}/{productid}")
    public ShoppingCart deleteProduct(@PathVariable long cartid,@PathVariable long productid){
     return   shoppingCartService.deleteProduct(cartid,productid);
  }
  @GetMapping(value = "/cart/{id}")
    public ShoppingCart Cart(@PathVariable long id){
       return shoppingCartService.getCart(id);
  }

//  @GetMapping(value="/checkout/{cartid}")
//    public void checkout(@PathVariable long cartid){
//       shoppingCartService.checkout(cartid);
//
//  }
@DeleteMapping(value="/removeallproduct/{cartid}")
public ShoppingCart deleteAllProduct(@PathVariable long cartid){
    return   shoppingCartService.deleteAllProduct(cartid);
}
}
