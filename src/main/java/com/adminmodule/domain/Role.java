package com.adminmodule.domain;

import com.adminmodule.domain.Enum.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "role_type")
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}
