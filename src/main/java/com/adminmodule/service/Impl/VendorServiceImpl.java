package com.adminmodule.service.Impl;

import com.adminmodule.domain.Address;
import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.domain.Enum.Status;
import com.adminmodule.domain.Role;
import com.adminmodule.domain.Vendor;
import com.adminmodule.domain.VendorAdmin;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.exceptionResponse.userException.UserNotFoundException;
import com.adminmodule.repository.*;
import com.adminmodule.service.VendorService;
import com.adminmodule.service.dto.VendorAdaptor;
import com.adminmodule.service.dto.VendorAdminAdapter;
import com.adminmodule.service.dto.VendorAdminDTO;
import com.adminmodule.service.dto.VendorDTO;
import com.adminmodule.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {


    private final VendorRepository vendorRepository;
    private final AddressRepository addressRepository;
    private final AccountDetailsRepository accountDetailsRepository;
    private final VendorAdminRepository vendorAdminRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository,
                             AddressRepository addressRepository,
                             AccountDetailsRepository accountDetailsRepository,
                             VendorAdminRepository vendorAdminRepository,
                                RoleRepository roleRepository,
                                AccountRepository accountRepository){
        this.vendorRepository = vendorRepository;
        this.addressRepository = addressRepository;
        this.accountDetailsRepository = accountDetailsRepository;
        this.vendorAdminRepository = vendorAdminRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public List<Vendor> getAllVendors(String authorizationHeader) {
        return vendorRepository.findAll();
    }

    @Override
    public VendorDTO getVendorById(Long id, String authorizationHeader) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if (vendor.isPresent()) {
            return VendorAdaptor.toDTO(vendor.get());
        }
        throw new UserNotFoundException("Vendor not found");
    }

    @Override
    public VendorDTO addVendor(VendorDTO vendorDTO) {
        if (vendorRepository.findByEmail(vendorDTO.getEmail()) != null) {
            throw new UserBadRequestException("Email already exists.");
        }
        //if username or password is null
        if (vendorDTO.getEmail() == null) {
            throw new UserBadRequestException("Email is required.");
        }

        Vendor vendor1 = VendorAdaptor.fromDTO(vendorDTO);
        //check if address is null
        if (vendorDTO.getAddress() != null) {
            vendor1.setAddress(addressRepository.save(vendor1.getAddress()));
        }
        //check if billing address is null
        if (vendorDTO.getBillingAddress() != null) {
            vendor1.setBillingAddress(addressRepository.save(vendor1.getBillingAddress()));
        }
        //check if account details is null
        if (vendorDTO.getAccountDetails() != null) {
            vendor1.setAccountDetails(accountDetailsRepository.save(vendor1.getAccountDetails()));
        }
        vendor1.setStatus(Status.PENDING_APPROVAL);
        Vendor vendor = vendorRepository.save(vendor1);
        //create vendor admin
//        VendorAdmin vendorAdmin = vendor1.getVendorAdmins().toArray(new VendorAdmin[0])[0];
//        VendorAdminDTO vendorAdminDTO = addVendorAdmin((long) vendor.getId(),
//                VendorAdminAdapter.toDTO(vendorAdmin), null);

        return VendorAdaptor.toDTO(vendorRepository.save(vendor1));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO, String authorizationHeader) {
        Optional<Vendor> existingVendor = vendorRepository.findById(id);
        if (existingVendor.isPresent()) {
            Vendor vendor = existingVendor.get();
            vendor.setName(vendorDTO.getName());
            vendor.setPhone(vendorDTO.getPhone());
            if (vendor.getAddress() == null) {
                Address address = addressRepository.save(vendorDTO.getAddress());
                vendor.setAddress(address);
            } else {
                Optional<Address> existingAddress = addressRepository.findById(vendor.getAddress().getId());
                if (existingAddress.isPresent()) {
                    Address address = existingAddress.get();
                    address.setCity(vendorDTO.getAddress().getCity());
                    address.setState(vendorDTO.getAddress().getState());
                    address.setStreet(vendorDTO.getAddress().getStreet());
                    address.setZip(vendorDTO.getAddress().getZip());
                    addressRepository.save(address);
                }
            }
            if (vendor.getBillingAddress() == null) {
                Address address = addressRepository.save(vendorDTO.getBillingAddress());
                vendor.setBillingAddress(address);
            } else {
                Optional<Address> optionalAddress = addressRepository.findById(vendor.getBillingAddress().getId());
                if (optionalAddress.isPresent()) {
                    Address address = optionalAddress.get();
                    address.setCity(vendorDTO.getBillingAddress().getCity() != null ? vendorDTO.getBillingAddress().getCity() : address.getCity());
                    address.setState(vendorDTO.getBillingAddress().getState() != null ? vendorDTO.getBillingAddress().getState() : address.getState());
                    address.setStreet(vendorDTO.getBillingAddress().getStreet() != null ? vendorDTO.getBillingAddress().getStreet() : address.getStreet());
                    address.setZip(vendorDTO.getBillingAddress().getZip() != null ? vendorDTO.getBillingAddress().getZip() : address.getZip());
                    addressRepository.save(address);
                }
            }

            Vendor savedVendor = vendorRepository.save(vendor);
            return VendorAdaptor.toDTO(savedVendor);
        } else {
            throw new UserNotFoundException("Vendor not found");
        }
    }

    //update address, shipping address, account details for vendor


    @Override
    public void deleteVendor(Long id, String authorizationHeader) {
        vendorRepository.deleteById(id);
    }
    @Override
    public void approveVendor(Long id, String authorizationHeader) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Vendor not found"));
        existingVendor.setStatus(Status.APPROVED);
        vendorRepository.save(existingVendor);
    }
    @Override
    public List<Vendor> getAllPendingApprovalVendors(String authorizationHeader) {
        return vendorRepository.findAllPendingApprovalVendors();
    }

    @Override
    public List<Vendor> getAllPendingPaymentVendors(String authorizationHeader) {
        return vendorRepository.findAllPendingPaymentVendors();
    }

    @Override
    public VendorAdminDTO getVendorAdminByUsername(String username) {
        return vendorRepository.findVendorAdminByEmail(username)
                .map(VendorAdminAdapter::toDTO)
                .orElseThrow(() -> new UserNotFoundException("Vendor Admin not found"));
    }

    @Override
    public List<VendorAdmin> getAllVendorAdmins(String authorizationHeader) {
        List<VendorAdmin> vendorAdmins = vendorAdminRepository.findAll();
        if (!vendorAdmins.isEmpty()) {
            vendorAdmins.forEach(vendorAdmin ->{
                vendorAdmin.getAccount().setPassword("********");
            });
        }
        return vendorAdmins;
    }

    @Override
    public List<VendorAdmin> getAllVendorAdminsByVendor(Long vendorId, String authorizationHeader) {
        List<VendorAdmin> vendorAdmins = vendorAdminRepository.findAllByVendorId(vendorId);
        if (!vendorAdmins.isEmpty()) {
            vendorAdmins.forEach(vendorAdmin ->{
                vendorAdmin.getAccount().setPassword("********");
            });
        }
        return vendorAdmins;
    }

    @Override
    public VendorAdminDTO addVendorAdmin(Long vendorId, VendorAdminDTO vendorAdminDTO, String authorizationHeader) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new UserNotFoundException("Vendor not found"));

        if (vendorAdminDTO == null) {
            throw new UserBadRequestException("VendorAdmin cannot be null");
        }
        if (vendorAdminDTO.getEmail() == null) {
            throw new UserBadRequestException("Email is required");
        }
        if (vendorAdminDTO.getAccount().getPassword() == null) {
            throw new UserBadRequestException("Password is required");
        }
        if (accountRepository.existsByUsername(vendorAdminDTO.getEmail())) {
            throw new UserBadRequestException("Email already exists");
        }

        VendorAdmin vendorAdmin = VendorAdminAdapter.fromDTO(vendorAdminDTO);
        vendorAdmin.setVendor(vendor);
        Role role = roleRepository.findByRoleType(RoleType.VENDOR_ADMIN);
        vendorAdmin.getAccount().setRoles(Utils.addRoles(role));
        vendorAdmin.getAccount().setPassword(Utils.encodePassword(vendorAdmin.getAccount().getPassword()));
        vendorAdmin.getAccount().setUsername(vendorAdmin.getEmail());
        vendorAdmin.setAccount(accountRepository.save(vendorAdmin.getAccount()));
        VendorAdmin savedVendorAdmin = vendorAdminRepository.save(vendorAdmin);
        VendorAdminDTO newVendorAdminDTO = VendorAdminAdapter.toDTO(savedVendorAdmin);
        newVendorAdminDTO.getAccount().setPassword("********");
        return VendorAdminAdapter.toDTO(savedVendorAdmin);
    }

    @Override
    public VendorAdminDTO updateVendorAdmin(Long vendorId, Long vendorAdminId, VendorAdminDTO vendorAdminDTO, String authorizationHeader) {
        Optional<VendorAdmin> vendorAdminOptional = vendorAdminRepository.findById(vendorAdminId);
        if(vendorAdminOptional.isPresent()) {
            VendorAdmin vendorAdmin = vendorAdminOptional.get();
            vendorAdmin.setFirstName(vendorAdminDTO.getFirstName());
            vendorAdmin.setLastName(vendorAdminDTO.getLastName());
            vendorAdmin.setPhone(vendorAdminDTO.getPhone());
            VendorAdminDTO savedVendorAdmin = VendorAdminAdapter.toDTO(vendorAdmin);
            savedVendorAdmin.getAccount().setPassword("********");
            return savedVendorAdmin;
        }else{
            throw new UserNotFoundException("Vendor Admin not found");
        }
    }

    @Override
    public void deleteVendorAdmin(Long vendorId, Long vendorAdminId, String authorizationHeader) {
        vendorAdminRepository.deleteById(vendorAdminId);
    }


}