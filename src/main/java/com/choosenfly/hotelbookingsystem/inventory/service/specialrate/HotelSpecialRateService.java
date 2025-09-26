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
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateExcludeCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountExcludedCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateCombined;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateExcludeCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StayPay;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.InvalidDataException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.SpecialRatePersistException;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelContractRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelDiscountRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomCategoryRepositoy;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomTypeRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelSpecialRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelStaypayRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
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
	
	private final HotelDiscountRepository discountRepository;
	
	private final HotelStaypayRepository hotelStaypayRepository;
	
	private MasterCountryRepository countryRepository;
	
	public HotelSpecialRateService(HotelSpecialRateRepository specialRateRepository,HotelRepository hotelRepository,
			HotelRoomCategoryRepositoy roomCategoryRepo,HotelRoomTypeRepository roomTypeRepo,
			OccupancyRepository occupancyRepo,MasterMarketTypeRepository masterMarketTypeRepository,
			HotelDiscountRepository discountRepository,HotelStaypayRepository hotelStaypayRepository,
			MasterCountryRepository countryRepository) {
		this.specialRateRepository=specialRateRepository;
		this.hotelRepository=hotelRepository;
		this.roomCategoryRepo=roomCategoryRepo;
		this.roomTypeRepo=roomTypeRepo;
		this.occupancyRepo=occupancyRepo;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
		this.discountRepository=discountRepository;
		this.hotelStaypayRepository=hotelStaypayRepository;
		this.countryRepository=countryRepository;
	}

	@Override
	public Long saveSpecialRate(@Valid SpecialRateDTO specialRateDTO) {
		// TODO Auto-generated method stub
		if(specialRateDTO==null) {
			throw new MissingRequestBodyException("specialRateDTO cannot be null");
		}
		
//		if(specialRateDTO.getRateCode()== null || specialRateDTO.getRateCode().isEmpty())
//		{
//			throw new MissingRequestBodyException("Rate code is required");
//		}
	    SpecialRate entity = new SpecialRate();
	    entity.setRateCode(specialRateDTO.getRateCode());
	    entity.setIsAllDays(specialRateDTO.getAllDays());
	    entity.setIsWeekDay(specialRateDTO.getWeekDay());
	    entity.setIsWeekEnd(specialRateDTO.getWeekEnd());
	    entity.setSeasonId(specialRateDTO.getSeasonId());
//	    entity.setExcludeCountry(specialRateDTO.getExcludeCountry());
	    entity.setBookDate(specialRateDTO.getBookDate());
	    entity.setLengthStay(specialRateDTO.getLengthStay());
	    entity.setBookDay(specialRateDTO.getBookDay());
	    entity.setRemark(specialRateDTO.getRemark());
	    entity.setIsRefund(specialRateDTO.getIsRefund());
		Hotel hotel = hotelRepository.findById(specialRateDTO.getHotelId()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " +specialRateDTO.getHotelId()));

		entity.setHotel(hotel);
		
		// Excluded countries
		List<SpecialRateExcludeCountry> excludedCountries = Optional.ofNullable(specialRateDTO.getExcludeCountrys())
		        .orElse(Collections.emptyList())
		        .stream()
		        .map(countryId -> {
		        	SpecialRateExcludeCountry excludeCountry = new SpecialRateExcludeCountry();
//		            DiscountExcludedCountry excludeCountry = new DiscountExcludedCountry();
		            
		            // Fetch the MasterCountry entity
		             MasterCountry country = countryRepository.findById(countryId)
		                    .orElseThrow(() -> new EntityNotFoundException("Country not found for id: " + countryId));
		            
		            excludeCountry.setCountry(country); // set entity
		            excludeCountry.setSpecialRate(entity);  // associate with discount
		            return excludeCountry;
		        })
		        .collect(Collectors.toList());

		// Set to discount entity
		entity.setSpecialRateExcludeCountries(excludedCountries);
		
	    // 1. MarketTypes
		
		if(specialRateDTO.getMarketype()==null || specialRateDTO.getMarketype().isEmpty()) {
			throw new InvalidDataException("market type cannot be null or empty");
		}
		List<SpecialRateMarketType> marketTypes = specialRateDTO.getMarketype().stream()
	        .map(mid -> {
	        	SpecialRateMarketType mt = new SpecialRateMarketType();
	    		MasterMarketType marketData = masterMarketTypeRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            mt.setSpecialRate(entity);
	            return mt;
	        }).collect(Collectors.toList());
 			entity.setSpecialRateMarketTypes(marketTypes);
	    

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
//	            room.setMeal(r.getMeal());
	            if (r.getAdultrate()!= null&&r.getExtraBed()==true) {
	                room.setAdultrate(r.getAdultrate());
	            }
	            if (r.getChildrate() != null&&r.getExtraBed()==true) {
	                room.setChildrate(r.getChildrate());
	            }

	            return room;
	        }).collect(Collectors.toList());
	    entity.setSpecialRateRooms(rooms);

	    if (specialRateDTO.getCombinedPromoId() != null && specialRateDTO.getCombinedPromoId() > 0L 
	    	    && specialRateDTO.getPromotype() != null && !specialRateDTO.getPromotype().isEmpty()) {
	    	System.out.println("DSR entered");
	    	SpecialRateCombined combined = new SpecialRateCombined();
	    	combined.setPromotype(specialRateDTO.getPromotype());

	    	if ("DSR".equalsIgnoreCase(specialRateDTO.getPromotype())) {
	    	    DiscountRate discount = discountRepository.findById(specialRateDTO.getCombinedPromoId())
	    	        .orElseThrow(() -> new EntityNotFoundException("Promo id not found"));
	    	    combined.setPromotion(discount);
	    	    combined.setPromotype("DSR");
	    	} else if ("SAP".equalsIgnoreCase(specialRateDTO.getPromotype())) {
	    	    StayPay staypay = hotelStaypayRepository.findById(specialRateDTO.getCombinedPromoId())
	    	        .orElseThrow(() -> new EntityNotFoundException("Promo id not found"));
	    	    combined.setPromotion(staypay);
	    	    combined.setPromotype("SAP");
	    	}

	    	combined.setSpecialRate(entity);
	    	entity.setSpecialRateCombined(combined);

	    	
	    }
	    SpecialRate save = specialRateRepository.save(entity);
	     if(save.getSpecialRateId()==null || save.getSpecialRateId()==0) {
	    	throw new SpecialRatePersistException("Failed to save Special Rate");
	     }
	     return save.getSpecialRateId(); 
	}

	@Override
	public SpecialRateDTO getSpecialRate(Long id) {
	    // Fetch SpecialRate entity by ID
	    SpecialRate entity = specialRateRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Special rate not found with id: " + id));
	    
	    return convertToDTO(entity);

	}


	private SpecialRateDTO convertToDTO(SpecialRate entity) {
		// TODO Auto-generated method stub

	    // Main DTO
	    SpecialRateDTO dto = new SpecialRateDTO();
	    dto.setSpecialRateId(entity.getSpecialRateId());
	    dto.setRateCode(entity.getRateCode());
	    dto.setAllDays(entity.getIsAllDays());
	    dto.setWeekDay(entity.getIsWeekDay());
	    dto.setWeekEnd(entity.getIsWeekEnd());
	    dto.setSeasonId(entity.getSeasonId());
//	    dto.setExcludeCountry(entity.getExcludeCountry());
	    dto.setBookDate(entity.getBookDate());
	    dto.setLengthStay(entity.getLengthStay());
	    dto.setBookDay(entity.getBookDay());
	    dto.setRemark(entity.getRemark());
	    dto.setIsRefund(entity.getIsRefund());
	    dto.setPromotype(entity.getSpecialRateCombined().getPromotype());
	    if (entity.getSpecialRateCombined() != null) {
	        dto.setPromotype(entity.getSpecialRateCombined().getPromotype());

	        Object promo = entity.getSpecialRateCombined().getPromotion();
	        if (promo instanceof DiscountRate) {
	            dto.setCombinedPromoId(((DiscountRate) promo).getDiscountId());
	        } else if (promo instanceof StayPay) {
	            dto.setCombinedPromoId(((StayPay) promo).getStaypayId());
	        }
	    }
	    if (entity.getHotel() != null) {
	        dto.setHotelId(entity.getHotel().getHotelId());
	    }
	    
	    
	     List<Long> countries = entity.getSpecialRateExcludeCountries().stream()
	    	.map(exc -> exc.getCountry().getId())
	    	.collect(Collectors.toList());
	    dto.setExcludeCountrys(countries);
	    

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
//	                        roomDTO.setMeal(r.isMeal());
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
		if(specialRateDTO==null) {
			throw new MissingRequestBodyException("specialRateDTO cannot be null");
		}
		
		if(id==null || id==0) {
			throw new MissingRequestBodyException("Special rate id is required");
		}
		if(specialRateDTO.getRateCode()== null || specialRateDTO.getRateCode().isEmpty())
		{
			throw new MissingRequestBodyException("Rate code is required");
		}

	    SpecialRate entity = specialRateRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Special rate not found with id: " + id));

	    // Update main entity fields
	    entity.setRateCode(specialRateDTO.getRateCode());
	    entity.setIsAllDays(specialRateDTO.getAllDays());
	    entity.setIsWeekDay(specialRateDTO.getWeekDay());
	    entity.setIsWeekEnd(specialRateDTO.getWeekEnd());
	    entity.setSeasonId(specialRateDTO.getSeasonId());
//	    entity.setExcludeCountry(specialRateDTO.getExcludeCountry());
	    entity.setBookDate(specialRateDTO.getBookDate());
	    entity.setLengthStay(specialRateDTO.getLengthStay());
	    entity.setBookDay(specialRateDTO.getBookDay());
	    entity.setRemark(specialRateDTO.getRemark());
	    entity.setIsRefund(specialRateDTO.getIsRefund());

	    // Update hotel
	    Hotel hotel = hotelRepository.findById(specialRateDTO.getHotelId())
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + specialRateDTO.getHotelId()));
	    entity.setHotel(hotel);
	    
		// Excluded countries
		List<SpecialRateExcludeCountry> excludedCountries = Optional.ofNullable(specialRateDTO.getExcludeCountrys())
		        .orElse(Collections.emptyList())
		        .stream()
		        .map(countryId -> {
		        	SpecialRateExcludeCountry excludeCountry = new SpecialRateExcludeCountry();
//		            DiscountExcludedCountry excludeCountry = new DiscountExcludedCountry();
		            
		            // Fetch the MasterCountry entity
		             MasterCountry country = countryRepository.findById(countryId)
		                    .orElseThrow(() -> new EntityNotFoundException("Country not found for id: " + countryId));
		            
		            excludeCountry.setCountry(country); // set entity
		            excludeCountry.setSpecialRate(entity);  // associate with discount
		            return excludeCountry;
		        })
		        .collect(Collectors.toList());

		// Set to discount entity
		if (entity.getSpecialRateExcludeCountries() != null) {
		    entity.getSpecialRateExcludeCountries().clear();
		    entity.getSpecialRateExcludeCountries().addAll(excludedCountries);
		} else {
		    entity.setSpecialRateExcludeCountries(excludedCountries);
		}

	    // 1. Market Types
	    List<SpecialRateMarketType> marketTypes = specialRateDTO.getMarketype().stream()
	            .map(mid -> {
	                SpecialRateMarketType mt = new SpecialRateMarketType();
	                MasterMarketType marketData = masterMarketTypeRepository.findById(mid)
	                        .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id: " + mid));
	                mt.setMarketType(marketData);
	                mt.setSpecialRate(entity);
	                return mt;
	            }).collect(Collectors.toList());
	    if(entity.getSpecialRateMarketTypes()!=null) {
	    	entity.getSpecialRateMarketTypes().clear();
	    	entity.getSpecialRateMarketTypes().addAll(marketTypes);
	    }
	    else {
	    	entity.setSpecialRateMarketTypes(marketTypes);
	    }

	    // 2. Validity
	    List<SpecialRateValidity> validities = specialRateDTO.getSpecialRateValidityDTO().stream()
	            .map(v -> {
	                SpecialRateValidity validity = new SpecialRateValidity();
	                validity.setSpecialRate(entity);
	                validity.setIsType(v.getIsType());

	                if ("V".equals(validity.getIsType())) {
	                    validity.setValidityFrom(v.getValidityFrom());
	                    validity.setValidityTo(v.getValidityTo());
	                } else {
	                    validity.setBlackOutFrom(v.getValidityFrom());
	                    validity.setBlackOutTo(v.getValidityTo());
	                }
	                return validity;
	            }).collect(Collectors.toList());
	    if(entity.getSpecialRateValidities()!=null) {
	    	entity.getSpecialRateValidities().clear();
	    	entity.getSpecialRateValidities().addAll(validities);
	    }
	    else {
	    entity.setSpecialRateValidities(validities);
	    }
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
//	                room.setMeal(r.getMeal());

	                if (r.getAdultrate() != null && Boolean.TRUE.equals(r.getExtraBed())) {
	                    room.setAdultrate(r.getAdultrate());
	                }
	                if (r.getChildrate() != null && Boolean.TRUE.equals(r.getExtraBed())) {
	                    room.setChildrate(r.getChildrate());
	                }
	                return room;
	            }).collect(Collectors.toList());
	    if(entity.getSpecialRateRooms()!=null) {
	    	entity.getSpecialRateRooms().clear();
	    	entity.getSpecialRateRooms().addAll(rooms);
	    }else {
	    	 entity.setSpecialRateRooms(rooms);
	    }


	    if (specialRateDTO.getCombinedPromoId() != null && specialRateDTO.getCombinedPromoId() > 0L 
	            && specialRateDTO.getPromotype() != null && !specialRateDTO.getPromotype().isEmpty()) {

	        SpecialRateCombined combined = entity.getSpecialRateCombined(); // fetch existing mapping
	        if (combined == null) {
	            combined = new SpecialRateCombined();
	            combined.setSpecialRate(entity);
	        }

	        combined.setPromotype(specialRateDTO.getPromotype());

	        if ("DSR".equalsIgnoreCase(specialRateDTO.getPromotype())) {
	            DiscountRate discount = discountRepository.findById(specialRateDTO.getCombinedPromoId())
	                .orElseThrow(() -> new EntityNotFoundException("Promo id not found"));
	            combined.setPromotion(discount);
	            combined.setPromotype("DSR");
	        } else if ("SAP".equalsIgnoreCase(specialRateDTO.getPromotype())) {
	            StayPay staypay = hotelStaypayRepository.findById(specialRateDTO.getCombinedPromoId())
	                .orElseThrow(() -> new EntityNotFoundException("Promo id not found"));
	            combined.setPromotion(staypay);
	            combined.setPromotype("SAP");
	        }

	        // re-attach combined back to SpecialRate
	        entity.setSpecialRateCombined(combined);
	    }

	    // Save updated entity
	    SpecialRate updated = specialRateRepository.save(entity);
	  


	    return convertToDTO(updated);
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

	   Page<SpecialRate>  specialRates = specialRateRepository.findAll(pageable);

	    // Convert SpecialRate entity to SpecialRateDTO with related lists
	    return specialRates.map(entity -> {
	        SpecialRateDTO dto = new SpecialRateDTO();
	        dto.setSpecialRateId(entity.getSpecialRateId());
	        dto.setRateCode(entity.getRateCode());
	        dto.setAllDays(entity.getIsAllDays());
	        dto.setWeekDay(entity.getIsWeekDay());
	        dto.setWeekEnd(entity.getIsWeekEnd());
	        dto.setSeasonId(entity.getSeasonId());
//	        dto.setExcludeCountry(entity.getExcludeCountry());
	        dto.setBookDate(entity.getBookDate());
	        dto.setLengthStay(entity.getLengthStay());
	        dto.setBookDay(entity.getBookDay());
	        dto.setRemark(entity.getRemark());
	        dto.setIsRefund(entity.getIsRefund());
	        dto.setHotelId(entity.getHotel().getHotelId());
	        
		    if (entity.getSpecialRateCombined() != null) {
		        dto.setPromotype(entity.getSpecialRateCombined().getPromotype());

		        Object promo = entity.getSpecialRateCombined().getPromotion();
		        if (promo instanceof DiscountRate) {
		            dto.setCombinedPromoId(((DiscountRate) promo).getDiscountId());
		        } else if (promo instanceof StayPay) {
		            dto.setCombinedPromoId(((StayPay) promo).getStaypayId());
		        }
		    }
	        
		     List<Long> countries = entity.getSpecialRateExcludeCountries().stream()
		 	    	.map(exc -> exc.getCountry().getId())
		 	    	.collect(Collectors.toList());
		 	    dto.setExcludeCountrys(countries);

	        // Market Types
	        dto.setMarketype(entity.getSpecialRateMarketTypes()
	                .stream()
	                .map(mt -> mt.getMarketType().getMarketTypeId())
	                .collect(Collectors.toList())
	        );

	        // Validities
	        dto.setSpecialRateValidityDTO(entity.getSpecialRateValidities()
	                .stream()
	                .map(v -> {
	                    SpecialRateValidityDTO vDto = new SpecialRateValidityDTO();
	                    vDto.setValidity_id(v.getSpecialRateValidityId());
	                    vDto.setIsType(v.getIsType());
	                    if ("V".equals(v.getIsType())) {
	                        vDto.setValidityFrom(v.getValidityFrom());
	                        vDto.setValidityTo(v.getValidityTo());
	                    } else {
	                        vDto.setValidityFrom(v.getBlackOutFrom());
	                        vDto.setValidityTo(v.getBlackOutTo());
	                    }
	                    return vDto;
	                })
	                .collect(Collectors.toList())
	        );

	        // Rooms
	        dto.setSpecialRateRoomDTO(entity.getSpecialRateRooms()
	                .stream()
	                .map(r -> {
	                    SpecialRateRoomDTO rDto = new SpecialRateRoomDTO();
	                    rDto.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
	                    rDto.setHotelRoomTypeId(r.getRoomType().getHotelRoomTypeId());
	                    rDto.setOcuppancyTypeIid(r.getHotelOccupancy().getId());
	                    rDto.setRate(r.getRate());
	                    rDto.setExtraBed(r.getIsExtraBed());
//	                    rDto.setHotelMealId(r.getHotelMealId());
	                    rDto.setAdultrate(r.getAdultrate());
	                    rDto.setChildrate(r.getChildrate());

	                    return rDto;
	                })
	                .collect(Collectors.toList())
	        );

	        return dto;
	    });
	}

}
