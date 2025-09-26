package com.choosenfly.hotelbookingsystem.inventory.service.discount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.inventory.dto.discount.DiscountDTO;

import jakarta.validation.Valid;

public interface HotelDiscountServiceInterface {

	Long saveDiscount(@Valid DiscountDTO discountDTO);

	DiscountDTO getDiscount(Long id);

	DiscountDTO editDiscount(Long id, @Valid DiscountDTO discountDTO);

	ResponseEntity<String> deleteDiscount(Long id);

	Page<DiscountDTO> getAllDiscount(Pageable pageable, String search);


	
}
