package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterTermsAndCondition;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterVisaInformation;

@Repository
public interface MasterTermsAndConditionRepository extends JpaRepository<MasterTermsAndCondition, Long>{

	Page<MasterTermsAndCondition> findByTermsCodeContainingIgnoreCase(String name, Pageable pageable); 
}
