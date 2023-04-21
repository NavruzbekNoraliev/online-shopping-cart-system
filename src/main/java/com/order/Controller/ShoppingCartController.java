package com.order.Controller;


import com.order.Entity.Cart;
import com.order.Entity.Product;
import com.order.Service.Impl.ShoppingCartService;
import com.order.Entity.ShoppingCart;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService=shoppingCartService;
    }

    //add product to cart
    @PostMapping("/{productId}")
    public Product addProductToCart(@PathVariable long productId){
        //add product to cart and return the added product
        //Todo: add product to cart
        return null;
    }
    //update product quantity in cart
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable long productId){
        //update product quantity in cart and return the updated product
        //Todo: update product quantity in cart
        return null;
    }
    //removes a cart item
    @DeleteMapping("/{productId}")
    public Product removeProduct(@PathVariable long productId){
        //remove product from cart and return the removed product
        //Todo: remove product from cart
        return null;
    }

    //updates the quantity of a cart item
    @PutMapping("/{productId}/{quantity}")
    public Product updateProductQuantity(@PathVariable long productId,@PathVariable int quantity){
        //update product quantity in cart and return the updated product
        //Todo: update product quantity in cart
        return null;
    }
    //get cart - returns the current state of the cart
    @GetMapping
    public Cart getCart(){
        //get cart and return the cart
        //Todo: get cart and return the cart
        return null;
    }
    //get cart by id - returns the current state of the cart
    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable long cartId){
        //get cart and return the cart
        //Todo: get cart and return the cart
        return null;
    }

    //checkout
    @PostMapping("/checkout")
    public Cart checkout(){
        //checkout and return the cart
        //Todo: checkout and return the cart
        return null;
    }


   //@Autowired

//    @PostMapping(value = "/add-cart/{cartid}/{productid}")
//    public ShoppingCart addProductToCart(@PathVariable long productid, @PathVariable long cartid){
//
//        return shoppingCartService.addToCart(productid,cartid);
//    }
//
//    @PostMapping(value = "/create-cart/{email}")
//    public ShoppingCart createCart(@PathVariable String email){
//       return shoppingCartService.createCart(email);
//   }
//
//  @DeleteMapping(value="/removeproduct/{cartid}/{productid}")
//    public ShoppingCart deleteProduct(@PathVariable long cartid,@PathVariable long productid){
//     return   shoppingCartService.deleteProduct(cartid,productid);
//  }
//  @GetMapping(value = "/cart/{id}")
//    public ShoppingCart Cart(@PathVariable long id){
//       return shoppingCartService.getCart(id);
//  }
//
////  @GetMapping(value="/checkout/{cartid}")
////    public void checkout(@PathVariable long cartid){
////       shoppingCartService.checkout(cartid);
////
////  }
//@DeleteMapping(value="/removeallproduct/{cartid}")
//public ShoppingCart deleteAllProduct(@PathVariable long cartid){
//    return   shoppingCartService.deleteAllProduct(cartid);
//}


}

