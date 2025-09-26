package com.choosenfly.hotelbookingsystem.registration.activity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityRate;

@Repository
public interface ActivityRateRepository extends JpaRepository<ActivityRate, Long>{

	Page<ActivityRate> findByActivityNameContainingIgnoreCase(String search, Pageable pageable);

}
