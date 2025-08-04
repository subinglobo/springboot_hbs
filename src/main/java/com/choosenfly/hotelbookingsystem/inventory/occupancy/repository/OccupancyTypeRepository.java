package com.choosenfly.hotelbookingsystem.inventory.occupancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.master.repository.MasterOccupancyType;

public interface OccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{

}
