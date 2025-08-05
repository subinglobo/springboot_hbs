package com.choosenfly.hotelbookingsystem.masters.service.dayActivities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterDayActivitiesDTO;

import jakarta.validation.Valid;

public interface DayActivitiesServiceInterface {

	Long saveDayActivities(@Valid MasterDayActivitiesDTO activitiesDTO);

	MasterDayActivitiesDTO getDayActivitiesById(Long id);

	MasterDayActivitiesDTO editDayActivities(Long id, @Valid MasterDayActivitiesDTO activitiesDTO);

	ResponseEntity<String> deleteDayActivities(Long id);

	Page<MasterDayActivitiesDTO> getAllDayActivities(Pageable pageable, String search);



}
