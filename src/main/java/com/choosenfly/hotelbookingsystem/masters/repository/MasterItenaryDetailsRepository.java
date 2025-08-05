package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterItenaryDetails;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterVisaInformation;

public interface MasterItenaryDetailsRepository extends JpaRepository<MasterItenaryDetails, Long>{

	Page<MasterItenaryDetails> findByitineraryHeadingContainingIgnoreCase(String name, Pageable pageable); 
}
