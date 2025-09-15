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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterContactTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterCurrencyDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterMarketTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterMarkupTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.service.marketType.MarketTypeServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.markupType.MarkupTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/marketType")
public class MarketTypeController {
	
	private final  MarketTypeServiceInterface marketTypeServiceInterface;
	
	@Autowired
	public MarketTypeController(MarketTypeServiceInterface marketTypeServiceInterface) {
		this.marketTypeServiceInterface = marketTypeServiceInterface;
	}
	
	
	@PostMapping("/saveMarketType")
	public Long saveMarketType(@Valid @RequestBody MasterMarketTypeDTO marketDTO ) {
		
		return marketTypeServiceInterface.saveMarketType(marketDTO);
		
	}
	
	@GetMapping("/{id}")
	public MasterMarketTypeDTO getMarketTypeById(@PathVariable("id") Long id) {
		
		return marketTypeServiceInterface.getMarketTypeById(id);
		
	}
	
	@PutMapping("/{id}")
	public MasterMarketTypeDTO editMarketType(@PathVariable("id") Long id , @RequestBody MasterMarketTypeDTO marketDTO)  {
		
		return marketTypeServiceInterface.editMarketType(id , marketDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMarketType(@PathVariable("id") Long id) {
		
		return marketTypeServiceInterface.deleteMarketType(id);
		
	}
	
	@GetMapping
	public ResponseEntity<List<MasterMarketTypeDTO>> getAllMarketTypeList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int limit,
			@RequestParam(required = false, name = "search") String searchTerm) {   

		Pageable pageable = PageRequest.of(page, limit);
		Page<MasterMarketTypeDTO> markettypePage = marketTypeServiceInterface.getAllMarketTypeList(pageable, searchTerm);
		List<MasterMarketTypeDTO> markettypeListDTO = Optional.ofNullable(markettypePage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(markettypeListDTO, HttpStatus.OK);
	}
	

}
