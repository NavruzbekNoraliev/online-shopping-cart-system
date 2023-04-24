package com.adminmodule.service;

import com.adminmodule.domain.Vendor;
import com.adminmodule.domain.VendorAdmin;
import com.adminmodule.service.dto.VendorAdminDTO;
import com.adminmodule.service.dto.VendorDTO;
import com.adminmodule.service.dto.VendorDTO1;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors(String authorizationHeader);
    VendorDTO getVendorById(Long id, String authorizationHeader);
    public VendorDTO addVendor(VendorDTO vendorDTO);
    VendorDTO updateVendor(Long id, VendorDTO vendorDTO, String authorizationHeader);
    void deleteVendor(Long id, String authorizationHeader);
    void approveVendor(Long id, String authorizationHeader);

    List<Vendor> getAllPendingApprovalVendors(String authorizationHeader);

    List<Vendor> getAllPendingPaymentVendors(String authorizationHeader);

    VendorAdminDTO getVendorAdminByEmail(String email);

    List<VendorAdmin> getAllVendorAdmins(String authorizationHeader);
    List<VendorAdmin> getAllVendorAdminsByVendor(Long vendorId, String authorizationHeader);
    VendorAdminDTO addVendorAdmin(Long vendorId, VendorAdminDTO vendorAdminDTO);
    VendorAdminDTO updateVendorAdmin(Long vendorId, Long vendorAdminId, VendorAdminDTO vendorAdminDTO, String authorizationHeader);

    void deleteVendorAdmin(Long vendorId, Long vendorAdminId, String authorizationHeader);
    
    VendorDTO1 getVendorForOrderById(Long vendorIdr);
}
