package com.choosenfly.hotelbookingsystem.inventory.occupancy.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.minimumlength.dto.MinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.inventory.minimumlength.dto.MinimumLengthResponseDTO;
import com.choosenfly.hotelbookingsystem.inventory.occupancy.dto.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.inventory.occupancy.dto.HotelOccupancyPatchDTO;
import com.choosenfly.hotelbookingsystem.inventory.occupancy.dto.HotelOccupancyResponseDTO;
import com.choosenfly.hotelbookingsystem.inventory.occupancy.dto.ListOccupanyDTO;
import com.choosenfly.hotelbookingsystem.inventory.occupancy.entities.HotelOccupancy;

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
