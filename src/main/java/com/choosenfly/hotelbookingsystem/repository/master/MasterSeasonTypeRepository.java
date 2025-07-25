package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterSeasonType;

public interface MasterSeasonTypeRepository extends JpaRepository<MasterSeasonType , Long> {

	Page<MasterSeasonType> findByNameContainingIgnoreCase(String search, Pageable pageable);

}
