package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterTermsAndCondition;
import com.choosenfly.hotelbookingsystem.entities.master.MasterVisaInformation;

@Repository
public interface MasterTermsAndConditionRepository extends JpaRepository<MasterTermsAndCondition, Long>{

	Page<MasterTermsAndCondition> findByTermsCodeContainingIgnoreCase(String name, Pageable pageable); 
}
