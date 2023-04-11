package com.productvendor.service;

import com.productvendor.model.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    Vendor getVendorById(Long id);
    Vendor addVendor(Vendor vendor);
    Vendor updateVendor(Long id, Vendor vendor);
    void deleteVendor(Long id);
}
