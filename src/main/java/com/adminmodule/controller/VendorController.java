package com.adminmodule.controller;

import com.adminmodule.domain.Vendor;
import com.adminmodule.security.JWTUtility;
import com.adminmodule.service.EmployeeService;
import com.adminmodule.service.VendorService;
import com.adminmodule.service.dto.VendorAdaptor;
import com.adminmodule.service.dto.VendorAdminDTO;
import com.adminmodule.service.dto.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

    @Autowired
    EmployeeService employeeService;

    private final VendorService vendorService;

    private final JWTUtility jwtUtility;

    @Autowired
    public VendorController(VendorService vendorService, JWTUtility jwtUtility) {
        this.vendorService = vendorService;
        this.jwtUtility = jwtUtility;
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    //get vendor by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        VendorAdminDTO vendorAdminDTO = vendorService.getVendorAdminByUsername(username);
        Vendor vendor = vendorAdminDTO.getVendor();
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (vendor.getId() != id || !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    //add vendor
    @PostMapping()
    public ResponseEntity<?> addVendor(@RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.ok(vendorService.addVendor(vendorDTO));
    }
    //update vendor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVendor(@PathVariable Long id, @RequestBody Vendor vendor,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        VendorAdminDTO vendorAdminDTO = vendorService.getVendorAdminByUsername(username);
        VendorDTO vendor1 = VendorAdaptor.toDTO(vendorAdminDTO.getVendor());
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (vendor1.getId() != id || !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(vendorService.updateVendor(id, vendor1));
    }
    //delete vendor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable Long id,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        VendorAdminDTO vendorAdminDTO = vendorService.getVendorAdminByUsername(username);
        Vendor vendor = vendorAdminDTO.getVendor();
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (vendor.getId() != id || !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        vendorService.deleteVendor(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approveVendor(@PathVariable Long id,
                                           @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        //get role from token
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        //check if role is admin
        if (!roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        vendorService.approveVendor(id);
        return ResponseEntity.ok().build();
    }


    //pending vendors with good practices
    @GetMapping("/pending")
    public ResponseEntity<?> getAllPendingApprovalVendors(@RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        //get role from token
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        //check if role is admin
        if (!roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(vendorService.getAllPendingApprovalVendors());
    }

    @GetMapping("/pending-payment")
    public ResponseEntity<?> getAllPendingPaymentVendors(@RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        //get role from token
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        //check if role is admin
        if (!roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(vendorService.getAllPendingPaymentVendors());
    }
}
