package com.shopping.shoppingcartmodule.Service.Impl;

import com.shopping.shoppingcartmodule.Entity.Product;
import com.shopping.shoppingcartmodule.Entity.ShoppingCart;
import com.shopping.shoppingcartmodule.Repository.ProductRepo;
import com.shopping.shoppingcartmodule.Repository.ShoppingCartRepository;
import com.shopping.shoppingcartmodule.Service.ShoppingCartInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ShoppingCartService implements ShoppingCartInterface {
    //    @Autowired
//    private KafkaTemplate<String,ShoppingCart> kafkaTemplate;
    private ProductRepo productRepository;

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepo productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;

    }


    @Override
    public ShoppingCart addToCart(long productId, long cartId) {

        AtomicReference<ShoppingCart> shoppingCart = new AtomicReference<>();
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));
        double productTotalPrice = product.getPrice() * product.getQuantity();
        Optional<ShoppingCart> shoppingCartoptional = shoppingCartRepository.findById(cartId);
        shoppingCartoptional.ifPresent((cart) -> {
            cart.getProducts().add(product);
            cart.setTotalPrice(cart.getTotalPrice() + productTotalPrice);
            shoppingCart.set(shoppingCartRepository.save(cart));
        });

        return shoppingCart.get();
    }

    // @KafkaListener(topics = "topicName", groupId = "foo")
    @Override
    public ShoppingCart createCart(String email) {
        Optional<ShoppingCart> optionalshoppingcart = shoppingCartRepository.findByEmailidIgnoreCase(email);
        if (optionalshoppingcart.isPresent()) return optionalshoppingcart.get();
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
            double productprice = product1.getPrice() * product1.getQuantity();
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - productprice);
            shoppingCart.getProducts().remove(product1);
//            productRepository.delete(product1);

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
    @Override
    public ShoppingCart deleteAllProduct(long cartid) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartid).orElse(null);
        shoppingCart.setProducts(null);
        shoppingCart.setTotalPrice(0.00);
        return shoppingCartRepository.save(shoppingCart);
    }
}
