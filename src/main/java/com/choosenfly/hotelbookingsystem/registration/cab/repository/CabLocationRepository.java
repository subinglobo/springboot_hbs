package com.choosenfly.hotelbookingsystem.registration.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabLocation;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabRates;

public interface CabLocationRepository extends JpaRepository<CabLocation, Long>{

}
