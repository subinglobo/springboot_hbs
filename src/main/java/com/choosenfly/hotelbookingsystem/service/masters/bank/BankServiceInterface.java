package com.choosenfly.hotelbookingsystem.service.masters.bank;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;

public interface BankServiceInterface {

	Long saveMasterBank( MasterBankDTO bankDTO);

	MasterBankDTO getBankDetailsById(Long id);

	MasterBankDTO editBank(Long id , MasterBankDTO bankDTO);

	ResponseEntity<String> deleteBank(Long id);

}
