package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelTermsAndConditions;

@Repository
public interface HotelTermsAndConditionsRepository extends JpaRepository<HotelTermsAndConditions, Long>{

}
