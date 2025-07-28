package com.choosenfly.hotelbookingsystem.service.masters.dayActivities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterDayActivitiesDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDayActivities;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterDayActivitiesRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterStateRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class DayActivitiesService implements DayActivitiesServiceInterface{
	
	private final MasterDayActivitiesRepository activitiesRepository;

	private final MasterCountryRepository masterCountryRepository;
	
	private final MasterStateRepository masterStateRepository;

	public DayActivitiesService(MasterDayActivitiesRepository activitiesRepository,
			MasterCountryRepository masterCountryRepository,MasterStateRepository masterStateRepository) {
		this.activitiesRepository = activitiesRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
	}


	@Override
	@Transactional
	public Long saveDayActivities(@Valid MasterDayActivitiesDTO activitiesDTO) {
		// TODO Auto-generated method stub

		MasterDayActivities entity = new MasterDayActivities();

			MasterCountry countryEntity = 
					masterCountryRepository.findById(activitiesDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +activitiesDTO.getCountryId() ));
			entity.setCountry(countryEntity);
			
			MasterState stateEntity = 
					masterStateRepository.findById(activitiesDTO.getStateId()).orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + activitiesDTO.getStateId()));
			entity.setState(stateEntity);
			entity.setActivityCode(activitiesDTO.getActivityCode());
			entity.setActivityName(activitiesDTO.getActivityName());
			entity.setDescription(activitiesDTO.getDescription());
			MasterDayActivities save = activitiesRepository.save(entity);
			Long dayActivityId = save.getDayActivityId();
			if (dayActivityId != 0) {
				return dayActivityId;
			}
			return null;
		
	}

	@Override
	@Transactional
	public MasterDayActivitiesDTO getDayActivitiesById(Long id) {
		// TODO Auto-generated method stub
		MasterDayActivities ActivitesData = 
				activitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Day activity not found for id :" + id));

		if (ActivitesData.getDayActivityId() != null) {
			
			MasterDayActivitiesDTO dto = new MasterDayActivitiesDTO();
			
			dto.setDayActivityId(ActivitesData.getDayActivityId());
			dto.setActivityName(ActivitesData.getActivityName());
			dto.setActivityCode(ActivitesData.getActivityCode());
			dto.setDescription(ActivitesData.getDescription());
			dto.setCountryId(ActivitesData.getCountry().getId());
			dto.setStateId(ActivitesData.getState().getId());
			return dto;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterDayActivitiesDTO editDayActivities(Long id, @Valid MasterDayActivitiesDTO activitiesDTO) {
		// TODO Auto-generated method stub
		
		MasterDayActivities ActivitesData = 
				activitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Day activity not found for id :" + id));

		ActivitesData.setActivityName(activitiesDTO.getActivityName());
		ActivitesData.setActivityCode(activitiesDTO.getActivityCode());
		ActivitesData.setDescription(activitiesDTO.getDescription());

		MasterCountry countryEntity = 
				masterCountryRepository.findById(activitiesDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +activitiesDTO.getCountryId() ));
		ActivitesData.setCountry(countryEntity);

		MasterState stateEntity = 
				masterStateRepository.findById(activitiesDTO.getStateId()).orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + activitiesDTO.getStateId()));	
		ActivitesData.setState(stateEntity);
		MasterDayActivities save = activitiesRepository.save(ActivitesData);
	
		MasterDayActivitiesDTO dto = new MasterDayActivitiesDTO();
		
		dto.setDayActivityId(save.getDayActivityId());
		dto.setActivityName(save.getActivityName());
		dto.setActivityCode(save.getActivityCode());
		dto.setDescription(save.getDescription());
		dto.setCountryId(save.getCountry().getId());
		dto.setStateId(save.getState().getId());
		
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteDayActivities(Long id) {
		// TODO Auto-generated method stub
		MasterDayActivities ActivitesData = 
				activitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Day activity not found for id :" + id));

		activitiesRepository.delete(ActivitesData);

		return ResponseEntity.ok("Day activities with id " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterDayActivitiesDTO> getAllDayActivities(Pageable pageable, String search) {
		// TODO Auto-generated method stub
	    Page<MasterDayActivities> dayActivityPage;

	    if (StringUtils.hasText(search)) {
	    	dayActivityPage = activitiesRepository.findByActivityNameContainingIgnoreCase(search, pageable);
	    } else {
	    	dayActivityPage = activitiesRepository.findAll(pageable);
	    }

	    return dayActivityPage.map(dayActivity -> {
	    	MasterDayActivitiesDTO dto = new MasterDayActivitiesDTO();
	    	dto.setDayActivityId(dayActivity.getDayActivityId());
	    	dto.setActivityName(dayActivity.getActivityName());
	    	dto.setActivityCode(dayActivity.getActivityCode());
	    	dto.setDescription(dayActivity.getDescription());
	    	if(dayActivity.getCountry()!=null) {
		    	dto.setCountryId(dayActivity.getCountry().getId());
	    	}
	    	if(dayActivity.getState()!=null) {
		    	dto.setStateId(dayActivity.getState().getId());
	    	}

	        return dto;
	    });
	}

}
