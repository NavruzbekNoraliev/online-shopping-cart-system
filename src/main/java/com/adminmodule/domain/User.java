package com.adminmodule.domain;

import jakarta.persistence.*;
import lombok.*;


import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new LinkedHashSet<>();
}
