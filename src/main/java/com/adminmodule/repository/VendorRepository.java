package com.adminmodule.repository;

import com.adminmodule.domain.Vendor;
import com.adminmodule.domain.VendorAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query("SELECT v FROM Vendor v WHERE v.status = 5")
    List<Vendor> findAllPendingApprovalVendors();
    @Query("SELECT v FROM Vendor v WHERE v.status = 3")
    List<Vendor> findAllPendingPaymentVendors();

    @Query("SELECT va FROM VendorAdmin va WHERE va.email = ?1")
    Optional<VendorAdmin> findVendorAdminByEmail(String email);

    //find vendor by email
    Optional<Vendor> findByEmail(String email);
}
