package com.adminmodule.service;

import com.adminmodule.domain.Vendor;
import com.adminmodule.service.dto.VendorAdminDTO;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    Vendor getVendorById(Long id);
    Vendor addVendor(Vendor vendor);
    Vendor updateVendor(Long id, Vendor vendor);
    void deleteVendor(Long id);
    void approveVendor(Long id);

    List<Vendor> getAllPendingApprovalVendors();

    List<Vendor> getAllPendingPaymentVendors();

    VendorAdminDTO getVendorAdminByUsername(String username);
}
