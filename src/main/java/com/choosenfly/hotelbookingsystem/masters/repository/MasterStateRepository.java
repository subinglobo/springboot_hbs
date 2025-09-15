package com.choosenfly.hotelbookingsystem.masters.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

@Repository
public interface MasterStateRepository extends JpaRepository<MasterState, Long> {

	@Query("SELECT m FROM MasterState m WHERE m.country.id = :countryId AND m.isDeleted = false")
	List<MasterState> findByCountryId(Long countryId);

	Page<MasterState> findByNameContainingIgnoreCaseAndIsDeletedFalse(String search, Pageable pageable);


}
