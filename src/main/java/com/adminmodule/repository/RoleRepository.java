package com.adminmodule.repository;

import com.adminmodule.domain.Enum.RoleType;
import com.adminmodule.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {



    Role findByRoleType(RoleType roleType);
}
