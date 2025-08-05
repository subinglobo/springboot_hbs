package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

@Repository
public interface MasterStateRepository extends JpaRepository<MasterState, Long> {

	Page<MasterState> findByNameContainingIgnoreCase(String search, Pageable pageable);


}
