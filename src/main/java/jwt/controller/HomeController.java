package jwt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwt.model.*;
import jwt.service.UserService;
import jwt.utility.JWTUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/home2")
    public String home2() {
        log.info("before return");
        return "Hello World, seems working with no id";
    }

    @GetMapping("/producttest/{id}")
    public String getProduct(@PathVariable("id") Long id) throws JsonProcessingException {
        String response = userService.getProduct(id);
        ObjectMapper mapper = new ObjectMapper();
        ProductDTO productDTO = mapper.readValue(response, ProductDTO.class);
        log.info("Category: "+productDTO.getCategory().getName());
        return userService.getProduct(id);
    }

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
}
