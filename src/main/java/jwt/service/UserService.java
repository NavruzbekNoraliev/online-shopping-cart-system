package jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwt.model.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private ProductSample productSample;

    //constructor injection
    public UserService(ProductSample productSample) {
        this.productSample = productSample;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //get user from account service, for now, just dummy data

        return new User("admin", "password", new ArrayList<>());
    }

    //get product from product service
    public String getProduct(Long id) throws JsonProcessingException {
        log.info("before return");
        ObjectMapper mapper = new ObjectMapper();
        ProductDTO productDTO = productSample.getProducts(id);
        String json = mapper.writeValueAsString(productDTO);
        return json;
    }
}