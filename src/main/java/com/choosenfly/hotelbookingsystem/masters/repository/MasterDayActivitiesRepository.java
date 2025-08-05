package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterDayActivities;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

public interface MasterDayActivitiesRepository  extends JpaRepository<MasterDayActivities, Long>{

	Page<MasterDayActivities> findByActivityNameContainingIgnoreCase(String name, Pageable pageable); 
}
