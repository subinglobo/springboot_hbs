package com.choosenfly.hotelbookingsystem.auth.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    // To fetch user type by name (if needed)
    Optional<UserType> findByTypeNameIgnoreCase(String typeName);
}