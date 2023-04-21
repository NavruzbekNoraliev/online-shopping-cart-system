package com.adminmodule.repository;

import com.adminmodule.domain.VendorAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorAdminRepository extends JpaRepository<VendorAdmin, Long> {

    Optional<VendorAdmin> findByEmail(String email);

    List<VendorAdmin> findAllByVendorId(Long vendorId);
}