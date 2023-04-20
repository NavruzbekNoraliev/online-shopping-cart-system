package com.adminmodule.controller;

import com.adminmodule.domain.Vendor;
import com.adminmodule.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    //get vendor by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }
    //add vendor
    @PostMapping()
    public ResponseEntity<?> addVendor(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.addVendor(vendor));
    }
    //update vendor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.updateVendor(id, vendor));
    }
    //delete vendor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approveVendor(@PathVariable Long id) {
        vendorService.approveVendor(id);
        return ResponseEntity.ok().build();
    }


    //pending vendors with good practices
    @GetMapping("/pending")
    public ResponseEntity<?> getAllPendingApprovalVendors() {
        return ResponseEntity.ok(vendorService.getAllPendingApprovalVendors());
    }

    @GetMapping("/pending-payment")
    public ResponseEntity<?> getAllPendingPaymentVendors() {
        return ResponseEntity.ok(vendorService.getAllPendingPaymentVendors());
    }
}
