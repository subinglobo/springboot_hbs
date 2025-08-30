package com.choosenfly.hotelbookingsystem.inventory.service.staypay;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.inventory.dto.staypay.StayPayPromotionDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.staypay.StaypayRoomDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.staypay.StaypayValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StayPay;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StaypayMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StaypayRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StaypayValidity;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.InvalidDataException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.SpecialRatePersistException;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomCategoryRepositoy;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomTypeRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelSpecialRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelStaypayRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomTypeRepository;

import jakarta.validation.Valid;

@Service
public class HotelStaypayService implements HotelStaypayServiceInterface{
	
	private final HotelStaypayRepository hotelStaypayRepository;
	
	private final HotelRepository hotelRepository;

	private final HotelRoomCategoryRepositoy roomCategoryRepo;
	
	private final HotelRoomTypeRepository roomTypeRepo;
	
	private MasterMarketTypeRepository masterMarketTypeRepository;
	
	private MasterRoomCategoryRepository MasterRoomCategoryRepository;
	
	private MasterRoomTypeRepository masterRoomTypeRepository;
	
	public HotelStaypayService(HotelStaypayRepository hotelStaypayRepository,HotelRepository hotelRepository,
			HotelRoomCategoryRepositoy roomCategoryRepo,HotelRoomTypeRepository roomTypeRepo,
			MasterMarketTypeRepository masterMarketTypeRepository) {
		this.hotelStaypayRepository=hotelStaypayRepository;
		this.hotelRepository=hotelRepository;
		this.roomCategoryRepo=roomCategoryRepo;
		this.roomTypeRepo=roomTypeRepo;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
	}

	@Override
	public Long saveStayPay(@Valid StayPayPromotionDTO stayPayDTO) {
		// TODO Auto-generated method stub
		
		if(stayPayDTO==null) {
			throw new MissingRequestBodyException("stayPayDTO cannot be null");
		}
		
//		if(specialRateDTO.getRateCode()== null || specialRateDTO.getRateCode().isEmpty())
//		{
//			throw new MissingRequestBodyException("Rate code is required");
//		}
	    StayPay entity = new StayPay();
	    entity.setBookDate(stayPayDTO.getBookDate());
	    entity.setBookDay(stayPayDTO.getBookDay());
	    entity.setIsAllDays(stayPayDTO.isAllDays());
	    entity.setIsRefund(stayPayDTO.isRefund());
	    entity.setIsWeekDay(stayPayDTO.isWeekDay());
	    entity.setIsWeekEnd(stayPayDTO.isWeekEnd()); 
	    entity.setPromotionfor(stayPayDTO.getPromotionfor());
	    entity.setRateCode(stayPayDTO.getRateCode());
	    entity.setRemark(stayPayDTO.getRemark());
	    entity.setSeasonId(stayPayDTO.getSeasonId());

		Hotel hotel = hotelRepository.findById(stayPayDTO.getHotelId()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " +stayPayDTO.getHotelId()));

		entity.setHotel(hotel);
	    // 1. MarketTypes
		
		if(stayPayDTO.getMarketype()==null || stayPayDTO.getMarketype().isEmpty()) {
			throw new InvalidDataException("market type cannot be null or empty");
		}
        List<StaypayMarketType> marketTypes = stayPayDTO.getMarketype().stream()
	        .map(mid -> {
	        	StaypayMarketType mt = new StaypayMarketType();
	    		MasterMarketType marketData = masterMarketTypeRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            mt.setStayPay(entity);
	            return mt;
	        }).collect(Collectors.toList());
 			entity.setMarketTypes(marketTypes);
	    

	    // 2. Validity
 			List<StaypayValidity> validities = stayPayDTO.getPromotionValidityDTO().stream()
	        .map(v -> {
	           StaypayValidity validity = new StaypayValidity();
	            validity.setStayPay(entity);
	            validity.setIsType(v.getIsType());
	                validity.setValidityFrom(v.getValidityFrom());
		            validity.setValidityTo(v.getValidityTo());
	            return validity;
	        }).collect(Collectors.toList());
 		entity.setValidities(validities);
	   
 		// 3. Room Rates
 		List<StaypayRoom> rooms = stayPayDTO.getPromotionRoomDTO().stream()
	        .map(r -> {
	            StaypayRoom room = new StaypayRoom();
	            room.setStayPay(entity);

	            room.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                                    .orElseThrow(() -> new EntityNotFoundException("Room Category not found")));
	            room.setRoomType(roomTypeRepo.findById(r.getHotelRoomtypeId())
	                                  .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));

	           
	            room.setNoOffree(r.getNoOffree());
	            room.setNoOfpay(r.getNoOfpay());
	            room.setNoOfStay(r.getNoOfstay());
	            return room;
	        }).collect(Collectors.toList());
	    entity.setStaypayRooms(rooms);
	    
