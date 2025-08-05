package com.choosenfly.hotelbookingsystem.inventory.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.dto.BlockCheckInAndCheckOutDTO;

public interface BlockCheckInCheckOutServiceInterface {

	
	BlockCheckInAndCheckOutDTO addBlockDatestToHotel(BlockCheckInAndCheckOutDTO request);

	BlockCheckInAndCheckOutDTO getBlockDatestToHotel(Long hotelId, Long blockId);

	BlockCheckInAndCheckOutDTO updateBlockDatestToHotel(Long hotelId, BlockCheckInAndCheckOutDTO request);

	List<BlockCheckInAndCheckOutDTO> getBlockedDatesOfAHotel(Long hotelId);

	void deleteBlockDateOfAHotel(Long hotelId, Long blockId);
}
