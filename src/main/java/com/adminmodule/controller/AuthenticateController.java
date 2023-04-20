package com.adminmodule.controller;


import com.adminmodule.domain.Account;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticateController {

    private final AuthService authService;

    public AuthenticateController(AuthService authService) {
        this.authService = authService;
    }

    //with manual exception handling

    @PostMapping
    public ResponseEntity<Object> generateToken(@RequestBody Account account) {
        return authService.generateToken(account);
    }
}
