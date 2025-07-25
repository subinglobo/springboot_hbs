package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDayActivities;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;

public interface MasterDayActivitiesRepository  extends JpaRepository<MasterDayActivities, Long>{

	Page<MasterDayActivities> findByActivityNameContainingIgnoreCase(String name, Pageable pageable); 
}
