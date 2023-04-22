package com.shopping.shoppingcartmodule.Service.Impl;

import com.shopping.shoppingcartmodule.DTO.ShoppingCartDTO;
import com.shopping.shoppingcartmodule.Entity.Product;
import com.shopping.shoppingcartmodule.Entity.ShoppingCart;
import com.shopping.shoppingcartmodule.ExeceptionHandling.InvalidQuantityException;
import com.shopping.shoppingcartmodule.ExeceptionHandling.ProductNotFoundException;
import com.shopping.shoppingcartmodule.ExeceptionHandling.ShoppingCartNotFoundException;
import com.shopping.shoppingcartmodule.Repository.ProductRepo;
import com.shopping.shoppingcartmodule.Repository.ShoppingCartRepository;
import com.shopping.shoppingcartmodule.Service.DTO.ProductCartDTO;
import com.shopping.shoppingcartmodule.Service.ShoppingCartInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShoppingCartService implements ShoppingCartInterface {

    private ProductRepo productRepository;

    private ShoppingCartRepository shoppingCartRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepo productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;

    }


    @Override
    public ShoppingCartDTO addToCart(long cartId, long productId, int quantity) {
        log.info("entering... To addToCart Method ");
        AtomicReference<ShoppingCart> shoppingCart = new AtomicReference<>();
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> {
            log.error("product not found for productid: {}", productId);
            return new ProductNotFoundException("Product not found" + productId);
        });
        if(!product.isAvailable()) throw new InvalidQuantityException("PRODUCT QUANTITY IS NOT AVAILABLE");
       else if (quantity > product.getQuantity()) throw new InvalidQuantityException("WE DO NOT HAVE THAT MUCH QUANTITY OF PRODUCT");
        double productTotalPrice = product.getPrice() * quantity;


        Optional<ShoppingCart> shoppingCartoptional = Optional.ofNullable(shoppingCartRepository.findById(cartId).orElseThrow(
                () -> new ShoppingCartNotFoundException("CART NOT FOUND" + cartId)
        ));
        shoppingCartoptional.ifPresent((cart) -> {
            cart.setTotalPrice(cart.getTotalPrice() + productTotalPrice);
            Integer existedProductQuantity = cart.getBuketProduct().get(productId);
            int i = Objects.nonNull(existedProductQuantity) ? existedProductQuantity : 0;
            cart.getBuketProduct().put(productId, i + quantity);
            Integer cartQuantity = cart.getBuketProduct().values().stream().reduce(0, Integer::sum);
            cart.setQuantity(cartQuantity);
            shoppingCart.set(shoppingCartRepository.save(cart));
        });
        int databaseQuantity = product.getQuantity() - quantity;
        if(databaseQuantity==0) product.setAvailable(false);
        product.setQuantity(databaseQuantity);
        productRepository.save(product);
        return entityToDto(shoppingCart.get());
    }

    private ShoppingCartDTO entityToDto(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO(shoppingCart.getId(), shoppingCart.getUserId(),
                shoppingCart.getTotalPrice(), shoppingCart.getQuantity());
        List<Long> productsId = new ArrayList<>(shoppingCart.getBuketProduct().keySet());
        List<ProductCartDTO> products = productRepository.findAllById(productsId).stream().map(product -> {
            product.setQuantity(shoppingCart.getBuketProduct().get(product.getId()));
            ProductCartDTO productCartDTO=new ProductCartDTO(product.getId(),product.getName(), product.getDescription(),
                    product.getPrice(),product.getQuantity(),product.getColor(),product.getCategory());
            return productCartDTO;
        }).collect(Collectors.toList());

        shoppingCartDTO.getProducts().addAll(products);
        return shoppingCartDTO;
    }

    @Override
    public ShoppingCartDTO createCart(String userid) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findByUserIdIgnoreCase(userid);
        if (optionalShoppingCart.isPresent()) return entityToDto(optionalShoppingCart.get());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userid);
        return entityToDto(shoppingCartRepository.save(shoppingCart));
    }


    @Override
    public ShoppingCartDTO deleteProductFromCart(long cartid,long productid) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartid).orElseThrow(() ->
                new ShoppingCartNotFoundException("CART NOT FOUND WITH THIS ID:" + cartid));
        if(!shoppingCart.getBuketProduct().containsKey(productid)) throw new  ProductNotFoundException("YOU DO NOT HAVE THAT KIND OF PRODUCT");
        int producatRemovedQuantity = shoppingCart.getBuketProduct().get(productid);
        shoppingCart.setQuantity(shoppingCart.getQuantity() - producatRemovedQuantity);
        shoppingCart.getBuketProduct().entrySet().removeIf(entry -> entry.getKey().equals(productid));

        return entityToDto(shoppingCartRepository.save(shoppingCart));
    }

    public ShoppingCartDTO getCart(long id) {
        return entityToDto(shoppingCartRepository.findById(id).orElseThrow(() -> new
                ShoppingCartNotFoundException("CART NOT FOUND WITH THIS ID :" + id)));
    }

    @Override
    public ShoppingCartDTO deleteAllProduct(long cartid) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartid).orElseThrow(() -> new
                ShoppingCartNotFoundException("CART NOT FOUND WITH THIS ID : " + cartid));
        shoppingCart.getBuketProduct().clear();
        shoppingCart.setTotalPrice(0.00);
        shoppingCart.setQuantity(0);
        return entityToDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public String checkout(long cartId) {
        ShoppingCartDTO shoppingCartDTO = entityToDto(shoppingCartRepository.findById(cartId).orElseThrow(() -> new
                ShoppingCartNotFoundException("CART NOT FOUND WITH THIS ID :" + cartId)));
        //publish shoppingCartDTO payment service(saif) topic
        return "published";
    }
}
