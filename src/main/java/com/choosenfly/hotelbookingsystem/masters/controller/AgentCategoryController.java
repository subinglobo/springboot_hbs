package com.choosenfly.hotelbookingsystem.masters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.service.agentCategory.AgentCategoryServiceInterface;

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
	
	@GetMapping
    public ResponseEntity<List<MasterAgentCategoryDTO>> getAllAvailableAgentCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int limit, 
			@RequestParam(required = false) String search) {

		Pageable pageable = PageRequest.of(page, limit);

		Page<MasterAgentCategoryDTO> agentCategoryPage = agentCategoryServiceInterface.getAllAvailableAgentCategories(pageable, search);

		 List<MasterAgentCategoryDTO> agentCategoryList = Optional.ofNullable(agentCategoryPage)
				.map(p -> p.getContent())
				.orElse(List.of()); 

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(agentCategoryList, HttpStatus.OK);
	}
	
    
}
