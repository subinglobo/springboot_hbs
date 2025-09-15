package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;

public interface MasterMarketTypeRepository extends JpaRepository<MasterMarketType, Long>{

	Page<MasterMarketType> findByNameStartingWithIgnoreCase(String searchTerm, Pageable pageable);

}
