package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterOccupancyType;

@Repository
public interface MasterOccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{

}
