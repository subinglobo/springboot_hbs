package com.choosenfly.hotelbookingsystem.repository.availability;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.availability.HotelAvailability;

public interface AvailabilityRepository extends JpaRepository<HotelAvailability, Long> {

}
