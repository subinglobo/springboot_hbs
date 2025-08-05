package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;

public interface OccupancyRepository extends JpaRepository<HotelOccupancy, Long> {

}
