package com.choosenfly.hotelbookingsystem.service.masters.currency;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterCurrencyDTO;

public interface CurrencyServiceInterface {

	Long saveCurrency(MasterCurrencyDTO currecyDTO);

	MasterCurrencyDTO getCurrecnyById(Long id);

	MasterCurrencyDTO editCurrency(Long id, MasterCurrencyDTO currecyDTO);

	ResponseEntity<String> deleteCurrency(Long id);

}
