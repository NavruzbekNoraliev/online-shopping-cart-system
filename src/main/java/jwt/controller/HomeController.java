package jwt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwt.model.*;
import jwt.service.UserService;
import jwt.utility.JWTUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class HomeController {

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;



    //constructor injection
    public HomeController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping("/secureEndpoint")
    public String home2() {
        log.info("before return");
        return "Hello World, this is secured endpoint!";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

//    @GetMapping("/producttest/{id}")
//    public String getProduct(@PathVariable("id") Long id) throws JsonProcessingException {
//        String response = userService.getProduct(id);
//        ObjectMapper mapper = new ObjectMapper();
//        ProductDTO productDTO = mapper.readValue(response, ProductDTO.class);
//        log.info("Category: "+productDTO.getCategory().getName());
//        return userService.getProduct(id);
//    }

    //get all users
//    @GetMapping("/users/all")
//    public List<User> users() {
//        return userService.getUsers();
//    }

    //get user by id
//    @GetMapping("/users/{id}")
//    public User user(@PathVariable("id") int id) {
//        return userService.getUser(id);
//    }
//
//    //get user by username
//    @GetMapping("/users/username/{username}")
//    public User user(@PathVariable("username") String username) {
//        return userService.getUser(username);
//    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            throw new Exception("User is already authenticated");
//        }

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                            jwtRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails =
                userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }

    //register new user and return token
    @PostMapping("/register")
    public JwtResponse register(@RequestBody User userInfo) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            throw new Exception("User is already authenticated");
        }

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userInfo.getUsername(),
                            userInfo.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails =
                userService.loadUserByUsername(userInfo.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }

    //if user is already authenticated, return the user info
    @GetMapping("/user")
    public String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return userService.loadUserByUsername(authentication.getName()).getUsername() + " is authenticated";
        }
        return null;
    }

}
