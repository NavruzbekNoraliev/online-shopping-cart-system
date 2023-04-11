package com.productvendor.service.Impl;

import com.productvendor.model.Vendor;
import com.productvendor.repository.VendorRepo;
import com.productvendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepo vendorRepository;

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "id", id));
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "id", id));
        existingVendor.setName(vendor.getName());
        existingVendor.setPhoneNumber(vendor.getPhoneNumber());
        existingVendor.setEmail(vendor.getEmail());
        existingVendor.setAddress(vendor.getAddress());
//        existingVendor.setAccountDetails(vendor.getAccountDetails());
        existingVendor.setBillingAddress(vendor.getBillingAddress());

        return vendorRepository.save(existingVendor);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}

