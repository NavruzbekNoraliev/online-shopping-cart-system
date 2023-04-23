package com.adminmodule.service.dto;

import com.adminmodule.domain.VendorAdmin;

public class VendorAdminAdapter {

    public static VendorAdmin fromDTO(VendorAdminDTO dto){
        return new VendorAdmin(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getVendor(),
                dto.getAccount());
    }

    public static VendorAdminDTO toDTO(VendorAdmin vendorAdmin){
    return new VendorAdminDTO(vendorAdmin.getId(),
                vendorAdmin.getFirstName(),
                vendorAdmin.getLastName(),
                vendorAdmin.getEmail(),
                vendorAdmin.getPhone(),
                vendorAdmin.getVendor(),
                vendorAdmin.getAccount());
    }


}
