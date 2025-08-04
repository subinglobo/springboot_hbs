package com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.dto.BlockCheckInAndCheckOutDTO;

public interface BlockCheckInCheckOutServiceInterface {

	
	BlockCheckInAndCheckOutDTO addBlockDatestToHotel(BlockCheckInAndCheckOutDTO request);

	BlockCheckInAndCheckOutDTO getBlockDatestToHotel(Long hotelId, Long blockId);

	BlockCheckInAndCheckOutDTO updateBlockDatestToHotel(Long hotelId, BlockCheckInAndCheckOutDTO request);

	List<BlockCheckInAndCheckOutDTO> getBlockedDatesOfAHotel(Long hotelId);

	void deleteBlockDateOfAHotel(Long hotelId, Long blockId);
}
