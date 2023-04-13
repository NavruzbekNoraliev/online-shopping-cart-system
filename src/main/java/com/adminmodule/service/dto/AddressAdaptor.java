package com.adminmodule.service.dto;

import com.adminmodule.domain.Address;

public class AddressAdaptor {

    public static Address fromDTO(AddressDTO dto){

        return new Address(dto.getId(),
                dto.getStreet(),
                dto.getCity(),
                dto.getState(),
                dto.getZip());
    }

    public static AddressDTO toDTO(Address address){

        return new AddressDTO(address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip());
    }
}
