package com.choosenfly.hotelbookingsystem.service.occupancy;

import java.util.List;

import com.choosenfly.hotelbookingsystem.dto.minimumlength.MinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.dto.minimumlength.MinimumLengthResponseDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyPatchDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyResponseDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.ListOccupanyDTO;
import com.choosenfly.hotelbookingsystem.entities.occupancy.HotelOccupancy;

public interface OccupancyServiceInterface{

	HotelOccupancy addOccupancy(HotelOccupancyDTO request);

	List<ListOccupanyDTO> getHotelOccupancies(Long hotelId);

	HotelOccupancyResponseDTO getHotelOccupancy(Long hotelId, Long occupancyId);

	ListOccupanyDTO updateOccupancyStatus(Long occupancyId, HotelOccupancyPatchDTO patchDTO);

	void addMinimumLength(MinimumLengthDTO request);

	List<MinimumLengthResponseDTO> getMinimumLengthOfAHottel(Long hotelId);

	MinimumLengthDTO getAMinimumLengthOfHotel(Long hotelId, Long minimumLengthId);

}
