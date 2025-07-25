package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterItenaryDetails;

public interface MasterItenaryDetailsRepository extends JpaRepository<MasterItenaryDetails, Long>{

	
}
