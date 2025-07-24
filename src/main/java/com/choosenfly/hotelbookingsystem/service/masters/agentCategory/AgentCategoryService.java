package com.choosenfly.hotelbookingsystem.service.masters.agentCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.repository.master.MasterAgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterBankRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AgentCategoryService implements AgentCategoryServiceInterface{
	
	private final MasterAgentCategoryRepository agentCategoryRepository;
	
	@Autowired
	public AgentCategoryService(MasterAgentCategoryRepository agentCategoryRepository) {
		this.agentCategoryRepository = agentCategoryRepository;
		
	}

	@Override
	@Transactional
	public Long saveMasterAgentCategory(@Valid MasterAgentCategoryDTO agentCategoryDTO) {
		// TODO Auto-generated method stub
		MasterAgentCategory entity = new MasterAgentCategory();
		entity.setName(agentCategoryDTO.getName());
		entity.setIsDeleted(false);
		MasterAgentCategory save = agentCategoryRepository.save(entity);
		Long agentCatId = save.getCategoryId();
		if(agentCatId != 0) {
			return agentCatId;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterAgentCategoryDTO getAgentCategoryDetailsById(Long id) {
		// TODO Auto-generated method stub
		
		MasterAgentCategory agentCategory = agentCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("agent category not found Exception:"+ id));
		
		if(agentCategory.getCategoryId() != null) {
			
			MasterAgentCategoryDTO masteragentCatDTO = new MasterAgentCategoryDTO();
			masteragentCatDTO.setAgentCategoryId(agentCategory.getCategoryId());
			masteragentCatDTO.setName(agentCategory.getName());
			masteragentCatDTO.setIsDeleted(agentCategory.getIsDeleted());
			return masteragentCatDTO;
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterAgentCategoryDTO editAgentCategory(Long id, @Valid MasterAgentCategoryDTO agentCategoryDTO) {
		// TODO Auto-generated method stub
		MasterAgentCategory agentCategory = agentCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("agent category not found Exception:"+ id));
		
		agentCategory.setName(agentCategoryDTO.getName());

		MasterAgentCategory	save = agentCategoryRepository.save(agentCategory);
		 
		MasterAgentCategoryDTO masterAgentCatDTO = new MasterAgentCategoryDTO();
		masterAgentCatDTO.setAgentCategoryId(save.getCategoryId());
		masterAgentCatDTO.setName(save.getName());
		masterAgentCatDTO.setIsDeleted(save.getIsDeleted());
		return masterAgentCatDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteAgentCategory(Long id) {
		// TODO Auto-generated method stub
		MasterAgentCategory agentCategory = agentCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("agent category not found Exception:"+ id));
		
		
		agentCategoryRepository.delete(agentCategory);
		
		return ResponseEntity.ok("Agent category with id " + id + " deleted successfully");
	
	}



}
