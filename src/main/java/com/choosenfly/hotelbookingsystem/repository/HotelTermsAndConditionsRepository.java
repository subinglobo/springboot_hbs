package com.choosenfly.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelTermsAndConditions;

@Repository
public interface HotelTermsAndConditionsRepository extends JpaRepository<HotelTermsAndConditions, Long>{

}
