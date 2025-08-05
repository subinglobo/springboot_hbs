package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterHotelType;

@Repository
public interface MasterHotelTypeRepository extends JpaRepository<MasterHotelType, Long>{

	Page<MasterHotelType> findByNameContainingIgnoreCase(String search, Pageable pageable);

}
