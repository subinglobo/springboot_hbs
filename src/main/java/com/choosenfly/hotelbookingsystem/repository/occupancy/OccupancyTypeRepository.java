package com.choosenfly.hotelbookingsystem.repository.occupancy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterOccupancyType;

@Repository
public interface OccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{

	Page<MasterOccupancyType> findByNameContainingIgnoreCase(String search, Pageable pageable);

}
