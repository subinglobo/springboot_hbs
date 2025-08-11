package com.choosenfly.hotelbookingsystem.auth.repository.token;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.auth.enitities.token.RefreshToken;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByTokenHash(String tokenHash);
    void deleteByUser(UserAccount user);
}