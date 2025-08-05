package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelAvailability;

public interface AvailabilityRepository extends JpaRepository<HotelAvailability, Long> {

}
