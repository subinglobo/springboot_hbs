package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.masters.repository.MasterOccupancyType;

public interface OccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{

}
