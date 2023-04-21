//package com.adminmodule.domain.seed;
//
//import com.adminmodule.domain.*;
//import com.adminmodule.domain.Enum.RoleType;
//import com.adminmodule.repository.AccountRepository;
//import com.adminmodule.repository.EmployeeRepository;
//import com.adminmodule.repository.RoleRepository;
//import com.adminmodule.utils.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//
//import static com.adminmodule.domain.Enum.RoleType.*;
//
//@Component
//public class AdminSeed implements CommandLineRunner {
//
//    private final EmployeeRepository employeeRepository;
//    private final AccountRepository accountRepository;
//    private final RoleRepository roleRepository;
//    @Autowired
//    public AdminSeed(EmployeeRepository employeeRepository,
//                     AccountRepository accountRepository,
//                     RoleRepository roleRepository) {
//        this.employeeRepository = employeeRepository;
//        this.accountRepository = accountRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadData();
//    }
//
//    private void loadData() {
//        if (roleRepository.count() == 0) {
//            Role data1 = new Role(ADMIN);
//            Role data2 = new Role(CUSTOMER);
//            Role data3 = new Role(VENDOR_ADMIN);
//
//            roleRepository.save(data1);
//            roleRepository.save(data2);
//            roleRepository.save(data3);
//
//
//        }
//
//        if (employeeRepository.count() == 0) {
//            String password = Utils.encodePassword("Aa@12345");
//            String phone = "1234567890";
//            Role roleA = roleRepository.findByRoleType(RoleType.ADMIN);
//
//            Customer nav = new Customer("Mehmet", "A", phone, "Mehmet@gmail.com");
//            alp.setBillingAddress(addressRepository.save(address2));
//            alp.setShippingAddress(addressRepository.save(address2));
//            Account account2 = new Account(alp.getEmail(), password);
//            account2.setRoles(Set.of(roleC));
//            alp.setAccount(accountRepository.save(account2));
////            customerRepository.save(alp);
//
//
//        Customer abdu = new Customer("Abdulhakim", "E", phone, "abdu@gmail.com");
//        Address address3 = new Address("200 s 4th St", "Iowa City", "IA", "55647");
//        abdu.setBillingAddress(addressRepository.save(address3));
//        abdu.setShippingAddress(addressRepository.save(address3));
//        Account account3 = new Account(abdu.getEmail(), password);
//        account3.setRoles(Set.of(roleA));
//        abdu.setAccount(accountRepository.save(account3));
//        customerRepository.save(abdu);
//
//    }
//}
