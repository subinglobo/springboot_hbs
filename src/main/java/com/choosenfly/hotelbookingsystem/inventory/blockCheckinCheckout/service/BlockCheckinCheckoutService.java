package com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.dto.BlockCheckInAndCheckOutDTO;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.dto.BlockCheckinCheckoutValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.entities.BlockCheckInAndCheckOut;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.entities.BlockCheckinCheckoutValidity;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.repository.BlockCheckinCheckoutRepositoryInterface;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.repository.BlockCheckinCheckoutValidityRepository;
import com.choosenfly.hotelbookingsystem.inventory.hotel.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.hotel.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.master.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.master.repository.MasterMarketTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class BlockCheckinCheckoutService implements BlockCheckInCheckOutServiceInterface {

	private final HotelRepository hotelRepository;

	private final BlockCheckinCheckoutRepositoryInterface blockCheckinCheckoutRepository;

	private final MasterMarketTypeRepository marketTypeRepository;
	
	private final BlockCheckinCheckoutValidityRepository blockCheckinCheckoutValidityRepository;

	@Autowired
	public BlockCheckinCheckoutService(BlockCheckinCheckoutRepositoryInterface blockCheckinCheckoutRepository,
			MasterMarketTypeRepository marketTypeRepository, HotelRepository hotelRepository,BlockCheckinCheckoutValidityRepository blockCheckinCheckoutValidityRepository) {
		this.blockCheckinCheckoutRepository = blockCheckinCheckoutRepository;
		this.marketTypeRepository = marketTypeRepository;
		this.hotelRepository = hotelRepository;
		this.blockCheckinCheckoutValidityRepository = blockCheckinCheckoutValidityRepository;
	}

	@Override
	@Transactional
	public BlockCheckInAndCheckOutDTO addBlockDatestToHotel(BlockCheckInAndCheckOutDTO request) {
		// TODO Auto-generated method stub
		
		
		Hotel hotel = hotelRepository.findById(request.getHotelId())
		.orElseThrow(() -> new EntityNotFoundException("Hotel not found with id : "+request.getHotelId()));

		BlockCheckInAndCheckOut blockCheckInAndCheckOut = new BlockCheckInAndCheckOut();
		blockCheckInAndCheckOut.setIsCheckin(request.getIsCheckin());
		blockCheckInAndCheckOut.setIsCheckOut(request.getIsCheckOut());

		blockCheckInAndCheckOut.setHotel(hotel);
		
		MasterMarketType marketType = marketTypeRepository.findById(request.getMarketTypeId()).orElseThrow(
				() -> new EntityNotFoundException("Market Type not Found for id : " + request.getMarketTypeId()));

		blockCheckInAndCheckOut.setMarketType(marketType);

		List<BlockCheckinCheckoutValidity> blockCheckinCheckoutValidityList = request.getValidityList().stream()
				.map(valididty -> {
					BlockCheckinCheckoutValidity validity = new BlockCheckinCheckoutValidity();
					validity.setBlockCheckinCheckout(blockCheckInAndCheckOut);
					validity.setValidityFrom(valididty.getValidityFrom());
					validity.setValidityTo(valididty.getValidityTo());

					return validity;

				}).collect(Collectors.toList());

		blockCheckInAndCheckOut.setValidityList(blockCheckinCheckoutValidityList);

		BlockCheckInAndCheckOut save = blockCheckinCheckoutRepository.save(blockCheckInAndCheckOut);

		BlockCheckInAndCheckOutDTO response = new BlockCheckInAndCheckOutDTO();

		response.setHotelId(request.getHotelId());
		response.setId(save.getId());
		response.setIsCheckin(save.getIsCheckin());
		response.setIsCheckOut(save.getIsCheckOut());
		response.setMarketTypeId(marketType.getMarketTypeId());
		response.setMarketTypeName(marketType.getName());

		List<BlockCheckinCheckoutValidity> validityList = save.getValidityList();

		List<BlockCheckinCheckoutValidityDTO> validityListDTO = validityList.stream().map(val -> {
			BlockCheckinCheckoutValidityDTO dto = new BlockCheckinCheckoutValidityDTO();
			dto.setBlockId(save.getId());
			dto.setId(val.getId());
			dto.setValidityFrom(val.getValidityFrom());
			dto.setValidityTo(val.getValidityTo());

			return dto;
		}).collect(Collectors.toList());

		response.setValidityList(validityListDTO);

		return response;
	}

	@Override
	@Transactional
	public BlockCheckInAndCheckOutDTO getBlockDatestToHotel(Long hotelId, Long blockId) {
		// TODO Auto-generated method stub

		BlockCheckInAndCheckOut block = blockCheckinCheckoutRepository.findById(blockId).orElseThrow(
				() -> new EntityNotFoundException(" Block Checkin Checkout not found with id : " + blockId));

		BlockCheckInAndCheckOutDTO response = new BlockCheckInAndCheckOutDTO();

		response.setHotelId(hotelId);
		response.setId(block.getId());
		response.setIsCheckin(block.getIsCheckin());
		response.setIsCheckOut(block.getIsCheckOut());
		response.setMarketTypeId(block.getMarketType().getMarketTypeId());
		response.setMarketTypeName(block.getMarketType().getName());

		List<BlockCheckinCheckoutValidity> validityList = block.getValidityList();

		List<BlockCheckinCheckoutValidityDTO> validityListDTO = validityList.stream().map(val -> {
			BlockCheckinCheckoutValidityDTO dto = new BlockCheckinCheckoutValidityDTO();
			dto.setBlockId(block.getId());
			dto.setId(val.getId());
			dto.setValidityFrom(val.getValidityFrom());
			dto.setValidityTo(val.getValidityTo());

			return dto;
		}).collect(Collectors.toList());

		response.setValidityList(validityListDTO);

		return response;

	}

	@Override
	@Transactional
	public BlockCheckInAndCheckOutDTO updateBlockDatestToHotel(Long hotelId, BlockCheckInAndCheckOutDTO request) {
	    // Fetch the parent entity with its current state
	    BlockCheckInAndCheckOut block = blockCheckinCheckoutRepository.findById(request.getId())
	        .orElseThrow(() -> new EntityNotFoundException("Block Checkin Checkout not found with id: " + request.getId()));

	    // Update parent fields
	    block.setIsCheckin(request.getIsCheckin());
	    block.setIsCheckOut(request.getIsCheckOut());

	    MasterMarketType marketType = marketTypeRepository.findById(request.getMarketTypeId())
	        .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id: " + request.getMarketTypeId()));
	    block.setMarketType(marketType);

	    // Handle validity list
	    List<BlockCheckinCheckoutValidity> validityList = block.getValidityList();
	    Map<Long, BlockCheckinCheckoutValidity> existingValidities = validityList.stream()
	        .collect(Collectors.toMap(BlockCheckinCheckoutValidity::getId, v -> v));

	    validityList.clear(); // Clear current list

	    List<BlockCheckinCheckoutValidity> updatedValidityList = request.getValidityList().stream()
	        .map(validityDTO -> {
	            BlockCheckinCheckoutValidity validity;
	            if (validityDTO.getId() != null) {
	                // Fetch existing entity for updates
	                validity = existingValidities.getOrDefault(validityDTO.getId(),
	                    blockCheckinCheckoutValidityRepository.findById(validityDTO.getId())
	                        .orElse(new BlockCheckinCheckoutValidity())); // New if not in DB
	            } else {
	                // New entity
	                validity = new BlockCheckinCheckoutValidity();
	            }
	            validity.setBlockCheckinCheckout(block);
	            validity.setValidityFrom(validityDTO.getValidityFrom());
	            validity.setValidityTo(validityDTO.getValidityTo());
	            return validity;
	        }).collect(Collectors.toList());

	    validityList.addAll(updatedValidityList);

	    // Save and flush to catch issues early
	    BlockCheckInAndCheckOut saved = blockCheckinCheckoutRepository.save(block);

	    // Map to DTO
	    BlockCheckInAndCheckOutDTO response = new BlockCheckInAndCheckOutDTO();
	    response.setHotelId(hotelId);
	    response.setId(saved.getId());
	    response.setIsCheckin(saved.getIsCheckin());
	    response.setIsCheckOut(saved.getIsCheckOut());
	    response.setMarketTypeId(marketType.getMarketTypeId());
	    response.setMarketTypeName(marketType.getName());

	    List<BlockCheckinCheckoutValidityDTO> validityListDTO = saved.getValidityList().stream()
	        .map(val -> {
	            BlockCheckinCheckoutValidityDTO dto = new BlockCheckinCheckoutValidityDTO();
	            dto.setBlockId(saved.getId());
	            dto.setId(val.getId());
	            dto.setValidityFrom(val.getValidityFrom());
	            dto.setValidityTo(val.getValidityTo());
	            return dto;
	        }).collect(Collectors.toList());

	    response.setValidityList(validityListDTO);
	    return response;
	}

	@Override
	@Transactional
	public List<BlockCheckInAndCheckOutDTO> getBlockedDatesOfAHotel(Long hotelId) {
		// TODO Auto-generated method stub

		Hotel hotel = hotelRepository.findHotelWithBlockedDatesByHotelId(hotelId)
				.orElseThrow(() -> new EntityNotFoundException("Blocked Dates not Fond with id : " + hotelId));

		List<BlockCheckInAndCheckOutDTO> dtoList = hotel.getHotelBlockedDates().stream().map(block -> {

			BlockCheckInAndCheckOutDTO blockDTO = new BlockCheckInAndCheckOutDTO();
			blockDTO.setHotelId(hotelId);
			blockDTO.setId(block.getId());
			blockDTO.setMarketTypeId(block.getMarketType().getMarketTypeId());
			blockDTO.setMarketTypeName(block.getMarketType().getName());

			return blockDTO;

		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	@Transactional
	public void deleteBlockDateOfAHotel(Long hotelId, Long blockId) {
		// TODO Auto-generated method stub
		
		
		BlockCheckInAndCheckOut block = blockCheckinCheckoutRepository.findById(blockId)
				.orElseThrow(() -> new EntityNotFoundException("BlockDate Not Found with id "+blockId));
				
		       Hotel hotel = Optional.ofNullable(block.getHotel())
		       .orElseThrow(() -> new HotelNotFoundException("Hotel Not Found For Provided Availability"));
				
		       if(!hotelId.equals(hotel.getHotelId()))
		       {
		    	   throw new HotelNotFoundException("BlockDate does not belong to the specified hotel.");
		       }
		       
				
				blockCheckinCheckoutRepository.deleteById(blockId);
	}

}
