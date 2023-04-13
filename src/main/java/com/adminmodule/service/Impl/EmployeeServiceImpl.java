package com.adminmodule.service.Impl;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Employee;
import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.domain.Role;
import com.adminmodule.exceptionResponse.userException.UserBadRequestException;
import com.adminmodule.exceptionResponse.userException.UserNotFoundException;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.repository.EmployeeRepository;
import com.adminmodule.repository.RoleRepository;
import com.adminmodule.service.EmployeeService;
import com.adminmodule.service.dto.EmployeeAdapter;
import com.adminmodule.service.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               RoleRepository roleRepository,
                               AccountRepository accountRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()){
            return EmployeeAdapter.toDTO(employee.get());
        }else{
            throw new UserNotFoundException("Employee not found");
        }
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employeeDTO.getEmail());
        if(employeeOptional.isPresent()){
            throw new UserBadRequestException("Employee already exists");
        }
        Employee employee = EmployeeAdapter.fromDTO(employeeDTO);

        Role role = roleRepository.findByRoleType(RoleType.ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        employee.getAccount().setRoles(roles);

        employee.getAccount().setPassword(this.passwordEncoder.encode(employee.getAccount().getPassword()));
        employee.getAccount().setUsername(employee.getEmail());

        Account account = accountRepository.save(employee.getAccount());
        employee.setAccount(account);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeAdapter.toDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setPhone(employeeDTO.getPhone());
            Employee savedEmployee = employeeRepository.save(employee);
            return EmployeeAdapter.toDTO(savedEmployee);
        }else{
            throw new UserNotFoundException("Employee not found");
        }

    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
