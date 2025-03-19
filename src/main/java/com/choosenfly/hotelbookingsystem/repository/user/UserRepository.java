package com.choosenfly.hotelbookingsystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.user.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>{

}
