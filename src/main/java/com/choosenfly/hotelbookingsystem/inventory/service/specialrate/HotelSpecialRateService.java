package com.choosenfly.hotelbookingsystem.inventory.service.specialrate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.inventory.dto.specialrate.SpecialRateDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.specialrate.SpecialRateRoomDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.specialrate.SpecialRateValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateExcludeCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelContractRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomCategoryRepositoy;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomTypeRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelSpecialRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomTypeRepository;

import jakarta.validation.Valid;

@Service
public class HotelSpecialRateService  implements HotelSpecialRateServiceInterface{
	
	private final HotelSpecialRateRepository specialRateRepository;
	
	private final HotelRepository hotelRepository;

	private final HotelRoomCategoryRepositoy roomCategoryRepo;
	
	private final HotelRoomTypeRepository roomTypeRepo;
	
	private final OccupancyRepository occupancyRepo;
	
	private MasterMarketTypeRepository masterMarketTypeRepository;
	
	private MasterRoomCategoryRepository MasterRoomCategoryRepository;
	
	private MasterRoomTypeRepository masterRoomTypeRepository;
	
	public HotelSpecialRateService(HotelSpecialRateRepository specialRateRepository,HotelRepository hotelRepository,
			HotelRoomCategoryRepositoy roomCategoryRepo,HotelRoomTypeRepository roomTypeRepo,
			OccupancyRepository occupancyRepo,MasterMarketTypeRepository masterMarketTypeRepository) {
		this.specialRateRepository=specialRateRepository;
		this.hotelRepository=hotelRepository;
		this.roomCategoryRepo=roomCategoryRepo;
		this.roomTypeRepo=roomTypeRepo;
		this.occupancyRepo=occupancyRepo;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
	}

