package com.adminmodule.service.Impl;
import com.adminmodule.domain.Account;
import com.adminmodule.domain.Address;
import com.adminmodule.domain.Customer;

import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.domain.Role;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.exceptionResponse.userException.UserNotFoundException;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.repository.AddressRepository;
import com.adminmodule.repository.CustomerRepository;
import com.adminmodule.repository.RoleRepository;
import com.adminmodule.service.CustomerService;
import com.adminmodule.service.dto.AddressAdaptor;
import com.adminmodule.service.dto.AddressDTO;
import com.adminmodule.service.dto.CustomerAdapter;
import com.adminmodule.service.dto.CustomerDTO;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               AddressRepository addressRepository,
                               RoleRepository roleRepository,
                               AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;

    }




    @Override
    public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
       Optional<Customer>  customer = customerRepository.findById(id);

         if(customer.isPresent()){

                return CustomerAdapter.toDTO(customer.get());
         }
         else{
             throw new UserNotFoundException("Customer not found");
         }
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(customerDTO.getEmail());
        if(customerOptional.isPresent()){
            throw new UserBadRequestException("Customer already exists");
        }

        Customer customer = CustomerAdapter.fromDTO(customerDTO);


        if(customer.getShippingAddress() != null){
            Address address = addressRepository.save(customer.getShippingAddress());
           customer.setShippingAddress(address);
        }
        if(customer.getBillingAddress() != null){
            Address address = addressRepository.save(customer.getBillingAddress());
            customer.setBillingAddress(address);
        }

        customer.getAccount().setPassword(Utils.encodePassword(customer.getAccount().getPassword()));
        customer.getAccount().setUsername(customer.getEmail());
        Role role = roleRepository.findByRoleType(RoleType.CUSTOMER);
        customer.getAccount().setRoles(Utils.addRoles(role));

        Account account = accountRepository.save(customer.getAccount());
        customer.setAccount(account);
        Customer newCustomer =  customerRepository.save(customer);
        return CustomerAdapter.toDTO(newCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setFirstName(customerDTO.getFirstName() != null ? customerDTO.getFirstName() : customer.getFirstName());
            customer.setLastName(customerDTO.getLastName() != null ? customerDTO.getLastName() : customer.getLastName());
            customer.setPhone(customerDTO.getPhone() != null ? customerDTO.getPhone() : customer.getPhone());
            System.out.println(customer+ " test case");
            if(customer.getBillingAddress() == null){
                Address address = addressRepository.save(customerDTO.getBillingAddress());
                System.out.println(address);
                customer.setBillingAddress(address);
            }else{
                Optional<Address> optionalAddress = addressRepository.findById(customer.getBillingAddress().getId());
                if (optionalAddress.isPresent()) {
                    Address address = optionalAddress.get();
                    address.setCity(customerDTO.getBillingAddress().getCity() != null ? customerDTO.getBillingAddress().getCity() : address.getCity());
                    address.setState(customerDTO.getBillingAddress().getState() != null ? customerDTO.getBillingAddress().getState() : address.getState());
                    address.setStreet(customerDTO.getBillingAddress().getStreet() != null ? customerDTO.getBillingAddress().getStreet() : address.getStreet());
                    address.setZip(customerDTO.getBillingAddress().getZip() != null ? customerDTO.getBillingAddress().getZip() : address.getZip());
                    addressRepository.save(address);
                }
            }
            if(customer.getShippingAddress() == null){
                customer.setShippingAddress(addressRepository.save(customerDTO.getShippingAddress()));
            }else{
               Optional<Address> optionalAddress = addressRepository.findById(customer.getShippingAddress().getId());
               if(optionalAddress.isPresent()){
                    Address address = optionalAddress.get();
                    address.setCity(customerDTO.getShippingAddress().getCity() != null ? customerDTO.getShippingAddress().getCity() : address.getCity());
                    address.setState(customerDTO.getShippingAddress().getState() != null ? customerDTO.getShippingAddress().getState() : address.getState());
                    address.setStreet(customerDTO.getShippingAddress().getStreet() != null ? customerDTO.getShippingAddress().getStreet() : address.getStreet());
                    address.setZip(customerDTO.getShippingAddress().getZip() != null ? customerDTO.getShippingAddress().getZip() : address.getZip());
                    addressRepository.save(address);
               }

            }

       Customer updatedCustomer = customerRepository.save(customer);

            return CustomerAdapter.toDTO(updatedCustomer);
        }
        else{
            throw new UserNotFoundException("Customer not found");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public AddressDTO updateShippingAddress(AddressDTO addressDTO, Long id) {
       Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if(addressDTO != null){
                customer.setShippingAddress(addressRepository.save(AddressAdaptor.fromDTO(addressDTO)));
                customerRepository.save(customer);
            }
            return AddressAdaptor.toDTO(customer.getShippingAddress());
        }
        else{
            throw new UserNotFoundException("Customer not found");
        }
    }

    @Override
    public AddressDTO updateBillingAddress(AddressDTO addressDTO, Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if(addressDTO != null){
                customer.setBillingAddress(addressRepository.save(AddressAdaptor.fromDTO(addressDTO)));
                customerRepository.save(customer);
            }
            return AddressAdaptor.toDTO(customer.getBillingAddress());
        }
        else{
            throw new UserNotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerDTO verifyCustomer(Account account) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(account.getUsername());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (Utils.checkPassword(account.getPassword(), customer.getAccount().getPassword())) {
                return CustomerAdapter.toDTO(customer);
            } else {
                throw new UserBadRequestException("Wrong password");
            }
        } else {
            throw new UserNotFoundException("Employee not found");
        }
    }

    //findByUsername follow good coding practice
    @Override
    public CustomerDTO getCustomerByUsername(String username) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(username);
        if (customerOptional.isPresent()) {
            return CustomerAdapter.toDTO(customerOptional.get());
        } else {
            throw new UserNotFoundException("Customer not found");
        }
    }


}
