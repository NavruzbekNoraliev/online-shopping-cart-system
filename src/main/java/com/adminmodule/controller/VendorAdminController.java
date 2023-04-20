package com.adminmodule.controller;

import com.adminmodule.security.AuthService;
import com.adminmodule.security.JWTUtility;
import com.adminmodule.service.VendorAdminService;
import com.adminmodule.service.dto.VendorAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendor-admin")
public class VendorAdminController {

    private final VendorAdminService vendorAdminService;

    private final JWTUtility jwtUtility;

    @Autowired
    public VendorAdminController(VendorAdminService vendorAdminService, JWTUtility jwtUtility) {
        this.vendorAdminService = vendorAdminService;
        this.jwtUtility = jwtUtility;
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllVendorAdmins() {
        return ResponseEntity.ok(vendorAdminService.getAllVendorAdmins());
    }
    @GetMapping("/vendor/{id}")
    public ResponseEntity<?> getVendorAdminsByVendorId(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        VendorAdminDTO vendorAdminDTO = vendorAdminService.getVendorAdminByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (vendorAdminDTO.getId() != id || !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(vendorAdminService.getVendorAdminById(id));
    }

    @PostMapping
    public ResponseEntity<?> addVendorAdmin(@RequestBody VendorAdminDTO vendorAdminDTO) {
        return ResponseEntity.ok(vendorAdminService.addVendorAdmin(vendorAdminDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVendorAdmin(@PathVariable Long id, @RequestBody VendorAdminDTO vendorAdminDTO,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        VendorAdminDTO vendorAdminDTO1 = vendorAdminService.getVendorAdminByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (vendorAdminDTO1.getId() != id || !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(vendorAdminService.updateVendorAdmin(id, vendorAdminDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVendorAdmin(@PathVariable Long id,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        String username = jwtUtility.getUsernameFromToken(jwtToken);
        VendorAdminDTO vendorAdminDTO1 = vendorAdminService.getVendorAdminByUsername(username);
        List<String> roles = jwtUtility.getRoleFromToken(jwtToken);
        if (vendorAdminDTO1.getId() != id || !roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }
        vendorAdminService.deleteVendorAdmin(id);
        return ResponseEntity.ok().build();
    }
}
