package com.adminmodule.utils;

import com.adminmodule.domain.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class Utils {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean checkPassword(String password, String encodedPassword){
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }

    public static Set<Role> addRoles(Role role){
        Set<Role> roles =  new HashSet<>();
        roles.add(role);
        return roles;
    }
}
