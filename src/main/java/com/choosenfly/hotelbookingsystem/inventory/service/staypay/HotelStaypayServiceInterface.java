package com.choosenfly.hotelbookingsystem.inventory.service.staypay;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.inventory.dto.staypay.StayPayPromotionDTO;

import jakarta.validation.Valid;

public interface HotelStaypayServiceInterface {

	Long saveStayPay(@Valid StayPayPromotionDTO stayPayDTO);

	StayPayPromotionDTO getstayPay(Long id);

	StayPayPromotionDTO editstayPay(Long id, @Valid StayPayPromotionDTO stayPayDTO);

	ResponseEntity<String> deletestayPay(Long id);

	Page<StayPayPromotionDTO> getAllstayPay(Pageable pageable, String search);


}
