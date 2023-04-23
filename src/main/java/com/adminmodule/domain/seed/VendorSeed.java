package com.adminmodule.domain.seed;

import com.adminmodule.domain.*;
import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.domain.Enum.VendorStatus;
import com.adminmodule.repository.*;
import com.adminmodule.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.adminmodule.domain.Enum.RoleType.*;

@Component
public class VendorSeed implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final VendorRepository vendorRepository;
    private final AccountDetailsRepository accountDetailsRep;

    private final VendorAdminRepository vendorAdminRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public VendorSeed(AddressRepository addressRepository,
                      VendorRepository vendorRepository,
                      AccountDetailsRepository accountDetailsRep,
                      VendorAdminRepository vendorAdminRepository,
                      RoleRepository roleRepository,
                      AccountRepository accountRepository){
        this.addressRepository = addressRepository;
        this.vendorRepository = vendorRepository;
        this.accountDetailsRep = accountDetailsRep;
        this.vendorAdminRepository = vendorAdminRepository;
        this.roleRepository = roleRepository;

        this.accountRepository = accountRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (roleRepository.count() == 0) {
            Role data1 = new Role(ADMIN);
            Role data2 = new Role(CUSTOMER);
            Role data3 = new Role(VENDOR_ADMIN);

            roleRepository.save(data1);
            roleRepository.save(data2);
            roleRepository.save(data3);

        }
        if (vendorRepository.count() == 0) {
            Role roleV = roleRepository.findByRoleType(RoleType.VENDOR_ADMIN);
            String password = Utils.encodePassword("Aa@12345");
            // First, create a vendor object and set its properties
            Vendor vendorA = new Vendor("ABC Company", "555-9876", "abc@example.com", VendorStatus.PENDING_PAYMENT);
//            vendorA.setAddress(addressRepository.save(new Address("456 Oak St", "Smallville", "NY", "67890")));
//            vendorA.setBillingAddress(addressRepository.save(new Address("456 Oak St", "Smallville", "NY", "67890")));
            vendorA.setAccountDetails(accountDetailsRep.save(new AccountDetails("2552336", "East Coast", "98765432")));
            vendorRepository.save(vendorA);

// Next, create a vendor admin object and set its properties
            VendorAdmin adminA = new VendorAdmin("Alice", "Smith", "alice@example.com", "1252352156");
            Account accountA = new Account(adminA.getEmail(), password);
            accountA.setRoles(Set.of(roleV));
            adminA.setAccount(accountRepository.save(accountA));

// Set the vendor property of the admin object to the previously created vendor object
            adminA.setVendor(vendorRepository.save(vendorA));

// Finally, save the vendor admin object to the repository
            vendorAdminRepository.save(adminA);

            Vendor vendorB = new Vendor("XYZ Corporation", "555-1234", "xyz@example.com", VendorStatus.ACTIVE);
//            vendorB.setAddress(addressRepository.save(new Address("789 Main St", "Bigtown", "CA", "12345")));
//            vendorB.setBillingAddress(addressRepository.save(new Address("789 Main St", "Bigtown", "CA", "12345")));
            vendorB.setAccountDetails(accountDetailsRep.save(new AccountDetails("3566221", "West Coast", "78901234")));
            vendorRepository.save(vendorB);

            VendorAdmin adminB = new VendorAdmin("Bob", "Johnson", "bob@example.com", "9876543210");
            Account accountB = new Account(adminB.getEmail(), password);
            accountB.setRoles(Set.of(roleV));
            adminB.setAccount(accountRepository.save(accountB));
            adminB.setVendor(vendorRepository.save(vendorB));
            vendorAdminRepository.save(adminB);

            Vendor vendorC = new Vendor("Acme Inc.", "555-5678", "acme@example.com", VendorStatus.ACTIVE);
//            vendorC.setAddress(addressRepository.save(new Address("123 1st St", "Anytown", "TX", "54321")));
//            vendorC.setBillingAddress(addressRepository.save(new Address("123 1st St", "Anytown", "TX", "54321")));
            vendorC.setAccountDetails(accountDetailsRep.save(new AccountDetails("456123", "Southwest", "24680975")));
            vendorRepository.save(vendorC);

            VendorAdmin adminC = new VendorAdmin("Charlie", "Davis", "charlie@example.com", "3698521470");
            Account accountC = new Account(adminC.getEmail(), password);
            accountC.setRoles(Set.of(roleV));
            adminC.setAccount(accountRepository.save(accountC));
            adminC.setVendor(vendorRepository.save(vendorC));
            vendorAdminRepository.save(adminC);

            Vendor vendorD = new Vendor("Acme Ltd.", "555-4321", "acmeltd@example.com", VendorStatus.PENDING_APPROVAL);
//            vendorD.setAddress(addressRepository.save(new Address("321 Elm St", "Somewhere", "FL", "24680")));
//            vendorD.setBillingAddress(addressRepository.save(new Address("321 Elm St", "Somewhere", "FL", "24680")));
            vendorD.setAccountDetails(accountDetailsRep.save(new AccountDetails("123456", "Southeast", "13579024")));
            vendorRepository.save(vendorD);

            VendorAdmin adminD = new VendorAdmin("David", "Lee", "david@example.com", "1472583690");
            Account accountD = new Account(adminD.getEmail(), password);
            accountD.setRoles(Set.of(roleV));
            adminD.setAccount(accountRepository.save(accountD));
            adminD.setVendor(vendorRepository.save(vendorD));
            vendorAdminRepository.save(adminD);

            // Vendor 5
            Vendor vendorE = new Vendor("123 Enterprises", "555-5678", "123@example.com", VendorStatus.PENDING_PAYMENT);
//            vendorE.setAddress(addressRepository.save(new Address("321 Elm St", "Bigcity", "TX", "54321")));
//            vendorE.setBillingAddress(addressRepository.save(new Address("321 Elm St", "Bigcity", "TX", "54321")));
            vendorE.setAccountDetails(accountDetailsRep.save(new AccountDetails("44332211", "Southwest", "87654321")));
            vendorRepository.save(vendorE);

            VendorAdmin adminE = new VendorAdmin("Emma", "Taylor", "emma@example.com", "9012345678");
            Account accountE = new Account(adminE.getEmail(), password);
            accountE.setRoles(Set.of(roleV));
            adminE.setAccount(accountRepository.save(accountE));
            adminE.setVendor(vendorRepository.save(vendorE));
            vendorAdminRepository.save(adminE);

        }
    }
}


