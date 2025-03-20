package com.choosenfly.hotelbookingsystem.repository.occupancy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.repository.master.MasterOccupancyType;

public interface OccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{

}
