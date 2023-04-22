package com.adminmodule.domain.seed;

import com.adminmodule.domain.*;
import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.repository.EmployeeRepository;
import com.adminmodule.repository.RoleRepository;
import com.adminmodule.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.adminmodule.domain.Enum.RoleType.*;

@Component
public class AdminSeed implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminSeed(EmployeeRepository employeeRepository,
                     AccountRepository accountRepository,
                     RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (roleRepository.count() == 0) {
            Role data1 = new Role(ADMIN);

            roleRepository.save(data1);


        }

        if (employeeRepository.count() == 0) {
            String password = Utils.encodePassword("Aa@12345");
            String phone = "1234567890";
            Role roleA = roleRepository.findByRoleType(RoleType.ADMIN);

            Employee nav = new Employee("Navruzbek", "A", "nav@gmail.com", phone);
            Account account2 = new Account(nav.getEmail(), password);
            account2.setRoles(Set.of(roleA));
            nav.setAccount(accountRepository.save(account2));
            employeeRepository.save(nav);
        }
    }
}
