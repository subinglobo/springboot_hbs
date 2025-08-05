package com.choosenfly.hotelbookingsystem.masters.service.agentCategory;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterAgentCategoryDTO;

import jakarta.validation.Valid;

public interface AgentCategoryServiceInterface {

	Long saveMasterAgentCategory(@Valid MasterAgentCategoryDTO agentCategoryDTO);

	MasterAgentCategoryDTO getAgentCategoryDetailsById(Long id);

	MasterAgentCategoryDTO editAgentCategory(Long id, @Valid MasterAgentCategoryDTO agentCategoryDTO);

	ResponseEntity<String> deleteAgentCategory(Long id);

}
