package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterHotelCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterHotelCategory;

@Repository
public interface MasterHotelCategoryRepository extends JpaRepository<MasterHotelCategory, Long> {

	Page<MasterHotelCategory> findByNameContainingIgnoreCase(String search, Pageable pageable);


}
