//package com.adminmodule.service.dto;
//
//import com.adminmodule.domain.VendorAdmin;
//
//public class VendorAdminAdaptor {
//
//    public static VendorAdmin fromDTO(VendorAdminDTO dto){
//        VendorAdmin vendorAdmin = new VendorAdmin(dto.getId(),
//                dto.getFirstName(),
//                dto.getLastName(),
//                dto.getEmail(),
//                dto.getPhone(),
//                dto.getUsername(),
//                dto.getPassword(),
//                dto.getVendor(),
//                dto.getRole());
//
//        return vendorAdmin;
//    }
//
//    public static VendorAdminDTO toDTO(VendorAdmin vendorAdmin){
//        VendorAdminDTO vendorAdminDTO = new VendorAdminDTO(vendorAdmin.getId(),
//                vendorAdmin.getFirstName(),
//                vendorAdmin.getLastName(),
//                vendorAdmin.getEmail(),
//                vendorAdmin.getPhone(),
//                vendorAdmin.getUsername(),
//                vendorAdmin.getPassword(),
//                vendorAdmin.getVendor(),
//                vendorAdmin.getRole());
//        return vendorAdminDTO;
//    }
//
//
//}
