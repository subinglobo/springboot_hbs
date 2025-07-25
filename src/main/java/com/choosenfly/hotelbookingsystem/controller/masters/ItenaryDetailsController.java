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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterItenaryDetailsDTO;
import com.choosenfly.hotelbookingsystem.service.masters.agentCategory.AgentCategoryServiceInterface;
import com.choosenfly.hotelbookingsystem.service.masters.itenaryDetails.ItenaryDetailsServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/master/itenaryDetails")
public class ItenaryDetailsController {
	
	private final ItenaryDetailsServiceInterface detailsServiceInterface;
	
	@Autowired
	public ItenaryDetailsController(ItenaryDetailsServiceInterface detailsServiceInterface) {
		this.detailsServiceInterface = detailsServiceInterface;
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/save")
	private Long saveItenaryDetails(@Valid @RequestBody MasterItenaryDetailsDTO itenaryDTO){
		return detailsServiceInterface.saveItenaryDetails(itenaryDTO);
	}
	
	@GetMapping("/{id}")
	private MasterAgentCategoryDTO getItenaryDetailsById(@PathVariable("id") Long id) {
		
		return detailsServiceInterface.getItenaryDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterAgentCategoryDTO editItenaryDetails(@PathVariable("id") Long id , @Valid @RequestBody MasterItenaryDetailsDTO itenaryDTO) {
		return detailsServiceInterface.editItenaryDetails(id , itenaryDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteItenaryDetails(@PathVariable("id") Long id) {
		return detailsServiceInterface.deleteItenaryDetails(id);
	}

}
