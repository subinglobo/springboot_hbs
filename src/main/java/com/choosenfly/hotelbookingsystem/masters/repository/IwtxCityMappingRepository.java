package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.masters.entities.ApiCityMapping;

public interface IwtxCityMappingRepository extends JpaRepository<ApiCityMapping, Long> {

	Page<ApiCityMapping> findByApiProviderContainingIgnoreCase(String search, Pageable pageable);


}
