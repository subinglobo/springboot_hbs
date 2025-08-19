package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterCurrency;

@Repository
public interface MasterCurrencyRepository extends JpaRepository<MasterCurrency, Long> {

	Page<MasterCurrency> findByNameStartingWithIgnoreCase(String searchTerm, Pageable pageable);

}
