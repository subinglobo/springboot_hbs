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
import org.springframework.web.server.ResponseStatusException;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterCurrencyDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterMarkupTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.service.currency.CurrencyServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/currency")
public class CurrecyController {
	
	private final CurrencyServiceInterface currencyServiceInterface;
	
	@Autowired
	public CurrecyController(CurrencyServiceInterface currencyServiceInterface) {
		this.currencyServiceInterface = currencyServiceInterface;
	}
	
	
	@PostMapping("/saveCurrency")
	public Long saveCurrency(@Valid @RequestBody MasterCurrencyDTO currecyDTO) {

		if (currecyDTO == null) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body cannot be null");
		}

		return currencyServiceInterface.saveCurrency(currecyDTO);

	}

	@GetMapping("/{id}")
	public MasterCurrencyDTO getCurrecnyById(@PathVariable("id") Long id) {
		
		return currencyServiceInterface.getCurrecnyById(id);
		
	}
	
	@PutMapping("/{id}")
	public MasterCurrencyDTO editCurrency(@PathVariable("id") Long id , @RequestBody MasterCurrencyDTO currecyDTO)  {
		
		return currencyServiceInterface.editCurrency(id , currecyDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCurrency(@PathVariable("id") Long id) {
		
		return currencyServiceInterface.deleteCurrency(id);
		
	}
	

}
