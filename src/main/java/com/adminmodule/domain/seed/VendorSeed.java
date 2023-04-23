//package com.adminmodule.domain.seed;
//
//import com.adminmodule.domain.AccountDetails;
//import com.adminmodule.domain.Address;
//import com.adminmodule.domain.Enum.Status;
//import com.adminmodule.domain.Vendor;
//import com.adminmodule.repository.AccountDetailsRepository;
//import com.adminmodule.repository.AddressRepository;
//import com.adminmodule.repository.VendorRepository;
//import com.adminmodule.utils.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class VendorSeed implements CommandLineRunner {
//
//    private final VendorRepository vendorRepository;
//    private final AccountDetailsRepository accountDetailsRepository;
//    private final AddressRepository addressRepository;
//
//    @Autowired
//    public VendorSeed(VendorRepository vendorRepository,
//                      AccountDetailsRepository accountDetailsRepository,
//                      AddressRepository addressRepository) {
//        this.vendorRepository = vendorRepository;
//        this.accountDetailsRepository = accountDetailsRepository;
//        this.addressRepository = addressRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadData();
//    }
//
//    private void loadData() {
//        if (vendorRepository.count() == 0) {
//            String phone = "1234567890";
//
//            Address address = new Address("100 Main St", "Fairfield", "IA", "52557");
//            Address billingAddress = new Address("200 Main St", "Fairfield", "IA", "52557");
//            AccountDetails accountDetails = new AccountDetails("1234567890", "987654321", "Bank of Fairfield");
////        AccountDetails accountDetails1 =  accountDetailsRepository.save(accountDetails);
////        Address address1 = addressRepository.save(address);
////        Address billingAddress1 = addressRepository.save(billingAddress);
//            Vendor vendor = new Vendor( "John Dae", "johndoe@example.com", phone);
//            vendor.setAddress(addressRepository.save(address));
//            vendor.setBillingAddress(addressRepository.save(address));
//            vendor.setAccountDetails(accountDetailsRepository.save(accountDetails));
//            vendorRepository.save(vendor);
//        }
//
////        Address address2 = new Address("200 Main St", "Fairfield", "IA", "52557");
////        Address billingAddress2 = new Address("300 Main St", "Fairfield", "IA", "52557");
////        AccountDetails accountDetails2 = new AccountDetails("1111111111", "2222222222", "Bank of America");
////
////        AccountDetails accountDetails3 =  accountDetailsRepository.save(accountDetails2);
////        Address address3 = addressRepository.save(address2);
////        Address billingAddress3 = addressRepository.save(billingAddress2);
////        Vendor vendor2 = new Vendor("Jane Doe", "janedoe@example.com", phone, address3, billingAddress3, accountDetails3, Status.ACTIVE);
////        vendorRepository.save(vendor2);
////
////        // For INACTIVE status
////        Vendor vendor3 = new Vendor("Alice Smith", "alicesmith@example.com", phone, address3, billingAddress3, accountDetails3, Status.INACTIVE);
////        vendorRepository.save(vendor3);
////
////        // For DELETED status
////        Vendor vendor4 = new Vendor("Bob Johnson", "bobjohnson@example.com", phone, address, billingAddress3, accountDetails3, Status.DELETED);
////        vendorRepository.save(vendor4);
////
////        // For PENDING_PAYMENT status
////        Vendor vendor5 = new Vendor("Emily Brown", "emilybrown@example.com", phone, address, billingAddress, accountDetails, Status.PENDING_PAYMENT);
////        vendorRepository.save(vendor5);
////
////        // For REJECTED status
////        Vendor vendor6 = new Vendor("Samuel Green", "samuelgreen@example.com", phone, address3, billingAddress, accountDetails, Status.REJECTED);
////        vendorRepository.save(vendor6);
////
////        // For APPROVED status
////        Vendor vendor7 = new Vendor("Olivia White", "oliviawhite@example.com", phone, address, billingAddress, accountDetails3, Status.APPROVED);
////        vendorRepository.save(vendor7);
////
////        Vendor vendor8 = new Vendor("Robert Smith", "robertsmith@example.com", phone, address3, billingAddress, accountDetails, Status.BLOCKED);
////        vendorRepository.save(vendor3);
//
//    }
//}
