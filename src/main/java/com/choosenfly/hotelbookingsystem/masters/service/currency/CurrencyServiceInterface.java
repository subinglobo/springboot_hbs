package com.choosenfly.hotelbookingsystem.masters.service.currency;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterCurrencyDTO;

public interface CurrencyServiceInterface {

	Long saveCurrency(MasterCurrencyDTO currecyDTO);

	MasterCurrencyDTO getCurrecnyById(Long id);

	MasterCurrencyDTO editCurrency(Long id, MasterCurrencyDTO currecyDTO);

	ResponseEntity<String> deleteCurrency(Long id);

	Page<MasterCurrencyDTO> getAllCurrencyList(Pageable pageable, String searchTerm);

}
