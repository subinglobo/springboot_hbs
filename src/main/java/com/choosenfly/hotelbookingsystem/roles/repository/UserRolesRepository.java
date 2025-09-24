package com.choosenfly.hotelbookingsystem.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.auth.enitities.role.Role;

public interface UserRolesRepository extends JpaRepository<Role, Long>{

}
