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

import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
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
	
	@GetMapping
	public ResponseEntity<List<MasterMarkupTypeDTO>> getAllMarkupTypeList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int limit,
			@RequestParam(required = false, name = "search") String searchTerm) {   

		Pageable pageable = PageRequest.of(page, limit);
		Page<MasterMarkupTypeDTO> markUpTypePage = markupTypeServiceInterface.getAllMarkupTypeList(pageable, searchTerm);
		List<MasterMarkupTypeDTO> markUpTypeListDTO = Optional.ofNullable(markUpTypePage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(markUpTypeListDTO, HttpStatus.OK);
	}
	

}
