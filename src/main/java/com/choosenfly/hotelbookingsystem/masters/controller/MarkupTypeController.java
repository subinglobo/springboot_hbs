package com.choosenfly.hotelbookingsystem.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterMarkupTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.service.markupType.MarkupTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/markupType")
public class MarkupTypeController {
	
	private final MarkupTypeServiceInterface markupTypeServiceInterface;
	
	@Autowired
	public MarkupTypeController(MarkupTypeServiceInterface markupTypeServiceInterface) {
		this.markupTypeServiceInterface = markupTypeServiceInterface;
	}
	
	
	@PostMapping("/saveMarkupType")
	public Long saveMarkupType(@Valid @RequestBody MasterMarkupTypeDTO markupDTO ) {
		
		if (markupDTO == null) {

			throw new MissingRequestBodyException("Request body cannot be null");
		}
		return markupTypeServiceInterface.saveMarkupType(markupDTO);
		
	}
	
	@GetMapping("/{id}")
	public MasterMarkupTypeDTO getMarkupTypeById(@PathVariable("id") Long id) {
		
		return markupTypeServiceInterface.getMarkupTypeById(id);
		
	}
	
	@PutMapping("/{id}")
	public MasterMarkupTypeDTO editMarkupType(@PathVariable("id") Long id , @RequestBody MasterMarkupTypeDTO markupDTO)  {
		
		return markupTypeServiceInterface.editMarkupType(id , markupDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMarkupType(@PathVariable("id") Long id) {
		
		return markupTypeServiceInterface.deleteMarkupType(id);
		
	}
	

}
