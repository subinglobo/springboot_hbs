package com.choosenfly.hotelbookingsystem.registration.cab.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabProvider;

public interface CabProviderRepository extends JpaRepository<CabProvider, Long> {

    // Search by providerName OR contactPerson (case-insensitive)
    Page<CabProvider> findByProviderNameContainingIgnoreCaseOrContactPersonContainingIgnoreCase(
            String providerName, String contactPerson, org.springframework.data.domain.Pageable pageable);
}