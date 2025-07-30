package com.choosenfly.hotelbookingsystem.service.masters.agentCategory;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;

import jakarta.validation.Valid;

public interface AgentCategoryServiceInterface {

	Long saveMasterAgentCategory(@Valid MasterAgentCategoryDTO agentCategoryDTO);

	MasterAgentCategoryDTO getAgentCategoryDetailsById(Long id);

	MasterAgentCategoryDTO editAgentCategory(Long id, @Valid MasterAgentCategoryDTO agentCategoryDTO);

	ResponseEntity<String> deleteAgentCategory(Long id);

}
