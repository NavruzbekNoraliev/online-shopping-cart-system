package com.adminmodule.controller;


import com.adminmodule.domain.Account;
import com.adminmodule.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    private final AuthService authService;

    public AuthenticateController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Object> generateToken(@RequestBody Account account) throws Exception {
        return authService.generateToken(account);
    }
}
