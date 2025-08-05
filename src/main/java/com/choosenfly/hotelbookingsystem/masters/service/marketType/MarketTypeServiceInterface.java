package com.choosenfly.hotelbookingsystem.masters.service.marketType;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterMarketTypeDTO;

public interface MarketTypeServiceInterface {

	Long saveMarketType(MasterMarketTypeDTO marketDTO);

	MasterMarketTypeDTO getMarketTypeById(Long id);

	MasterMarketTypeDTO editMarketType(Long id, MasterMarketTypeDTO marketDTO);

	ResponseEntity<String> deleteMarketType(Long id);


}
