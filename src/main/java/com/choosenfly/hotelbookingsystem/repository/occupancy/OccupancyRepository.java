package com.choosenfly.hotelbookingsystem.repository.occupancy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.occupancy.HotelOccupancy;

public interface OccupancyRepository extends JpaRepository<HotelOccupancy, Long> {

}
