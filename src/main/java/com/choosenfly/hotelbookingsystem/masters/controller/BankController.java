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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.service.bank.BankServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bank")
public class BankController {
	
	private final BankServiceInterface bankServiceInterface;
	
	@Autowired
	public BankController(BankServiceInterface bankServiceInterface) {
		this.bankServiceInterface = bankServiceInterface;
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping("/save")
	private Long saveMasterBank(@Valid @RequestBody MasterBankDTO bankDTO){
		return bankServiceInterface.saveMasterBank(bankDTO);
	}
	
	@GetMapping("/{id}")
	private MasterBankDTO getBankDetailsById(@PathVariable("id") Long id) {
		
		return bankServiceInterface.getBankDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterBankDTO editBank(@PathVariable("id") Long id , @Valid @RequestBody MasterBankDTO bankDTO) {
		return bankServiceInterface.editBank(id , bankDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteBank(@PathVariable("id") Long id) {
		return bankServiceInterface.deleteBank(id);
	}
	
	
	
	
	
	
	

}
