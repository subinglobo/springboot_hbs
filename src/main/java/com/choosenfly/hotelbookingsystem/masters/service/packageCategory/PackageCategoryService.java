package com.choosenfly.hotelbookingsystem.masters.service.packageCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterPackageCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPackageCategory;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterAgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterPackageCategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PackageCategoryService implements PackageCategoryServiceInterface{
	
	private final MasterPackageCategoryRepository packageCategoryRepository;
	
	@Autowired
	public PackageCategoryService(MasterPackageCategoryRepository packageCategoryRepository) {
		this.packageCategoryRepository = packageCategoryRepository;
		
	}

	@Override
	@Transactional
	public Long saveMasterPackageCategory(@Valid MasterPackageCategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		MasterPackageCategory entity = new MasterPackageCategory();
		entity.setName(categoryDTO.getName());
		entity.setCode(categoryDTO.getCode());
		MasterPackageCategory save = packageCategoryRepository.save(entity);
		Long pacCatId = save.getPackageCategoryId();
		if(pacCatId != 0) {
			return pacCatId;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterPackageCategoryDTO getPackageCategoryDetailsById(Long id) {
		// TODO Auto-generated method stub
		MasterPackageCategory Category = packageCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("package category not found Exception:"+ id));
		
		if(Category.getPackageCategoryId() != null) {
			
			MasterPackageCategoryDTO masterPacCatDTO = new MasterPackageCategoryDTO();
			masterPacCatDTO.setPackageCategoryId(Category.getPackageCategoryId());
			masterPacCatDTO.setName(Category.getName());
			masterPacCatDTO.setCode(Category.getCode());
			return masterPacCatDTO;
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterPackageCategoryDTO editpackageCategory(Long id, @Valid MasterPackageCategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		MasterPackageCategory Category = packageCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("package category not found Exception:"+ id));
		
		Category.setName(categoryDTO.getName());
		Category.setCode(categoryDTO.getCode());

		MasterPackageCategory	save = packageCategoryRepository.save(Category);
		 
		MasterPackageCategoryDTO masterPacCatDTO = new MasterPackageCategoryDTO();
		masterPacCatDTO.setPackageCategoryId(save.getPackageCategoryId());
		masterPacCatDTO.setName(save.getName());
		masterPacCatDTO.setCode(save.getCode());
		return masterPacCatDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deletepackageCategory(Long id) {
		// TODO Auto-generated method stub
		MasterPackageCategory Category = packageCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("package category not found Exception:"+ id));
		
		
		packageCategoryRepository.delete(Category);
		
		return ResponseEntity.ok("package category with id " + id + " deleted successfully");
	}

	
}
