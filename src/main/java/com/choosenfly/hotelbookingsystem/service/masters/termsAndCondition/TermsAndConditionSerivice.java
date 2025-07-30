package com.choosenfly.hotelbookingsystem.service.masters.termsAndCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterDayActivitiesDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterTermsAndConditionDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDayActivities;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;
import com.choosenfly.hotelbookingsystem.entities.master.MasterTermsAndCondition;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterDayActivitiesRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterTermsAndConditionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
public class TermsAndConditionSerivice implements TermsAndConditionInterface{
	
	private final MasterTermsAndConditionRepository termsAndConditionRepository;

	private final MasterCountryRepository masterCountryRepository;
	
	private final MasterStateRepository masterStateRepository;

	public TermsAndConditionSerivice(MasterTermsAndConditionRepository termsAndConditionRepository,
			MasterCountryRepository masterCountryRepository,MasterStateRepository masterStateRepository) {
		this.termsAndConditionRepository = termsAndConditionRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
	}

	@Override
	@Transactional
	public Long saveTermsAndCondition(@Valid MasterTermsAndConditionDTO termsDTO) {
		// TODO Auto-generated method stub
		
		MasterTermsAndCondition entity = new MasterTermsAndCondition();

		MasterCountry countryEntity = 
				masterCountryRepository.findById(termsDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +termsDTO.getCountryId() ));
		entity.setCountry(countryEntity);
		
		MasterState stateEntity = 
				masterStateRepository.findById(termsDTO.getStateId()).orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + termsDTO.getStateId()));
		entity.setState(stateEntity);
		entity.setTagline(countryEntity.getName()+"-"+stateEntity.getName());
		entity.setDescription(termsDTO.getDescription());
		entity.setDescriptionType(termsDTO.getDescriptionType());
		entity.setTermsCode(termsDTO.getTermsCode());
		MasterTermsAndCondition save = termsAndConditionRepository.save(entity);
		Long termsId = save.getTermsAndConditionsId();
		if (termsId != 0) {
			return termsId;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterTermsAndConditionDTO getTermsAndConditionById(Long id) {
		// TODO Auto-generated method stub
		MasterTermsAndCondition termsData = 
				termsAndConditionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Day activity not found for id :" + id));

		if (termsData.getTermsAndConditionsId()!= null) {
			MasterTermsAndConditionDTO dto = new MasterTermsAndConditionDTO();
			dto.setCountryId(termsData.getCountry().getId());	
			dto.setDescription(termsData.getDescription());
			dto.setDescriptionType(termsData.getDescriptionType());
			dto.setStateId(termsData.getState().getId());
			dto.setTagline(termsData.getTagline());
			dto.setTermsAndConditionsId(termsData.getTermsAndConditionsId());
			dto.setTermsCode(termsData.getTermsCode());
			return dto;
		}
		return null;
	}

	@Override
	public MasterTermsAndConditionDTO editTermsAndCondition(Long id, @Valid MasterTermsAndConditionDTO termsDTO) {
		// TODO Auto-generated method stub
		MasterTermsAndCondition termsData = 
				termsAndConditionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Day activity not found for id :" + id));

		termsData.setDescription(termsDTO.getDescription());
		termsData.setDescriptionType(termsDTO.getDescriptionType());
		MasterCountry countryEntity = 
				masterCountryRepository.findById(termsDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +termsDTO.getCountryId() ));
		termsData.setCountry(countryEntity);
		MasterState stateEntity = 
				masterStateRepository.findById(termsDTO.getStateId()).orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + termsDTO.getStateId()));	
		termsData.setState(stateEntity);
		termsData.setTagline(countryEntity.getName()+"-"+stateEntity.getName());
		termsData.setTermsCode(termsDTO.getTermsCode());
		
		MasterTermsAndCondition save = termsAndConditionRepository.save(termsData);
	
		MasterTermsAndConditionDTO dto = new MasterTermsAndConditionDTO();
		
		dto.setCountryId(save.getCountry().getId());	
		dto.setDescription(save.getDescription());
		dto.setDescriptionType(save.getDescriptionType());
		dto.setStateId(save.getState().getId());
		dto.setTagline(save.getTagline());
		dto.setTermsAndConditionsId(save.getTermsAndConditionsId());
		dto.setTermsCode(save.getTermsCode());
		
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteTermsAndCondition(Long id) {
		// TODO Auto-generated method stub
		MasterTermsAndCondition termsData = 
				termsAndConditionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Day activity not found for id :" + id));

		termsAndConditionRepository.delete(termsData);

		return ResponseEntity.ok("terms and condtion with id " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterTermsAndConditionDTO> getAllTermsAndCondition(Pageable pageable, String search) {
		// TODO Auto-generated method stub
	    Page<MasterTermsAndCondition> termsPage;

	    if (StringUtils.hasText(search)) {
	    	termsPage = termsAndConditionRepository.findByTermsCodeContainingIgnoreCase(search, pageable);
	    } else {
	    	termsPage = termsAndConditionRepository.findAll(pageable);
	    }

	    return termsPage.map(dayActivity -> {
	    	MasterTermsAndConditionDTO dto = new MasterTermsAndConditionDTO();
	    	
	    	dto.setCountryId(dayActivity.getCountry().getId());	
			dto.setDescription(dayActivity.getDescription());
			dto.setDescriptionType(dayActivity.getDescriptionType());
			dto.setStateId(dayActivity.getState().getId());
			dto.setTagline(dayActivity.getTagline());
			dto.setTermsAndConditionsId(dayActivity.getTermsAndConditionsId());
			dto.setTermsCode(dayActivity.getTermsCode());

	        return dto;
	    });
	
	}

}
