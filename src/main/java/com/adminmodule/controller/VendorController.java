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

    @GetMapping("/all")
    public ResponseEntity<?> getAllVendors( @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(vendorService.getAllVendors(authorizationHeader));
    }

    //get vendor by id
    @GetMapping("/{vendorId}")
    public ResponseEntity<?> getVendorById(@PathVariable Long vendorId,
                                           @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(vendorService.getVendorById(vendorId, authorizationHeader));
    }

    //add vendor
    @PostMapping()
    public ResponseEntity<?> addVendor(@RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.ok(vendorService.addVendor(vendorDTO));
    }
    //update vendor
    @PutMapping("/{vendorId}")
    public ResponseEntity<?> updateVendor(@PathVariable Long vendorId,
                                          @RequestBody VendorDTO vendorDTO,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(vendorService.updateVendor(vendorId, vendorDTO, authorizationHeader));
    }
    //delete vendor
    @DeleteMapping("/{vendorId}")
    public ResponseEntity<?> deleteVendor(@PathVariable Long vendorId,
                                          @RequestHeader("Authorization") String authorizationHeader) {
        vendorService.deleteVendor(vendorId, authorizationHeader);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve/{vendorId}")
    public ResponseEntity<?> approveVendor(@PathVariable Long vendorId,
                                           @RequestHeader("Authorization") String authorizationHeader) {
        vendorService.approveVendor(vendorId, authorizationHeader);
        return ResponseEntity.ok().build();
    }


    //pending vendors with good practices
    @GetMapping("/pending-approval")
    public ResponseEntity<?> getAllPendingApprovalVendors(@RequestHeader("Authorization") String authorizationHeader) {
               return ResponseEntity.ok(vendorService.getAllPendingApprovalVendors(authorizationHeader));
    }

    @GetMapping("/pending-payment")
    public ResponseEntity<?> getAllPendingPaymentVendors(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(vendorService.getAllPendingPaymentVendors(authorizationHeader));
    }

    //All vendor-admins regardless of vendor
    @GetMapping("/vendor-admin")
    public ResponseEntity<?> getAllVendorAdmins(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(vendorService.getAllVendorAdmins(authorizationHeader));
    }

    //All vendor-admins from a specific vendor
    @GetMapping("/{vendorId}/vendor-admin")
    public ResponseEntity<?> getAllVendorAdminsByVendor(@PathVariable Long vendorId,
                                                          @RequestHeader("Authorization") String authorizationHeader){
        return ResponseEntity.ok(vendorService.getAllVendorAdminsByVendor(vendorId, authorizationHeader));
    }

    //add vendor-admin
    @PostMapping("/{vendorId}/vendor-admin")
    public ResponseEntity<?> addVendorAdmin(@PathVariable Long vendorId,
                                            @RequestBody VendorAdminDTO vendorAdminDTO,
                                            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(vendorService.addVendorAdmin(vendorId, vendorAdminDTO, authorizationHeader));
    }
    @PutMapping("/{vendorId}/vendor-admin/{vendorAdminId}")
    public ResponseEntity<?> updateVendorAdmin(@PathVariable Long vendorId,
                                               @PathVariable Long vendorAdminId,
                                               @RequestBody VendorAdminDTO vendorAdminDTO,
                                               @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok(vendorService.updateVendorAdmin(vendorId, vendorAdminId, vendorAdminDTO, authorizationHeader));
    }
    @DeleteMapping("/{vendorId}/vendor-admin/{vendorAdminId}")
    public ResponseEntity<?> deleteVendorAdmin(@PathVariable Long vendorId,
                                               @PathVariable Long vendorAdminId,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        vendorService.deleteVendorAdmin(vendorId, vendorAdminId, authorizationHeader);
        return ResponseEntity.ok().build();
    }
}
