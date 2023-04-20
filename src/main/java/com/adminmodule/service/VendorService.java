package com.adminmodule.service;

import com.adminmodule.domain.Vendor;
import com.adminmodule.service.dto.VendorAdminDTO;
import com.adminmodule.service.dto.VendorDTO;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    VendorDTO getVendorById(Long id);
    public VendorDTO addVendor(VendorDTO vendorDTO);
    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
    void deleteVendor(Long id);
    void approveVendor(Long id);

    List<Vendor> getAllPendingApprovalVendors();

    List<Vendor> getAllPendingPaymentVendors();

    VendorAdminDTO getVendorAdminByUsername(String username);
}
