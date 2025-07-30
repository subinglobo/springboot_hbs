package com.choosenfly.hotelbookingsystem.service.masters.packageType;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPackageCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPackageType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterPackageCategoryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterPackageTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
public class PackageTypeService implements PackageTypeServiceInterface{
	
	private final MasterPackageTypeRepository masterPackageTypeRepository;
	
	@Autowired
	public PackageTypeService(MasterPackageTypeRepository masterPackageTypeRepository) {
		this.masterPackageTypeRepository = masterPackageTypeRepository;
		
	}

	@Override
	@Transactional
	public Long saveMasterPackageType(@Valid MasterPackageTypeDTO typeDTO) {
		// TODO Auto-generated method stub
		if(typeDTO == null) {
			throw new IllegalArgumentException("MasterPackageTypeDTO cannot be null");
		}
		MasterPackageType entity = new MasterPackageType();
		entity.setName(typeDTO.getName());
		entity.setCode(typeDTO.getCode());
		MasterPackageType save = masterPackageTypeRepository.save(entity);
		Long pacTypeId = save.getPackageTypeId();
		if(pacTypeId != 0) {
			return pacTypeId;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterPackageTypeDTO getPackageTypeDetailsById(Long id) {
		// TODO Auto-generated method stub
		MasterPackageType type = masterPackageTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("package type not found Exception:"+ id));
		
		if(type.getPackageTypeId() != null) {
			
			MasterPackageTypeDTO masterPacTypeDTO = new MasterPackageTypeDTO();
			masterPacTypeDTO.setPackageTypeId(type.getPackageTypeId());
			masterPacTypeDTO.setName(type.getName());
			masterPacTypeDTO.setCode(type.getCode());
			return masterPacTypeDTO;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterPackageTypeDTO editpackageType(Long id, @Valid MasterPackageTypeDTO typeDTO) {
		// TODO Auto-generated method stub
		MasterPackageType type = masterPackageTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("package type not found Exception:"+ id));
		
		type.setName(typeDTO.getName());
		type.setCode(typeDTO.getCode());

		MasterPackageType	save = masterPackageTypeRepository.save(type);
		 
		MasterPackageTypeDTO masterPacTypeDTO = new MasterPackageTypeDTO();
		masterPacTypeDTO.setPackageTypeId(save.getPackageTypeId());;
		masterPacTypeDTO.setName(save.getName());
		masterPacTypeDTO.setCode(save.getCode());
		return masterPacTypeDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deletepackageType(Long id) {
		// TODO Auto-generated method stub
		MasterPackageType type = masterPackageTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("package type not found Exception:"+ id));
		
		
		masterPackageTypeRepository.delete(type);
		
		return ResponseEntity.ok("package type with id " + id + " deleted successfully");
	}

}
