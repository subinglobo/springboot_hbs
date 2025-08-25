package com.choosenfly.hotelbookingsystem.masters.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;

@Repository
public interface MasterPlaceRepository extends JpaRepository<MasterPlace, Long> {

	Page<MasterPlace> findByNameContainingIgnoreCase(String search, Pageable pageable);

	@Query("SELECT m FROM MasterPlace m WHERE m.state.id = :stateId AND m.isDeleted = false")
	List<MasterPlace> findByStateId(Long stateId);

	@Query("SELECT m FROM MasterPlace m WHERE m.country.id = :countryId AND m.isDeleted = false")
	List<MasterPlace> findByCountryId(Long countryId);

	Page<MasterPlace> findByNameStartingWithIgnoreCase(String search, Pageable pageable);

	// For search with country filter
	@Query("SELECT m FROM MasterPlace m WHERE m.country.id = :countryId AND LOWER(m.name) LIKE LOWER(CONCAT(:searchTerm, '%')) AND m.isDeleted = false")
	List<MasterPlace> findByNameStartingWithIgnoreCase(@Param("searchTerm") String searchTerm, @Param("countryId") Long countryId);

	
	

}
