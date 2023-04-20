package com.adminmodule.service.Impl;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.domain.Role;
import com.adminmodule.domain.VendorAdmin;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.exceptionResponse.userException.UserNotFoundException;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.repository.RoleRepository;
import com.adminmodule.repository.VendorAdminRepository;
import com.adminmodule.service.VendorAdminService;
import com.adminmodule.service.dto.VendorAdminAdapter;
import com.adminmodule.service.dto.VendorAdminDTO;
import com.adminmodule.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VendorAdminServiceImpl implements VendorAdminService {

   private final VendorAdminRepository vendorAdminRepository;
   private final RoleRepository roleRepository;
   private final AccountRepository accountRepository;

   @Autowired
    public VendorAdminServiceImpl(VendorAdminRepository vendorAdminRepository,
                                  RoleRepository roleRepository,
                                  AccountRepository accountRepository
                                  ) {
        this.vendorAdminRepository = vendorAdminRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
   }

    @Override
    public List<VendorAdmin> getAllVendorAdmins() {
       return vendorAdminRepository.findAll();
    }

    @Override
    public VendorAdminDTO getVendorAdminById(Long id) {
        Optional<VendorAdmin> vendorAdmin = vendorAdminRepository.findById(id);

        if(vendorAdmin.isPresent()){
            return VendorAdminAdapter.toDTO(vendorAdmin.get());
        }else{
            throw new UserNotFoundException("Employee not found");
        }
    }

    @Override
    public VendorAdminDTO addVendorAdmin(VendorAdminDTO vendorAdminDTO) {
        Optional<VendorAdmin> vendorAdminOptional = vendorAdminRepository.findByEmail(vendorAdminDTO.getEmail());
        if(vendorAdminOptional.isPresent()){
            throw new UserBadRequestException("VendorAdmin already exists");
        }
        VendorAdmin vendorAdmin = VendorAdminAdapter.fromDTO(vendorAdminDTO);

        Role role = roleRepository.findByRoleType(RoleType.VENDOR_ADMIN);
        vendorAdmin.getAccount().setRoles(Utils.addRoles(role));
        vendorAdmin.getAccount().setPassword(Utils.encodePassword(vendorAdmin.getAccount().getPassword()));
        vendorAdmin.getAccount().setUsername(vendorAdmin.getEmail());
        Account account = accountRepository.save(vendorAdmin.getAccount());
        vendorAdmin.setAccount(account);

        VendorAdmin savedVendorAdmin = vendorAdminRepository.save(vendorAdmin);

        return VendorAdminAdapter.toDTO(savedVendorAdmin);
    }

    @Override
    public VendorAdminDTO updateVendorAdmin(Long id, VendorAdminDTO vendorAdminDTO) {
        Optional<VendorAdmin> vendorAdminOptional = vendorAdminRepository.findById(id);
        if(vendorAdminOptional.isPresent()) {
            VendorAdmin vendorAdmin = vendorAdminOptional.get();
            vendorAdmin.setFirstName(vendorAdminDTO.getFirstName());
            vendorAdmin.setLastName(vendorAdminDTO.getLastName());
            vendorAdmin.setPhone(vendorAdminDTO.getPhone());
            VendorAdmin savedEmployee = vendorAdminRepository.save(vendorAdmin);
            return VendorAdminAdapter.toDTO(savedEmployee);
        }else{
            throw new UserNotFoundException("Employee not found");
        }

    }

    @Override
    public void deleteVendorAdmin(Long id) {

       vendorAdminRepository.deleteById(id);
    }

    @Override
    public VendorAdminDTO verifyVendor(Account account) {
        Optional<VendorAdmin> vendorAdminOptional = vendorAdminRepository.findByEmail(account.getUsername());
        if(vendorAdminOptional.isPresent()){
            VendorAdmin vendorAdmin = vendorAdminOptional.get();
            if(Utils.checkPassword(vendorAdmin.getAccount().getPassword(),account.getPassword())){
                return VendorAdminAdapter.toDTO(vendorAdmin);
            }else{
                throw new UserBadRequestException("Invalid password");
            }
        }else{
            throw new UserNotFoundException("VendorAdmin not found");
        }
    }

    @Override
    public VendorAdminDTO getVendorAdminByUsername(String username) {
        return vendorAdminRepository.findByEmail(username)
                .map(VendorAdminAdapter::toDTO)
                .orElseThrow(() -> new UserNotFoundException("VendorAdmin not found"));
    }
}
