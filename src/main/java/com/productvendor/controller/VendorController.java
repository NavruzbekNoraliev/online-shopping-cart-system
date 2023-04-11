package com.productvendor.controller;

import com.productvendor.model.Vendor;
import com.productvendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    //get all vendors
    @GetMapping("/all")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    //get vendor by id
    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }
    //add vendor
    @PostMapping("/add")
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return vendorService.addVendor(vendor);
    }
    //update vendor
    @PutMapping("/update/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }
    //delete vendor
    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }

}
