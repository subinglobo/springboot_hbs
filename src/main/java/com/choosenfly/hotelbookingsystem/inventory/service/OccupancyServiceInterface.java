package com.choosenfly.hotelbookingsystem.inventory.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.dto.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelOccupancyPatchDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelOccupancyResponseDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.ListOccupanyDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.MinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.MinimumLengthResponseDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;

public interface OccupancyServiceInterface{

	HotelOccupancy addOccupancy(HotelOccupancyDTO request);

	List<ListOccupanyDTO> getHotelOccupancies(Long hotelId);

	HotelOccupancyResponseDTO getHotelOccupancy(Long hotelId, Long occupancyId);

	ListOccupanyDTO updateOccupancyStatus(Long occupancyId, HotelOccupancyPatchDTO patchDTO);

	void addMinimumLength(MinimumLengthDTO request);

	List<MinimumLengthResponseDTO> getMinimumLengthOfAHottel(Long hotelId);

	MinimumLengthDTO getAMinimumLengthOfHotel(Long hotelId, Long minimumLengthId);

	void editHotelOccupancy(Long hotelId, Long occupancyId, HotelOccupancyDTO request);

}
