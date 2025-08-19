package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterContactType;

@Repository
public interface MasterContactTypeRepository extends JpaRepository<MasterContactType, Long>{

	Page<MasterContactType> findByNameStartingWithIgnoreCase(String searchTerm, Pageable pageable);

	

}
