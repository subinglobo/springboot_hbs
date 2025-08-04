package com.choosenfly.hotelbookingsystem.auth.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.auth.enitities.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Optional: Find by role name if needed
    Role findByRoleName(String roleName);
}