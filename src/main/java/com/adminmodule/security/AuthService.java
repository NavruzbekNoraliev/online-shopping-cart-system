package com.adminmodule.security;

import com.adminmodule.domain.Account;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.service.CustomerService;
import com.adminmodule.service.EmployeeService;
import com.adminmodule.service.VendorService;
import com.adminmodule.service.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthService {
    private final CustomerService customerService;

    private final EmployeeService employeeService;

    private final VendorService vendorService;

    private final AuthenticationManager authenticationManager;

    private final AccountDetailsService accountDetailsService;

    private final JWTUtility jwtUtility;

    @Autowired
    public AuthService(CustomerService customerService,
                              AuthenticationManager authenticationManager,
                              AccountDetailsService accountDetailsService,
                              JWTUtility jwtUtility,
                              EmployeeService employeeService,
                              VendorService vendorService) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.accountDetailsService = accountDetailsService;
        this.jwtUtility = jwtUtility;
        this.employeeService = employeeService;
        this.vendorService = vendorService;
    }

    public ResponseEntity<Object> generateToken(@RequestBody Account account){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = accountDetailsService.loadUserByUsername(account.getEmail());
            final String token = jwtUtility.generateToken(userDetails);
            Map<String, Object> response = new HashMap<>();
            //check if the user is admin or customer or vendorAdmin and return the respective user
            if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))){
                response.put("bearerToken", token);
                response.put("user", customerService.getCustomerByUsername(account.getEmail()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
                response.put("bearerToken", token);
                response.put("user", employeeService.getEmployeeByEmail(account.getEmail()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_VENDOR_ADMIN"))){
                response.put("bearerToken", token);
                response.put("user", vendorService.getVendorAdminByEmail(account.getEmail()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                throw new UserBadRequestException("Invalid user");
            }
        } catch (AuthenticationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