	@Override
	public Long saveSpecialRate(@Valid SpecialRateDTO specialRateDTO) {
		// TODO Auto-generated method stub
		if(specialRateDTO==null) {
			throw new MissingRequestBodyException("specialRateDTO cannot be null");
		}
	    SpecialRate entity = new SpecialRate();
	    entity.setRateCode(specialRateDTO.getRateCode());
	    entity.setIsAllDays(specialRateDTO.getAllDays());
	    entity.setIsWeekDay(specialRateDTO.getWeekDay());
	    entity.setIsWeekEnd(specialRateDTO.getWeekEnd());
	    entity.setSeasonId(specialRateDTO.getSeasonId());
	    entity.setExcludeCountry(specialRateDTO.getExcludeCountry());
	    entity.setBookDate(specialRateDTO.getBookDate());
	    entity.setLengthStay(specialRateDTO.getLengthStay());
	    entity.setBookDay(specialRateDTO.getBookDay());
	    entity.setRemark(specialRateDTO.getRemark());
	    entity.setIsRefund(specialRateDTO.getIsRefund());
		Hotel hotel = hotelRepository.findById(specialRateDTO.getHotelId()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " +specialRateDTO.getHotelId()));

		entity.setHotel(hotel);
		System.out.println("mar");
	    // 1. MarketTypes
		List<SpecialRateMarketType> marketTypes = specialRateDTO.getMarketype().stream()
	        .map(mid -> {
	        	SpecialRateMarketType mt = new SpecialRateMarketType();
	    		MasterMarketType marketData = masterMarketTypeRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            mt.setSpecialRate(entity);
	            return mt;
	        }).collect(Collectors.toList());
 			entity.setSpecialRateMarketTypes(marketTypes);
	    

	    System.out.println("val");
	    // 2. Validity
 		List<SpecialRateValidity> validities = specialRateDTO.getSpecialRateValidityDTO().stream()
	        .map(v -> {
	            SpecialRateValidity validity = new SpecialRateValidity();
	            validity.setSpecialRate(entity);
	            validity.setIsType(v.getIsType());
	            
	            if(validity.getIsType()!=null && "V".equals(validity.getIsType())) {
	                validity.setValidityFrom(v.getValidityFrom());
		            validity.setValidityTo(v.getValidityTo());
	            }
	            else
	            {
	            	validity.setBlackOutFrom(v.getValidityFrom());
	            	validity.setBlackOutTo(v.getValidityTo());
	            	
	            }
	 
	            return validity;
	        }).collect(Collectors.toList());
 		entity.setSpecialRateValidities(validities);
 		System.out.println("room");
	    // 3. Room Rates
 		List<SpecialRateRoom> rooms = specialRateDTO.getSpecialRateRoomDTO().stream()
	        .map(r -> {
	            SpecialRateRoom room = new SpecialRateRoom();
	            room.setSpecialRate(entity);

	            room.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                                    .orElseThrow(() -> new EntityNotFoundException("Room Category not found")));
	            room.setRoomType(roomTypeRepo.findById(r.getHotelRoomTypeId())
	                                  .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));
	            room.setHotelOccupancy(occupancyRepo.findById(r.getOcuppancyTypeIid())
	                                  .orElseThrow(() -> new EntityNotFoundException("Occupancy not found")));

	            room.setRate(r.getRate());
	            room.setIsExtraBed(r.getExtraBed());
	            room.setMeal(r.getMeal());
	            if (r.getAdultrate()!= null&&r.getExtraBed()==true) {
	                room.setAdultrate(r.getAdultrate());
	            }
	            if (r.getChildrate() != null&&r.getExtraBed()==true) {
	                room.setChildrate(r.getChildrate());
	            }

	            return room;
	        }).collect(Collectors.toList());
	    entity.setSpecialRateRooms(rooms);
	    System.out.println("entere");
	    SpecialRate save = specialRateRepository.save(entity);
	     if(save.getSpecialRateId()==null || save.getSpecialRateId()==0) {
	    	 return null;
	     }
	     return save.getSpecialRateId(); 
	}

	@Override
	public SpecialRateDTO getSpecialRate(Long id) {
	    // Fetch SpecialRate entity by ID
	    SpecialRate entity = specialRateRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Special rate not found with id: " + id));

	    // Main DTO
	    SpecialRateDTO dto = new SpecialRateDTO();
	    dto.setSpecialRateId(entity.getSpecialRateId());
	    dto.setRateCode(entity.getRateCode());
	    dto.setAllDays(entity.getIsAllDays());
	    dto.setWeekDay(entity.getIsWeekDay());
	    dto.setWeekEnd(entity.getIsWeekEnd());
	    dto.setSeasonId(entity.getSeasonId());
	    dto.setExcludeCountry(entity.getExcludeCountry());
	    dto.setBookDate(entity.getBookDate());
	    dto.setLengthStay(entity.getLengthStay());
	    dto.setBookDay(entity.getBookDay());
	    dto.setRemark(entity.getRemark());
	    dto.setIsRefund(entity.getIsRefund());

	    if (entity.getHotel() != null) {
	        dto.setHotelId(entity.getHotel().getHotelId());
	    }

	    // 1. Market Types
	    dto.setMarketype(
	            entity.getSpecialRateMarketTypes().stream()
	                    .map(mt -> mt.getMarketType().getMarketTypeId()) // assuming MasterMarketType has getId()
	                    .collect(Collectors.toList())
	    );

	    // 2. Validities
	    dto.setSpecialRateValidityDTO(
	            entity.getSpecialRateValidities().stream()
	                    .map(v -> {
	                        SpecialRateValidityDTO validityDTO = new SpecialRateValidityDTO();
	                        validityDTO.setIsType(v.getIsType());
	                        validityDTO.setValidity_id(v.getSpecialRateValidityId());
	                        if ("V".equals(v.getIsType())) {
	                            validityDTO.setValidityFrom(v.getValidityFrom());
	                            validityDTO.setValidityTo(v.getValidityTo());
	                        } else {
	                            validityDTO.setValidityFrom(v.getBlackOutFrom());
	                            validityDTO.setValidityTo(v.getBlackOutTo());
	                        }
	                        return validityDTO;
	                    })
	                    .collect(Collectors.toList())
	    );

	    // 3. Rooms
	    dto.setSpecialRateRoomDTO(
	            entity.getSpecialRateRooms().stream()
	                    .map(r -> {
	                        SpecialRateRoomDTO roomDTO = new SpecialRateRoomDTO();
	                        roomDTO.setSpecialRateRoomId(r.getSpecialRateRoomId());
	                        roomDTO.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
	                        roomDTO.setHotelRoomTypeId(r.getRoomType().getHotelRoomTypeId());
	                        roomDTO.setOcuppancyTypeIid(r.getHotelOccupancy().getId());
	                        roomDTO.setRate(r.getRate());
	                        roomDTO.setExtraBed(r.getIsExtraBed());
	                        roomDTO.setMeal(r.isMeal());
	                        roomDTO.setAdultrate(r.getAdultrate());
	                        roomDTO.setChildrate(r.getChildrate());
	                        return roomDTO;
	                    })
	                    .collect(Collectors.toList())
	    );

	    return dto;
	}


	@Override
	public SpecialRateDTO editSpecialRate(Long id, @Valid SpecialRateDTO specialRateDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteSpecialRate(Long id) {
	    SpecialRate entity = specialRateRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Special rate not found with id: " + id));

	    specialRateRepository.delete(entity);

	    return ResponseEntity
	            .ok("Special rate deleted successfully with id: " + id);
	}
	@Override
	public Page<SpecialRateDTO> getAllSpecialRate(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
