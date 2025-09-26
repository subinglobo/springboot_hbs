package com.choosenfly.hotelbookingsystem.registration.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.registration.cab.entities.Cab;
@Repository
public interface CabRespository extends JpaRepository<Cab, Long>{

	
}
