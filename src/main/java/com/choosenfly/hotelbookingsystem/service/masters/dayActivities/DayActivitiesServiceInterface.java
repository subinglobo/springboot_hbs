package com.choosenfly.hotelbookingsystem.service.masters.dayActivities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterDayActivitiesDTO;

import jakarta.validation.Valid;

public interface DayActivitiesServiceInterface {

	Long saveDayActivities(@Valid MasterDayActivitiesDTO activitiesDTO);

	MasterDayActivitiesDTO getDayActivitiesById(Long id);

	MasterDayActivitiesDTO editDayActivities(Long id, @Valid MasterDayActivitiesDTO activitiesDTO);

	ResponseEntity<String> deleteDayActivities(Long id);

	Page<MasterDayActivitiesDTO> getAllDayActivities(Pageable pageable, String search);



}
