package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterOccupancyType;

@Repository
public interface MasterOccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{

}
