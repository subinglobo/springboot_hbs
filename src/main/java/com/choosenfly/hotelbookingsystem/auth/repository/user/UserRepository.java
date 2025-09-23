package com.choosenfly.hotelbookingsystem.auth.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

	Optional<UserAccount> findByUsername(String username);

	@Query(value = "SELECT ua.id FROM user_accounts ua WHERE ua.user_id = :userId", nativeQuery = true)
	Long fetchUserAccountId(@Param("userId") Long userId);
}
