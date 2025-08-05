package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterHotelCategory;

@Repository
public interface MasterHotelCategoryRepository extends JpaRepository<MasterHotelCategory, Long> {

}
