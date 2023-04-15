package com.adminmodule.controller;

import com.adminmodule.service.VendorAdminService;
import com.adminmodule.service.dto.VendorAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendor-admin")
public class VendorAdminController {

    private final VendorAdminService vendorAdminService;

    @Autowired
    public VendorAdminController(VendorAdminService vendorAdminService) {
        this.vendorAdminService = vendorAdminService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVendorAdmins() {
        return ResponseEntity.ok(vendorAdminService.getAllVendorAdmins());
    }
    @GetMapping("/vendor/{id}")
    public ResponseEntity<?> getVendorAdminsByVendorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendorAdminService.getVendorAdminById(id));
    }

    @PostMapping
    public ResponseEntity<?> addVendorAdmin(@RequestBody VendorAdminDTO vendorAdminDTO) {
        return ResponseEntity.ok(vendorAdminService.addVendorAdmin(vendorAdminDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVendorAdmin(@PathVariable Long id, @RequestBody VendorAdminDTO vendorAdminDTO) {
        return ResponseEntity.ok(vendorAdminService.updateVendorAdmin(id, vendorAdminDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVendorAdmin(@PathVariable Long id) {
        vendorAdminService.deleteVendorAdmin(id);
        return ResponseEntity.ok().build();
    }

}
