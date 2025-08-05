package com.choosenfly.hotelbookingsystem.inventory.service.hotelstopsale;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.inventory.dto.stopsale.StopSaleDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.stopsale.StopSaleValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale.HotelStopSale;
import com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale.StopSaleValiditty;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelStopSaleRepositoy;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomCategory;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomCategoryRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class HotelStopSaleService implements HotelStopSaleServiceInterface{
	
	private final HotelStopSaleRepositoy stopSaleRepositoy;
	
	private final HotelRepository hotelRepository;

	private MasterMarketTypeRepository masterMarketTypeRepository;
	
	private MasterRoomCategoryRepository masterRoomCategoryRepository;

	
	public HotelStopSaleService(HotelStopSaleRepositoy stopSaleRepositoy,HotelRepository hotelRepository,
			MasterMarketTypeRepository masterMarketTypeRepository,MasterRoomCategoryRepository masterRoomCategoryRepository) {
		this.stopSaleRepositoy=stopSaleRepositoy;
		this.hotelRepository=hotelRepository;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
		this.masterRoomCategoryRepository=masterRoomCategoryRepository;
		
	}

	@Override
	@Transactional
	public Long saveStopSale(@Valid StopSaleDTO stopSaleDTO) {
		// TODO Auto-generated method stub
		
		if(stopSaleDTO==null) {
			throw new MissingRequestBodyException("Request Body cannot be null");
		
		}
		
		HotelStopSale stopSale = new HotelStopSale();
		System.out.println("stopSaleDTO.getHotelId()::"+stopSaleDTO.getHotelId());
		Hotel hotel = hotelRepository.findById(stopSaleDTO.getHotelId()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + stopSaleDTO.getHotelId()));
		
		System.out.println("hotel::"+hotel);
		stopSale.setHotel(hotel);
		
		MasterMarketType marketData = masterMarketTypeRepository.findById(stopSaleDTO.getMarketTypeId()).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + stopSaleDTO.getMarketTypeId()));
		stopSale.setMarketTypeId(marketData);
		
		MasterRoomCategory roomCategoryEntity = masterRoomCategoryRepository.findById(stopSaleDTO.getRoomCategoryId()).orElseThrow(() -> new EntityNotFoundException("Room Category not found for id : " + stopSaleDTO.getRoomCategoryId()));
		stopSale.setRoomCategoryId(roomCategoryEntity);
		
		stopSale.setBlock(stopSaleDTO.getBlock());
		stopSale.setFreeSale(stopSaleDTO.getFreeSale());
		stopSale.setRoomAllocation(stopSaleDTO.getRoomAllocation());
		stopSale.setIsLive(false);
		
		List<StopSaleValiditty> validityList = stopSaleDTO.getStopSaleValidityDTO().stream().map(
			validity->{
				StopSaleValiditty stopSaleValiditty = new StopSaleValiditty();
				stopSaleValiditty.setStopSale(stopSale);
				stopSaleValiditty.setValidityFrom(validity.getValidityFrom());
				stopSaleValiditty.setValidityTo(validity.getValidityTo());
				
				return stopSaleValiditty;
				
			}).collect(Collectors.toList());
		stopSale.setValidityList(validityList);
		
		HotelStopSale save = stopSaleRepositoy.save(stopSale);
		save.getHotelStopSaleId();
		if(save.getHotelStopSaleId()!=null && save.getHotelStopSaleId()!=0) {
			return save.getHotelStopSaleId();
		}
		return null;
		
	}

	@Override
	@Transactional
	public StopSaleDTO getStopSale(Long id) {
	    HotelStopSale stopSaleData = stopSaleRepositoy.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("Stop sale not found for id: " + id));

	    if (stopSaleData.getHotelStopSaleId() != null) {
	        StopSaleDTO stopSaleDTO = new StopSaleDTO();
	        stopSaleDTO.setStopSaleId(stopSaleData.getHotelStopSaleId());
	        stopSaleDTO.setHotelId(stopSaleData.getHotel().getHotelId());
	        stopSaleDTO.setMarketTypeId(stopSaleData.getMarketTypeId().getMarketTypeId());
	        stopSaleDTO.setRoomCategoryId(stopSaleData.getRoomCategoryId().getRoomCategoryId());
	        stopSaleDTO.setBlock(stopSaleData.getBlock());
	        stopSaleDTO.setFreeSale(stopSaleData.getFreeSale());
	        stopSaleDTO.setRoomAllocation(stopSaleData.getRoomAllocation());
	        stopSaleDTO.setIsLive(stopSaleData.getIsLive());
	        // ✅ Map StopSaleValiditty list to StopSaleValidityDTO list
	        List<StopSaleValidityDTO> validityDTOList = stopSaleData.getValidityList().stream().map(validity -> {
	            StopSaleValidityDTO dto = new StopSaleValidityDTO();
	            dto.setStopSaleValidityId(validity.getId());
	            dto.setValidityFrom(validity.getValidityFrom());
	            dto.setValidityTo(validity.getValidityTo());
	            return dto;
	        }).collect(Collectors.toList());

	        stopSaleDTO.setStopSaleValidityDTO(validityDTOList);

	        return stopSaleDTO;
	    }

	    return null;
	}

	@Override
	@Transactional
	public StopSaleDTO editStopSale(Long id, @Valid StopSaleDTO stopSaleDTO) {
	    HotelStopSale stopSale = stopSaleRepositoy.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("StopSale not found with id: " + id));

	    // Update basic fields
	    stopSale.setBlock(stopSaleDTO.getBlock());
	    stopSale.setFreeSale(stopSaleDTO.getFreeSale());
	    stopSale.setRoomAllocation(stopSaleDTO.getRoomAllocation());

	    // Update Hotel, MarketType, RoomCategory if needed
	    Hotel hotel = hotelRepository.findById(stopSaleDTO.getHotelId())
	        .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + stopSaleDTO.getHotelId()));
	    stopSale.setHotel(hotel);

	    MasterMarketType marketData = masterMarketTypeRepository.findById(stopSaleDTO.getMarketTypeId())
	        .orElseThrow(() -> new EntityNotFoundException("Market Type not found with id :" + stopSaleDTO.getMarketTypeId()));
	    stopSale.setMarketTypeId(marketData);

	    MasterRoomCategory roomCategory = masterRoomCategoryRepository.findById(stopSaleDTO.getRoomCategoryId())
	        .orElseThrow(() -> new EntityNotFoundException("Room Category not found with id : " + stopSaleDTO.getRoomCategoryId()));
	    stopSale.setRoomCategoryId(roomCategory);

	    // ✅ Fix orphan issue: clear and add to the existing list
	    stopSale.getValidityList().clear();
	    List<StopSaleValiditty> updatedValidityList = stopSaleDTO.getStopSaleValidityDTO().stream().map(validityDTO -> {
	        StopSaleValiditty validity = new StopSaleValiditty();
	        validity.setValidityFrom(validityDTO.getValidityFrom());
	        validity.setValidityTo(validityDTO.getValidityTo());
	        validity.setStopSale(stopSale); // important for bi-directional mapping
	        return validity;
	    }).collect(Collectors.toList());
	    stopSale.getValidityList().addAll(updatedValidityList);
	    stopSaleRepositoy.save(stopSale);

	    // Optional: Return updated DTO
	    return getStopSale(id);
	}

	@Override
	public ResponseEntity<String> deleteStopSale(Long id) {
		// TODO Auto-generated method stub
		
	    HotelStopSale stopSale = stopSaleRepositoy.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Stop sale not found for id: " + id));

	        stopSaleRepositoy.delete(stopSale);
	        return ResponseEntity.ok("stop sale with id " + id + " deleted successfully");
	}


	@Override
	@Transactional
	public Page<StopSaleDTO> getAllStopSale(Pageable pageable, String search) {
		// TODO Auto-generated method stub
	    Page<HotelStopSale> stopSalePage = stopSaleRepositoy.findAll(pageable);

	    return stopSalePage.map(stopSale -> {
	        StopSaleDTO dto = new StopSaleDTO();
	        dto.setStopSaleId(stopSale.getHotelStopSaleId());
	        dto.setHotelId(stopSale.getHotel().getHotelId());
	        dto.setMarketTypeId(stopSale.getMarketTypeId().getMarketTypeId());
	        dto.setRoomCategoryId(stopSale.getRoomCategoryId().getRoomCategoryId());
	        dto.setFreeSale(stopSale.getFreeSale());
	        dto.setBlock(stopSale.getBlock());
	        dto.setRoomAllocation(stopSale.getRoomAllocation());
	        dto.setIsLive(stopSale.getIsLive());
	        dto.setStopSaleValidityDTO(
	            stopSale.getValidityList().stream().map(validity -> {
	                StopSaleValidityDTO validityDTO = new StopSaleValidityDTO();
	                validityDTO.setStopSaleValidityId(validity.getId());
	                validityDTO.setValidityFrom(validity.getValidityFrom());
	                validityDTO.setValidityTo(validity.getValidityTo());
	                return validityDTO;
	            }).collect(Collectors.toList())
	        );

	        return dto;
	    });
	}

	@Override
	@Transactional
	public ResponseEntity<String> updateIsLiveStatus(Long id, Boolean isLive) {
	    HotelStopSale stopSale = stopSaleRepositoy.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Stop Sale not found for id: " + id));

	    stopSale.setIsLive(isLive);

	    stopSaleRepositoy.save(stopSale);

	    String status = isLive ? "activated" : "deactivated";
	    return ResponseEntity.ok("Stop Sale with ID " + id + " has been successfully " + status + ".");
	}




}
