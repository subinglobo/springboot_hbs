package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterItenaryDetails;
import com.choosenfly.hotelbookingsystem.entities.master.MasterVisaInformation;

public interface MasterItenaryDetailsRepository extends JpaRepository<MasterItenaryDetails, Long>{

	Page<MasterItenaryDetails> findByitineraryHeadingContainingIgnoreCase(String name, Pageable pageable); 
}
