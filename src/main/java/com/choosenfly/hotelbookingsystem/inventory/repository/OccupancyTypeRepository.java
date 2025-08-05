package com.choosenfly.hotelbookingsystem.inventory.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterOccupancyType;


public interface OccupancyTypeRepository extends JpaRepository<MasterOccupancyType, Long>{
	
	Page<MasterOccupancyType> findByNameContainingIgnoreCase(String search,Pageable pageable);

}
