package com.choosenfly.hotelbookingsystem.masters.service;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;

public interface BankServiceInterface {

	Long saveMasterBank( MasterBankDTO bankDTO);

	MasterBankDTO getBankDetailsById(Long id);

	MasterBankDTO editBank(Long id , MasterBankDTO bankDTO);

	ResponseEntity<String> deleteBank(Long id);

}
