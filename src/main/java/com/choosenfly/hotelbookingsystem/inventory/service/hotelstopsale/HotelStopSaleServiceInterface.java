package com.choosenfly.hotelbookingsystem.inventory.service.hotelstopsale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.inventory.dto.stopsale.StopSaleDTO;

import jakarta.validation.Valid;

public interface HotelStopSaleServiceInterface {

	Long saveStopSale(@Valid StopSaleDTO stopSaleDTO);

	StopSaleDTO getStopSale(Long id);

	StopSaleDTO editStopSale(Long id, @Valid StopSaleDTO stopSaleDTO);

	ResponseEntity<String> deleteStopSale(Long id);

	Page<StopSaleDTO> getAllStopSale(Pageable pageable, String search);

	ResponseEntity<String> updateIsLiveStatus(Long id, Boolean isLive);


}
