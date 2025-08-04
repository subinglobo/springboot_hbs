package com.choosenfly.hotelbookingsystem.inventory.availability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.availability.entities.HotelAvailability;

public interface AvailabilityRepository extends JpaRepository<HotelAvailability, Long> {

}
