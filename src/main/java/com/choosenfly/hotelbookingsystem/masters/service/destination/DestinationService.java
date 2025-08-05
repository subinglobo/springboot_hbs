package com.choosenfly.hotelbookingsystem.masters.service.destination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterPlaceDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterPlaceRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DestinationService implements DestinationServiceInterface {
	
	
	private final MasterPlaceRepository masterPlaceRepository;
	
	private final MasterCountryRepository masterCountryRepository;
	
	private final MasterStateRepository masterStateRepository;
	
	public DestinationService(MasterPlaceRepository masterPlaceRepository , MasterCountryRepository masterCountryRepository , 
			MasterStateRepository masterStateRepository ) {
		this.masterPlaceRepository = masterPlaceRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
	}

	@Override
	@Transactional
	public Long saveDestination(MasterPlaceDTO placeDTO) {
		// TODO Auto-generated method stub
		
		MasterPlace entity = new MasterPlace();
		
		MasterCountry countryEntity = 
				masterCountryRepository.findById(placeDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +placeDTO.getCountryId() )); 
		entity.setCountry(countryEntity);
		
		MasterState stateEntity = 
				masterStateRepository.findById(placeDTO.getStateId()).orElseThrow(() -> new EntityNotFoundException("State not found for id: " + placeDTO.getStateId()));
		entity.setState(stateEntity);
		
		
		entity.setName(placeDTO.getName());
		entity.setPlaceCode(placeDTO.getPlaceCode());
		entity.setIsDeleted(false);
		
		MasterPlace save = masterPlaceRepository.save(entity);
		if(save.getId() != 0) {
			return save.getId();
		}
		return null;
	}

	@Override
	@Transactional
	public MasterPlaceDTO getDestinationById(Long id) {
		// TODO Auto-generated method stub
		
		MasterPlace destinationEntity =
				masterPlaceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Destination not found for id :" + id));
		
		MasterPlaceDTO dto = new MasterPlaceDTO();
		dto.setCountryId(destinationEntity.getCountry().getId());
		dto.setStateId(destinationEntity.getState().getId());
		dto.setName(destinationEntity.getName());
		dto.setPlaceCode(destinationEntity.getPlaceCode());
		dto.setIsDeleted(destinationEntity.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public MasterPlaceDTO editDestination(Long id, MasterPlaceDTO placeDTO) {
		// TODO Auto-generated method stub
		
		MasterPlace destinationEntity =
				masterPlaceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Destination not found for id :" + id));
		
		MasterCountry countryEntity = 
				masterCountryRepository.findById(placeDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +placeDTO.getCountryId() )); 
		destinationEntity.setCountry(countryEntity);
		
		MasterState stateEntity = 
				masterStateRepository.findById(placeDTO.getStateId()).orElseThrow(() -> new EntityNotFoundException("State not found for id: " + placeDTO.getStateId()));
		destinationEntity.setState(stateEntity);
		
		destinationEntity.setName(placeDTO.getName());
		destinationEntity.setPlaceCode(placeDTO.getPlaceCode());
		destinationEntity.setIsDeleted(false);
		MasterPlace editSave = masterPlaceRepository.save(destinationEntity);
		
		MasterPlaceDTO dto = new MasterPlaceDTO();
		dto.setCountryId(editSave.getCountry().getId());
		dto.setStateId(editSave.getState().getId());
		dto.setName(editSave.getName());
		dto.setPlaceCode(editSave.getPlaceCode());
		dto.setIsDeleted(editSave.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteDestination(Long id) {
		// TODO Auto-generated method stub
		MasterPlace destinationEntity =
				masterPlaceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Destination not found for id :" + id));
		
		masterPlaceRepository.delete(destinationEntity);
		
		return ResponseEntity.ok("Destination with id " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterPlaceDTO> getAllDestination(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		Page<MasterPlace> placePage;

	    if (StringUtils.hasText(search)) {
	    	placePage = masterPlaceRepository.findByNameContainingIgnoreCase(search, pageable);
	    } else {
	    	placePage = masterPlaceRepository.findAll(pageable);
	    }

	    return placePage.map(place -> {
	    	MasterPlaceDTO dto = new MasterPlaceDTO();
	        dto.setId(place.getId());
	        dto.setCountryId(place.getCountry().getId());
	        dto.setStateId(place.getState().getId());
	        dto.setName(place.getName());
	        dto.setPlaceCode(place.getPlaceCode());
	        dto.setIsDeleted(place.getIsDeleted());

	        if (place.getCountry() != null) {
	            dto.setCountryId(place.getCountry().getId());
	            dto.setCountry(place.getCountry().getName());
	        }

	        return dto;
	    });
	}

}
