package com.adminmodule.controller;

import com.adminmodule.domain.Vendor;
import com.adminmodule.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {


    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    //get all vendors
    @GetMapping()
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    //get vendor by id
    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }
    //add vendor
    @PostMapping()
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return vendorService.addVendor(vendor);
    }
    //update vendor
    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }
    //delete vendor
    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }

    @PostMapping("/approve/{id}")
    public void approveVendor(@PathVariable Long id) {
        vendorService.approveVendor(id);
    }

    @GetMapping("/pending-approval")
    public List<Vendor> getAllPendingApprovalVendors() {
        return vendorService.getAllPendingApprovalVendors();
    }

    @GetMapping("/pending-payment")
    public List<Vendor> getAllPendingPaymentVendors() {
        return vendorService.getAllPendingPaymentVendors();
    }

//    @Autowired
//    AccountFeignClient accountClient;
//    @RequestMapping("/customer/{customerid}")
//    public Account getName(@PathVariable("customerid") String customerId) {
//        Account account = accountClient.getName(customerId);
//        return account;
//    }
//    @FeignClient(name = "account-service", url = "http://localhost:8090")
//    interface AccountFeignClient {
//        @RequestMapping("/account/{customerid}")
//        public Account getName(@PathVariable("customerid") String customerId);
//    }
}
