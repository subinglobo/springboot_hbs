package com.choosenfly.hotelbookingsystem.inventory.availability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.availability.entities.AvailabilityValidity;

public interface AvailabilityValidityRepository extends JpaRepository<AvailabilityValidity, Long> {

}
