package com.choosenfly.hotelbookingsystem.service.masters.marketType;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterMarketTypeDTO;

public interface MarketTypeServiceInterface {

	Long saveMarketType(MasterMarketTypeDTO marketDTO);

	MasterMarketTypeDTO getMarketTypeById(Long id);

	MasterMarketTypeDTO editMarketType(Long id, MasterMarketTypeDTO marketDTO);

	ResponseEntity<String> deleteMarketType(Long id);


}
