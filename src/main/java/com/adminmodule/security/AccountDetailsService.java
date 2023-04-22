package com.adminmodule.security;

import com.adminmodule.domain.Account;
import com.adminmodule.domain.Role;
import com.adminmodule.repository.AccountRepository;
import com.adminmodule.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    //for testing purpose
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : account.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleType().toString()));
        }
        log.info("Account: " + account.getEmail() + " " + account.getPassword());
        return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
