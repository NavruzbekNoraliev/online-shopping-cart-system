package com.adminmodule.domain.seed;

import com.adminmodule.domain.*;
import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.repository.AddressRepository;
import com.adminmodule.repository.CustomerRepository;
import com.adminmodule.repository.RoleRepository;
import com.adminmodule.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.adminmodule.domain.Enum.RoleType.*;
@Component
public class CustomerSeed implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    @Autowired
    public CustomerSeed(RoleRepository roleRepository,
                        CustomerRepository customerRepository,
                        AccountRepository accountRepository,
                        AddressRepository addressRepository){
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
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

        if (customerRepository.count() == 0) {
            String password = Utils.encodePassword("Aa@12345");
            String phone = "1234567890";
            Role roleC = roleRepository.findByRoleType(RoleType.CUSTOMER);
            Role roleA = roleRepository.findByRoleType(RoleType.ADMIN);
            Role roleV = roleRepository.findByRoleType(RoleType.VENDOR_ADMIN);


            Customer customer = new Customer("Arda", "A", phone, "arda@gmail.com");
            Address address = new Address("1000N4th St", "Fairfield", "IA", "52557");
            Address shippingAddress = addressRepository.save(address);
            customer.setBillingAddress(shippingAddress);
            customer.setShippingAddress(shippingAddress);
            Account account = new Account(customer.getEmail(), password);
            account.setRoles(Set.of(roleC));
            Account newAccount = accountRepository.save(account);
            customer.setAccount(newAccount);
            customerRepository.save(customer);

            Customer customer1 = new Customer("Biniam", "G", phone, "biniam@gmail.com");
            Address address2 = new Address("117th st", "Fairfield", "MN", "53337");
            customer1.setBillingAddress(addressRepository.save(address2));
            customer1.setShippingAddress(addressRepository.save(address2));
            Account account1 = new Account(customer1.getEmail(), password);
            account1.setRoles(Set.of(roleC));
            customer1.setAccount(accountRepository.save(account1));
            customerRepository.save(customer1);


        }
    }
}
