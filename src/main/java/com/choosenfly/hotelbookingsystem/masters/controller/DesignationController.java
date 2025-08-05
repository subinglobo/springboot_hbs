package com.choosenfly.hotelbookingsystem.masters.controller;

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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterDesignationDTO;
import com.choosenfly.hotelbookingsystem.masters.service.DesignationServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/designation")
public class DesignationController {
	
	private final DesignationServiceInterface  designationServiceInterface;
	
	@Autowired
	public DesignationController(DesignationServiceInterface designationServiceInterface) {
		this.designationServiceInterface = designationServiceInterface;
		
	}
	
	
	@PostMapping("/saveDesignation")
	public Long save(@Valid @RequestBody MasterDesignationDTO desigDTO) {
		
		return designationServiceInterface.save(desigDTO);
	}
	
	@GetMapping("/{id}")
	private MasterDesignationDTO getDesigDetailsById(@PathVariable("id") Long id) {
		
		return designationServiceInterface.getDesigDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterDesignationDTO editDesignation(@PathVariable("id") Long id , @Valid @RequestBody MasterDesignationDTO desigDTO) {
		return designationServiceInterface.editDesignation(id , desigDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteDesignation(@PathVariable("id") Long id) {
		return designationServiceInterface.deleteDesignation(id);
	}
	

}
