package com.choosenfly.hotelbookingsystem.masters.service.designation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterDesignationDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterPlaceDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterDesignation;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterDesignationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class DesignationService implements DesignationServiceInterface{
	
	private final MasterDesignationRepository masterDesignationRepository;
	
	@Autowired
	public DesignationService (MasterDesignationRepository masterDesignationRepository) {
		this.masterDesignationRepository = masterDesignationRepository;
	}

	@Override
	@Transactional
	public Long save(@Valid MasterDesignationDTO desigDTO) {
		// TODO Auto-generated method stub
		
	MasterDesignation entity = new	MasterDesignation();
	entity.setName(desigDTO.getName());
	entity.setIsDeleted(false);
	MasterDesignation save = masterDesignationRepository.save(entity);
	if(save.getDesignationId() != 0) {
		
		return save.getDesignationId();
		
	}
		
	return null;
	
	}

	@Override
	@Transactional
	public MasterDesignationDTO getDesigDetailsById(Long id) {
		// TODO Auto-generated method stub
		MasterDesignation designation =
				masterDesignationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Designation id not found :"+id));
		
		MasterDesignationDTO masterDesignationDTO = new MasterDesignationDTO();
		masterDesignationDTO.setDesignationId(designation.getDesignationId());
		masterDesignationDTO.setName(designation.getName());
		masterDesignationDTO.setIsDeleted(designation.getIsDeleted());
		
		return masterDesignationDTO;
	}

	@Override
	@Transactional
	public MasterDesignationDTO editDesignation(Long id, @Valid MasterDesignationDTO desigDTO) {
		// TODO Auto-generated method stub
		
		MasterDesignation designation =
				masterDesignationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Designation id not found :"+id));
		
		designation.setName(desigDTO.getName());
		MasterDesignation save = masterDesignationRepository.save(designation);
		
		MasterDesignationDTO masterDesignationDTO = new MasterDesignationDTO();
		masterDesignationDTO.setDesignationId(save.getDesignationId());
		masterDesignationDTO.setIsDeleted(save.getIsDeleted());
		masterDesignationDTO.setName(save.getName());
		return masterDesignationDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteDesignation(Long id) {
		// TODO Auto-generated method stub
		MasterDesignation designation =
				masterDesignationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Designation id not found :"+id));
		
		masterDesignationRepository.delete(designation);
		
		return ResponseEntity.ok("Designation with id " + id + " deleted successfully");
	}

	@Override
	public Page<MasterDesignationDTO> getAllDesignation(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		Page<MasterDesignation> desigPage;

	    if (StringUtils.hasText(search)) {
	    	desigPage = masterDesignationRepository.findByNameContainingIgnoreCase(search, pageable);
	    } else {
	    	desigPage = masterDesignationRepository.findAll(pageable);
	    }

	    return desigPage.map(designation -> {
	    	MasterDesignationDTO dto = new MasterDesignationDTO();
	        dto.setDesignationId(designation.getDesignationId());
	        dto.setName(designation.getName());
	        dto.setIsDeleted(designation.getIsDeleted());
	       
	        return dto;
	    });
	}

}
