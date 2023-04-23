package com.adminmodule.service.dto;

import com.adminmodule.domain.Vendor;

public class VendorAdaptor {
    //fromDTO, toDTO

    public static VendorDTO toDTO(Vendor vendor){
        return new VendorDTO(vendor.getId(),
                vendor.getName(),
                vendor.getEmail(),
                vendor.getPhone(),
                vendor.getAddress(),
                vendor.getBillingAddress(),
                vendor.getAccountDetails(),
                vendor.getVendorStatus());
    }

    public static Vendor fromDTO(VendorDTO dto){
        return new Vendor(dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getBillingAddress(),
                dto.getAccountDetails(),
                dto.getVendorStatus());
    }
}
