package com.adminmodule.service.Impl;

import com.adminmodule.domain.Enum.Status;
import com.adminmodule.domain.Vendor;
import com.adminmodule.domain.VendorAdmin;
import com.adminmodule.exceptionResponse.userException.UserNotFoundException;
import com.adminmodule.repository.VendorRepository;
import com.adminmodule.service.VendorService;
import com.adminmodule.service.dto.VendorAdminAdapter;
import com.adminmodule.service.dto.VendorAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {


    private final VendorRepository vendorRepository;
    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Vendor not found"));
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        vendor.setStatus(Status.PENDING_PAYMENT);
        return vendorRepository.save(vendor);
    }

    public void approveVendor(Long id) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Vendor not found"));
        existingVendor.setStatus(Status.APPROVED);
        vendorRepository.save(existingVendor);
    }

    @Override
    public List<Vendor> getAllPendingApprovalVendors() {
        return vendorRepository.findAllPendingApprovalVendors();
    }

    @Override
    public List<Vendor> getAllPendingPaymentVendors() {
        return vendorRepository.findAllPendingPaymentVendors();
    }

    @Override
    public VendorAdminDTO getVendorAdminByUsername(String username) {
        return vendorRepository.findVendorAdminByEmail(username)
                .map(VendorAdminAdapter::toDTO)
                .orElseThrow(() -> new UserNotFoundException("Vendor Admin not found"));
    }


    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Vendor not found"));
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