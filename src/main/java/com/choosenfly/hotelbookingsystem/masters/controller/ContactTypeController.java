package com.choosenfly.hotelbookingsystem.masters.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterContactTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.service.ContactType.ContactTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacttype")
public class ContactTypeController {
	
	private ContactTypeServiceInterface contactTypeServiceInterface;
	
	
	public ContactTypeController(ContactTypeServiceInterface contactTypeServiceInterface) {
		this.contactTypeServiceInterface = contactTypeServiceInterface;
	}
	
	
	@PostMapping("/saveContactType")
	public Long saveContactType(@Valid @RequestBody MasterContactTypeDTO masterContactTypeDTO ) {
		
		return contactTypeServiceInterface.saveContactType(masterContactTypeDTO);
		
	}
	
	@GetMapping("/{id}")
	public MasterContactTypeDTO getContactTypeById(@PathVariable("id") Long id) {
		
		return contactTypeServiceInterface.getContactTypeById(id);
		
	}
	
	@PutMapping("/{id}")
	public MasterContactTypeDTO editContactType(@PathVariable("id") Long id , @RequestBody MasterContactTypeDTO masterContactTypeDTO)  {
		
		return contactTypeServiceInterface.editContactType(id , masterContactTypeDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContactType(@PathVariable("id") Long id) {
		
		return contactTypeServiceInterface.deleteContactType(id);
		
	}
	

}
