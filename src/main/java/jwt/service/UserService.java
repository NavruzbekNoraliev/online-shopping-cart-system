package jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwt.model.ProductDTO;
//import jwt.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {

//    private ProductSample productSample;
//
//    //constructor injection
//    public UserService(ProductSample productSample) {
//        this.productSample = productSample;
//    }

    //get all users
//    @Autowired
//    private UserRepo userRepo;

    //get all users
//    public List<jwt.model.User> getUsers() {
//        return userRepo.findAll();
//    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //get user from account service, for now, just dummy data

        return new User("Abdulhakim", "password", new ArrayList<>());
    }

    //register user
//    public void registerUser(jwt.model.User user) {
//        userRepo.save(user);
//    }

    //get user by id
//    public jwt.model.User getUser(Long id) {
//        return userRepo.findById(id).get();
//    }
//
//    //get user by username
//    public jwt.model.User getUser(String username) {
//        return userRepo.findByUsername(username);
//    }



    //get product from product service
//    public String getProduct(Long id) throws JsonProcessingException {
//        log.info("before return");
//        ObjectMapper mapper = new ObjectMapper();
//        ProductDTO productDTO = productSample.getProducts(id);
//        String json = mapper.writeValueAsString(productDTO);
//        return json;
//    }
}
