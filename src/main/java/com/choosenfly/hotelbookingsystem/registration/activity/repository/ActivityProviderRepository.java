package com.choosenfly.hotelbookingsystem.registration.activity.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityProvider;

@Repository
public interface ActivityProviderRepository extends JpaRepository<ActivityProvider, Long>{

	Page<ActivityProvider> findByProviderNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
			String search, String search2, String search3, org.springframework.data.domain.Pageable pageable);
}
