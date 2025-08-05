package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterVisaInformation;

@Repository
public interface MasterVisaInformationRepository extends JpaRepository<MasterVisaInformation, Long>{

	Page<MasterVisaInformation> findByPassportCodeContainingIgnoreCase(String name, Pageable pageable); 
}
