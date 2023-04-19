//package com.adminmodule.domain.seed;
//import com.adminmodule.domain.Role;
//import com.adminmodule.repository.RoleRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import static com.adminmodule.domain.Enum.RoleType.*;
//
//
//@Component
//public class RoleSeed implements CommandLineRunner {
//    private final RoleRepository roleRepository;
//    @Autowired
//    public RoleSeed(RoleRepository roleRepository){
//        this.roleRepository = roleRepository;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        loadData();
//    }
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
//        }
//    }
//}
