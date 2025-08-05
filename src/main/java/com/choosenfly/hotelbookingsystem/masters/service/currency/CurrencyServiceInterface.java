package com.choosenfly.hotelbookingsystem.masters.service.currency;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterCurrencyDTO;

public interface CurrencyServiceInterface {

	Long saveCurrency(MasterCurrencyDTO currecyDTO);

	MasterCurrencyDTO getCurrecnyById(Long id);

	MasterCurrencyDTO editCurrency(Long id, MasterCurrencyDTO currecyDTO);

	ResponseEntity<String> deleteCurrency(Long id);

}
