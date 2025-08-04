package com.choosenfly.hotelbookingsystem.masters.designation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.master.entities.MasterDesignation;
import com.choosenfly.hotelbookingsystem.master.repository.MasterDesignationRepository;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterDesignationDTO;

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

}
