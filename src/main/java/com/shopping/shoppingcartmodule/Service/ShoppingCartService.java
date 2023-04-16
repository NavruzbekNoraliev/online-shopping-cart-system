package com.shopping.shoppingcartmodule.Service;

import com.shopping.shoppingcartmodule.Entity.Product;
import com.shopping.shoppingcartmodule.Entity.ShoppingCart;
import com.shopping.shoppingcartmodule.Repository.ProductRepo;
import com.shopping.shoppingcartmodule.Repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ShoppingCartService implements ShoppingCartInterface {
//    @Autowired
//    private KafkaTemplate<String,ShoppingCart> kafkaTemplate;
     private ProductRepo productRepository;

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,ProductRepo productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository=productRepository;

    }


    @Override
    public ShoppingCart addToCart(Product product, long cartid) {
//        double totalPrice = shoppingCart.getProducts().stream()
//                .map(x -> x.getQuantity() * x.getPrice())
//                .reduce(0.0, (a, b) -> a + b);
        //shoppingCart.setTotalPrice(totalPrice);
        //return shoppingCartRepository.save(shoppingCart);
        AtomicReference<ShoppingCart> shoppingCart= new AtomicReference<>();
        double totalprice=product.getPrice()*product.getQuantity();
      Optional<ShoppingCart> shoppingCartoptional=shoppingCartRepository.findById(cartid);
         shoppingCartoptional.ifPresent((x)->{
             x.getProducts().add(product);
             x.setTotalPrice(x.getTotalPrice()+totalprice);
            shoppingCart.set(shoppingCartRepository.save(x));
                                   });

         return shoppingCart.get();
    }
   // @KafkaListener(topics = "topicName", groupId = "foo")
    @Override
    public ShoppingCart createCart(String email) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setEmailid(email);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteProduct(long cartid, long productid) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartid).orElse(null);
        Optional<Product> product = shoppingCart.getProducts()
                .stream()
                .filter(x -> x.getId() == productid)
                .findFirst();
        product.ifPresent(product1 -> {
            double productprice=product1.getPrice()*product1.getQuantity();
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()-productprice);
            shoppingCart.getProducts().remove(product1);
            productRepository.delete(product1);

        });
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getCart(long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

//    public void checkout(long cartid) {
//      ShoppingCart shoppingCart=shoppingCartRepository.findById(cartid).orElse(null);
//        kafkaTemplate.send("checkoutcart",shoppingCart);
//
//    }
}
