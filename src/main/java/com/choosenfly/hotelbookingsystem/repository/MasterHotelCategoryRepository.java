package com.choosenfly.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelCategory;

@Repository
public interface MasterHotelCategoryRepository extends JpaRepository<MasterHotelCategory, Long> {

}
