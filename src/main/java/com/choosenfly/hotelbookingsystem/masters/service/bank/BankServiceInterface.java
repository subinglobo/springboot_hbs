package com.choosenfly.hotelbookingsystem.masters.service.bank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;

public interface BankServiceInterface {

	Long saveMasterBank( MasterBankDTO bankDTO);

	MasterBankDTO getBankDetailsById(Long id);

	MasterBankDTO editBank(Long id , MasterBankDTO bankDTO);

	ResponseEntity<String> deleteBank(Long id);

	Page<MasterBankDTO> getAllBanks(Pageable pageable, String searchTerm);

}
