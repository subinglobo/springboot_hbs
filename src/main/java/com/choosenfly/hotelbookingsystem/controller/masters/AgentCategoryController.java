package com.choosenfly.hotelbookingsystem.controller.masters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.service.masters.agentCategory.AgentCategoryService;
import com.choosenfly.hotelbookingsystem.service.masters.agentCategory.AgentCategoryServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agentCategory")
public class AgentCategoryController {

	private final AgentCategoryServiceInterface agentCategoryServiceInterface;
	
	@Autowired
	public AgentCategoryController(AgentCategoryServiceInterface agentCategoryServiceInterface) {
		this.agentCategoryServiceInterface = agentCategoryServiceInterface;
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/save")
	private Long saveMasterAgentCategory(@Valid @RequestBody MasterAgentCategoryDTO AgentCategoryDTO){
		return agentCategoryServiceInterface.saveMasterAgentCategory(AgentCategoryDTO);
	}
	
	@GetMapping("/{id}")
	private MasterAgentCategoryDTO getAgentCategoryDetailsById(@PathVariable("id") Long id) {
		
		return agentCategoryServiceInterface.getAgentCategoryDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterAgentCategoryDTO editAgentCategory(@PathVariable("id") Long id , @Valid @RequestBody MasterAgentCategoryDTO agentCategoryDTO) {
		return agentCategoryServiceInterface.editAgentCategory(id , agentCategoryDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteAgentCategory(@PathVariable("id") Long id) {
		return agentCategoryServiceInterface.deleteAgentCategory(id);
	}
    
    
}