	    System.out.println("entere");
	   StayPay save = hotelStaypayRepository.save(entity);
	     if(save.getStaypayId()==null || save.getStaypayId()==0) {
	    	throw new SpecialRatePersistException("Failed to save Special Rate");
	     }
	     return save.getStaypayId(); 
	}

	@Override
	public StayPayPromotionDTO getstayPay(Long id) {
		// TODO Auto-generated method stub
		  if (id == null || id <= 0) {
		        throw new InvalidDataException("StayPay id must be a valid positive number");
		    }

		    // 2. Find entity or throw exception
		    StayPay stayPay = hotelStaypayRepository.findById(id)
		            .orElseThrow(() -> new EntityNotFoundException("StayPay not found with id: " + id));
		    
		    StayPayPromotionDTO covertToDTO = covertToDTO(stayPay);
		   
		    return covertToDTO;
		   
	}

	private StayPayPromotionDTO covertToDTO(StayPay stayPay) {
		// TODO Auto-generated method stub
		 // 3. Convert entity to DTO
	    StayPayPromotionDTO dto = new StayPayPromotionDTO();
	    dto.setStaypayId(stayPay.getStaypayId());
	    dto.setBookDate(stayPay.getBookDate());
	    dto.setBookDay(stayPay.getBookDay());
	    dto.setAllDays(stayPay.getIsAllDays());
	    dto.setRefund(stayPay.getIsRefund());
	    dto.setWeekDay(stayPay.getIsWeekDay());
	    dto.setWeekEnd(stayPay.getIsWeekEnd());
	    dto.setPromotionfor(stayPay.getPromotionfor());
	    dto.setRateCode(stayPay.getRateCode());
	    dto.setRemark(stayPay.getRemark());
	    dto.setSeasonId(stayPay.getSeasonId());
	    dto.setHotelId(stayPay.getHotel().getHotelId());

	    // Market Types
	    if (stayPay.getMarketTypes() != null) {
	        List<Long> marketypeIds = stayPay.getMarketTypes().stream()
	                .map(mt -> mt.getMarketType().getMarketTypeId())
	                .collect(Collectors.toList());
	        dto.setMarketype(marketypeIds);
	    }

	    // Validities
	    if (stayPay.getValidities() != null) {
	        List<StaypayValidityDTO> validityDTOs = stayPay.getValidities().stream()
	                .map(v -> {
	                	StaypayValidityDTO valDto = new StaypayValidityDTO();
	                    valDto.setPromoValidityId(v.getStaypayValidityId());
	                    valDto.setValidityFrom(v.getValidityFrom());
	                    valDto.setValidityTo(v.getValidityTo());
	                    valDto.setIsType(v.getIsType());
	                    valDto.setDeleted(0); // assuming not deleted
	                    return valDto;
	                }).collect(Collectors.toList());
	        dto.setPromotionValidityDTO(validityDTOs);
	    }

	    // Rooms
	    if (stayPay.getStaypayRooms() != null) {
	         List<StaypayRoomDTO> roomDTOs = stayPay.getStaypayRooms().stream()
	                .map(r -> {
	                	StaypayRoomDTO roomDto = new StaypayRoomDTO();
	                    roomDto.setPromoRoomId(r.getStaypayRoomId());
	                    roomDto.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
	                    roomDto.setHotelRoomtypeId(r.getRoomType().getHotelRoomTypeId());
	                    roomDto.setNoOffree(r.getNoOffree());
	                    roomDto.setNoOfpay(r.getNoOfpay());
	                    roomDto.setNoOfstay(r.getNoOfStay());
	                    return roomDto;
	                }).collect(Collectors.toList());
	        dto.setPromotionRoomDTO(roomDTOs);
	    }

	    return dto;
	}

	@Override
	public StayPayPromotionDTO editstayPay(Long id, @Valid StayPayPromotionDTO stayPayDTO) {
	    // 1. Validate input
	    if (id == null || id <= 0) {
	        throw new InvalidDataException("StayPay id must be a valid positive number");
	    }
	    if (stayPayDTO == null) {
	        throw new MissingRequestBodyException("stayPayDTO cannot be null");
	    }

	    // 2. Fetch entity
	    StayPay entity = hotelStaypayRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("StayPay not found with id: " + id));

	    // 3. Update simple fields
	    entity.setBookDate(stayPayDTO.getBookDate());
	    entity.setBookDay(stayPayDTO.getBookDay());
	    entity.setIsAllDays(stayPayDTO.isAllDays());
	    entity.setIsRefund(stayPayDTO.isRefund());
	    entity.setIsWeekDay(stayPayDTO.isWeekDay());
	    entity.setIsWeekEnd(stayPayDTO.isWeekEnd());
	    entity.setPromotionfor(stayPayDTO.getPromotionfor());
	    entity.setRateCode(stayPayDTO.getRateCode());
	    entity.setRemark(stayPayDTO.getRemark());
	    entity.setSeasonId(stayPayDTO.getSeasonId());

	    // 4. Update hotel reference
	    Hotel hotel = hotelRepository.findById(stayPayDTO.getHotelId())
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + stayPayDTO.getHotelId()));
	    entity.setHotel(hotel);

	    // 5. Update Market Types
	    if (stayPayDTO.getMarketype() == null || stayPayDTO.getMarketype().isEmpty()) {
	        throw new InvalidDataException("market type cannot be null or empty");
	    }
	    List<StaypayMarketType> marketTypes = stayPayDTO.getMarketype().stream()
	            .map(mid -> {
	                StaypayMarketType mt = new StaypayMarketType();
	                MasterMarketType marketData = masterMarketTypeRepository.findById(mid)
	                        .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	                mt.setMarketType(marketData);
	                mt.setStayPay(entity);
	                return mt;
	            }).collect(Collectors.toList());
	    entity.getMarketTypes().clear(); // ✅ orphanRemoval will delete old ones
	    entity.getMarketTypes().addAll(marketTypes);

	    // 6. Update Validities
	    List<StaypayValidity> validities = stayPayDTO.getPromotionValidityDTO().stream()
	            .map(v -> {
	                StaypayValidity validity = new StaypayValidity();
	                validity.setStayPay(entity);
	                validity.setIsType(v.getIsType());
	                validity.setValidityFrom(v.getValidityFrom());
	                validity.setValidityTo(v.getValidityTo());
	                return validity;
	            }).collect(Collectors.toList());
	    entity.getValidities().clear(); // ✅ orphanRemoval will delete old ones
	    entity.getValidities().addAll(validities);
	    // 7. Update Rooms
	    List<StaypayRoom> rooms = stayPayDTO.getPromotionRoomDTO().stream()
	            .map(r -> {
	                StaypayRoom room = new StaypayRoom();
	                room.setStayPay(entity);
	                room.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                        .orElseThrow(() -> new EntityNotFoundException("Room Category not found")));
	                room.setRoomType(roomTypeRepo.findById(r.getHotelRoomtypeId())
	                        .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));
	                room.setNoOffree(r.getNoOffree());
	                room.setNoOfpay(r.getNoOfpay());
	                room.setNoOfStay(r.getNoOfstay());
	                return room;
	            }).collect(Collectors.toList());
	    entity.getStaypayRooms().clear(); // ✅ orphanRemoval will delete old ones
	    entity.getStaypayRooms().addAll(rooms);

	    // 8. Save updated entity
	    StayPay updated = hotelStaypayRepository.save(entity);

	    if (updated.getStaypayId() == null || updated.getStaypayId() == 0) {
	        throw new SpecialRatePersistException("Failed to update StayPay promotion");
	    }
	    StayPayPromotionDTO covertToDTO = covertToDTO(updated);
	    // 9. Convert back to DTO (reuse your getstayPay method for consistency)
	    return covertToDTO;
	}


	@Override
	public ResponseEntity<String> deletestayPay(Long id) {
	    // 1. Check if record exists
	   StayPay stayPayEntity = hotelStaypayRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("StayPayPromotion not found with id: " + id));

	    // 2. Delete the record
	    hotelStaypayRepository.delete(stayPayEntity);

	    // 3. Return response
	    return ResponseEntity.ok("StayPayPromotion with id " + id + " deleted successfully.");
	}

	@Override
	public Page<StayPayPromotionDTO> getAllstayPay(Pageable pageable, String search) {

	    Page<StayPay> stayPayPage;

	    // If search is provided, you can filter (example: by rateCode). Otherwise fetch all.
	        stayPayPage = hotelStaypayRepository.findAll(pageable);
	        
			Page<StayPay> findAll = hotelStaypayRepository.findAll(
				    PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "staypayId"))
				);


	    // Convert StayPay entity -> StayPayPromotionDTO
	    return stayPayPage.map(entity -> {
	        StayPayPromotionDTO dto = new StayPayPromotionDTO();
	        dto.setStaypayId(entity.getStaypayId());
	        dto.setRateCode(entity.getRateCode());
	        dto.setAllDays(entity.getIsAllDays());
	        dto.setWeekDay(entity.getIsWeekDay());
	        dto.setWeekEnd(entity.getIsWeekEnd());
	        dto.setSeasonId(entity.getSeasonId());
//	        dto.setExcludeCountry(entity.g);
	        dto.setBookDate(entity.getBookDate());
	        dto.setBookDay(entity.getBookDay());
	        dto.setRemark(entity.getRemark());
	        dto.setRefund(entity.getIsRefund());
	        dto.setPromotionfor(entity.getPromotionfor());
	        dto.setHotelId(entity.getHotel().getHotelId());

	        // Market Types
	        dto.setMarketype(entity.getMarketTypes()
	                .stream()
	                .map(mt -> mt.getMarketType().getMarketTypeId())
	                .collect(Collectors.toList())
	        );

	        // Validities
	        dto.setPromotionValidityDTO(entity.getValidities().stream()
	                .map(v -> {
	                    StaypayValidityDTO vDto = new StaypayValidityDTO();
	                    vDto.setPromoValidityId(v.getStaypayValidityId());
	                    vDto.setIsType(v.getIsType());
	                    vDto.setValidityFrom(v.getValidityFrom());
	                    vDto.setValidityTo(v.getValidityTo());
	                    return vDto;
	                })
	                .collect(Collectors.toList())
	        );

	        // Rooms
	        dto.setPromotionRoomDTO(entity.getStaypayRooms().stream()
	                .map(r -> {
	                    StaypayRoomDTO rDto = new StaypayRoomDTO();
	                    rDto.setPromoRoomId(r.getStaypayRoomId());
	                    rDto.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
	                    rDto.setHotelRoomtypeId(r.getRoomType().getHotelRoomTypeId());
	                    rDto.setNoOffree(r.getNoOffree());
	                    rDto.setNoOfpay(r.getNoOfpay());
	                    rDto.setNoOfstay(r.getNoOfStay());
	                    return rDto;
	                })
	                .collect(Collectors.toList())
	        );

	        return dto;
	    });
	}

}
