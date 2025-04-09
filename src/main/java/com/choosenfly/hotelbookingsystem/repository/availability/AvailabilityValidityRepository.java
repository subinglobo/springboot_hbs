package com.choosenfly.hotelbookingsystem.repository.availability;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.availability.AvailabilityValidity;

public interface AvailabilityValidityRepository extends JpaRepository<AvailabilityValidity, Long> {

}
