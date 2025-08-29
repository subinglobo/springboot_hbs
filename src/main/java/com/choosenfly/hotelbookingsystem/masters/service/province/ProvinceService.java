package com.choosenfly.hotelbookingsystem.masters.service.province;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ProvinceService implements ProvinceServiceInterface {

	private final MasterStateRepository masterStateRepository;

	private final MasterCountryRepository masterCountryRepository;

	public ProvinceService(MasterStateRepository masterStateRepository,
			MasterCountryRepository masterCountryRepository) {
		this.masterStateRepository = masterStateRepository;
		this.masterCountryRepository = masterCountryRepository;
	}

	@Override
	@Transactional
	public Long saveProvince(MasterStateDTO stateDTO) {
		// TODO Auto-generated method stub

		MasterState entity = new MasterState();

		MasterCountry countryEntity = 
				masterCountryRepository.findById(stateDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +stateDTO.getCountryId() )); 
		entity.setCountry(countryEntity);
		entity.setName(stateDTO.getStateName());
		entity.setStateCode(stateDTO.getStateCode());
		entity.setIsDeleted(false);
		MasterState save = masterStateRepository.save(entity);
		Long stateId = save.getId();
		if (stateId != 0) {
			return stateId;
		}
		return null;
	}

	@Override
	public MasterStateDTO getProvinveById(Long id) {
		// TODO Auto-generated method stub

		MasterState provinceData = masterStateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + id));

		if (provinceData.getId() != null) {
			
			MasterStateDTO masterStateDTO = new MasterStateDTO();
			
			masterStateDTO.setId(provinceData.getId());
			masterStateDTO.setStateName(provinceData.getName());
			masterStateDTO.setStateCode(provinceData.getStateCode());

			MasterCountry masterCountry = new MasterCountry();
			masterStateDTO.setCountryId(provinceData.getCountry().getId());
			masterStateDTO.setCountry(provinceData.getCountry().getName());
			masterStateDTO.setIsDeleted(provinceData.getIsDeleted());
			return masterStateDTO;
		}
		return null;
	}

	@Override
	public MasterStateDTO editProvince(Long id, @Valid MasterStateDTO stateDTO) {
		// TODO Auto-generated method stub

		MasterState provinceData = masterStateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + id));

		provinceData.setName(stateDTO.getStateName());
		provinceData.setStateCode(stateDTO.getStateCode());
		
		MasterCountry countryEntity = 
				masterCountryRepository.findById(stateDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +stateDTO.getCountryId() )); 
		
		provinceData.setCountry(countryEntity);
		provinceData.setIsDeleted(false);
		MasterState save = masterStateRepository.save(provinceData);
	
		MasterStateDTO masterStateDTO = new MasterStateDTO();
		masterStateDTO.setStateName(save.getName());
		masterStateDTO.setStateCode(save.getStateCode());

		MasterCountry country = save.getCountry();
		masterStateDTO.setCountryId(country.getId());
		masterStateDTO.setCountry(country.getName());
		masterStateDTO.setIsDeleted(save.getIsDeleted());

		return masterStateDTO;
	}

	@Override
	public ResponseEntity<String> deleteProvince(Long id) {
		// TODO Auto-generated method stub

		MasterState provinceData = masterStateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + id));

		masterStateRepository.delete(provinceData);

		return ResponseEntity.ok("Province with id " + id + " deleted successfully");
	}

	@Transactional(readOnly = true)
	public Page<MasterStateDTO> getAllProvince(Pageable pageable, String search) {

	    Page<MasterState> statePage;

	    if (StringUtils.hasText(search)) {
	        statePage = masterStateRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(search, pageable);
	    } else {
	        statePage = masterStateRepository.findAll(pageable);
	    }

	    return statePage.map(state -> {
	        MasterStateDTO dto = new MasterStateDTO();  
	        dto.setId(state.getId());
	        dto.setStateName(state.getName());
	        dto.setStateCode(state.getStateCode());
	        dto.setIsDeleted(state.getIsDeleted());

	        if (state.getCountry() != null) {
	            dto.setCountryId(state.getCountry().getId());
	            dto.setCountry(state.getCountry().getName());
	        }

	        return dto;
	    });
	}

	@Override
	public List<MasterStateDTO> getProvinveByCountryId(Long countryId) {
		// TODO Auto-generated method stub
		
		MasterCountry countryEntity =  masterCountryRepository.findById(countryId)
        .orElseThrow(() -> new EntityNotFoundException("Country not found for id: " + countryId));
		
		List<MasterState> stateEntity =  masterStateRepository.findByCountryId(countryId);
		
		 List<MasterStateDTO> collect = stateEntity.stream()
	            .map(entity -> {
	                MasterStateDTO dto = new MasterStateDTO();
	                dto.setId(entity.getId());
	                dto.setStateName(entity.getName());
	                dto.setStateCode(entity.getStateCode());
	                dto.setCountryId(entity.getCountry().getId());
	                dto.setCountry(entity.getCountry().getName());
	                return dto;
	            })
	            .collect(Collectors.toList());
		 
		 return collect;
	}


}
