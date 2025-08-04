package com.choosenfly.hotelbookingsystem.inventory.occupancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.occupancy.entities.HotelOccupancy;

public interface OccupancyRepository extends JpaRepository<HotelOccupancy, Long> {

}
