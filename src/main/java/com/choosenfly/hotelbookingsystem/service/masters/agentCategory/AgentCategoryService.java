package com.choosenfly.hotelbookingsystem.service.masters.agentCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.globalhandler.EntityCreationException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterAgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterBankRepository;


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
		try {
			MasterAgentCategory save = agentCategoryRepository.save(entity);
			if (save == null || save.getCategoryId() == null || save.getCategoryId() == 0) {
	            throw new EntityCreationException("Failed to persist Meal Plan.");
	        }

	        return save.getCategoryId();
			
		} catch (IllegalArgumentException e) {
	        throw new EntityCreationException("Entity cannot be null while saving Meal Plan.", e);
	    } catch (OptimisticLockingFailureException e) {
	        throw new EntityCreationException("Meal Plan version conflict occurred during save.", e);
	    } catch (Exception e) {
	        throw new EntityCreationException("Unexpected error while saving Meal Plan: " + e.getMessage(), e);
	    }
		
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
