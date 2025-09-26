package com.choosenfly.hotelbookingsystem.inventory.service.discount;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidIsolationLevelException;

import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.dto.discount.DicountValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.discount.DiscountDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.discount.DiscountRoomDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateExcludeCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountExcludedCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountValidity;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.InvalidDataException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelContractRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelDiscountRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomCategoryRepositoy;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomTypeRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomTypeRepository;

import jakarta.validation.Valid;

@Service
public class HotelDiscountService implements  HotelDiscountServiceInterface{
	
	private final HotelDiscountRepository discountRepository;
	
	private final HotelRepository hotelRepository;

	private final HotelRoomCategoryRepositoy roomCategoryRepo;
	
	private final HotelRoomTypeRepository roomTypeRepo;
	
	
	private MasterMarketTypeRepository masterMarketTypeRepository;
	
	private MasterRoomCategoryRepository MasterRoomCategoryRepository;
	
	private MasterRoomTypeRepository masterRoomTypeRepository;
	
	private MasterCountryRepository countryRepository;

	public HotelDiscountService(HotelDiscountRepository discountRepository,HotelRepository hotelRepository
			,HotelRoomCategoryRepositoy roomCategoryRepo,HotelRoomTypeRepository roomTypeRepo,
			MasterMarketTypeRepository masterMarketTypeRepository,
			MasterRoomCategoryRepository MasterRoomCategoryRepository,
			MasterRoomTypeRepository masterRoomTypeRepository,
			MasterCountryRepository countryRepository) {
		this.discountRepository=discountRepository;
		this.hotelRepository=hotelRepository;
		this.roomCategoryRepo=roomCategoryRepo;
		this.roomTypeRepo=roomTypeRepo;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
		this.MasterRoomCategoryRepository=MasterRoomCategoryRepository;
		this.masterRoomTypeRepository=masterRoomTypeRepository;
		this.countryRepository=countryRepository;
	}


	@Override
	public Long saveDiscount(@Valid DiscountDTO discountDTO) {
		// TODO Auto-generated method stub
		System.out.println("discountDTO::::"+discountDTO);
		if(discountDTO==null) {
			throw new com.choosenfly.hotelbookingsystem.inventory.exceptions.MissingRequestBodyException("discountDTO cannot be null");
		}
		DiscountRate discountEnitity = new DiscountRate();
		Hotel hotel = hotelRepository.findById(discountDTO.getHotelId()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + discountDTO.getHotelId()));

		discountEnitity.setHotel(hotel);
		discountEnitity.setSeasonId(discountDTO.getSeasonId());
		discountEnitity.setRateCode(discountDTO.getRateCode());
		discountEnitity.setBookDate(discountDTO.getBookDate());
		discountEnitity.setBookDay(discountDTO.getBookDay());
		discountEnitity.setIsAllDays(discountDTO.getAllDays());
//		discountEnitity.setIsLive(discountDTO.getI);
		discountEnitity.setIsRefund(discountDTO.getRefund());
		discountEnitity.setIsWeekDay(discountDTO.getWeekDay());
		discountEnitity.setIsWeekEnd(discountDTO.getWeekEnd());
		discountEnitity.setExtraBed(discountDTO.getExtraBed());
//		discountEnitity.setPromotionMeals(discountDTO);
		discountEnitity.setPromotionRoom(null);
		
		discountEnitity.setRemark(discountDTO.getRemark());
		discountEnitity.setDiscountMarketTypes(null);
		discountEnitity.setDiscountValidities(null);
		
		
	    //excludeded countryList
		// Excluded countries
		List<DiscountExcludedCountry> excludedCountries = Optional.ofNullable(discountDTO.getExcludeCountry())
		        .orElse(Collections.emptyList())
		        .stream()
		        .map(countryId -> {
		            DiscountExcludedCountry excludeCountry = new DiscountExcludedCountry();
		            
		            // Fetch the MasterCountry entity
		             MasterCountry country = countryRepository.findById(countryId)
		                    .orElseThrow(() -> new EntityNotFoundException("Country not found for id: " + countryId));
		            
		            excludeCountry.setCountry(country); // set entity
		            excludeCountry.setDiscountRate(discountEnitity);  // associate with discount
		            return excludeCountry;
		        })
		        .collect(Collectors.toList());

		// Set to discount entity
		discountEnitity.setDiscountExcludedCountries(excludedCountries);
		
		if(discountDTO.getMarketype()==null||discountDTO.getMarketype().isEmpty()) {
			throw new InvalidDataException("market type cannot be null or empty");
		}

	    // 1. MarketTypes
	   List<DiscountMarketType> marketTypes = discountDTO.getMarketype().stream()
	        .map(mid -> {
	            DiscountMarketType mt = new DiscountMarketType();
	    		MasterMarketType marketData = masterMarketTypeRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            mt.setDiscountRate(discountEnitity);
	            return mt;
	        }).collect(Collectors.toList());
	    discountEnitity.setDiscountMarketTypes(marketTypes);
	    
	    // 2. Validity
	    List<DiscountValidity> validities = discountDTO.getValidityDTO().stream()
	        .map(v -> {
	        	DiscountValidity validity = new DiscountValidity();
	            validity.setDiscountRate(discountEnitity);
	            validity.setLive(false);
	            validity.setType(v.getIsType());
	            validity.setValidityFrom(v.getValidityFrom());
	            validity.setValidityTo(v.getValidityTo());
	            return validity;
	        }).collect(Collectors.toList());
	    discountEnitity.setDiscountValidities(validities);
	   
	    System.out.println("room");
	    // 3. Room Rates
	    if(discountDTO.getRoomDTO()!=null && !discountDTO.getRoomDTO().isEmpty()) {
	    List<DiscountRoom> rooms = discountDTO.getRoomDTO().stream()
	        .map(r -> {
	        	DiscountRoom room = new DiscountRoom();
	            room.setDiscountRate(discountEnitity);
	            if(r.getHotelRoomcategoryId()==null || r.getHotelRoomcategoryId()<=0L) {
	            	throw new InvalidDataException("inavild room category id");
	            }
	            room.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                                    .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException("Room Category not found")));
	            if(r.getHotelRoomtypeId()==null || r.getHotelRoomtypeId()<=0L) {
	            	throw new InvalidDataException("inavild room type id");
	            }
	            room.setRoomType(roomTypeRepo.findById(r.getHotelRoomtypeId())
	                                  .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));

	           room.setDiscountPercent(r.getDiscountPercent());
	           room.setDiscountValue(r.getDiscountValue());
	            return room;
	        }).collect(Collectors.toList());
	    discountEnitity.setDiscountRooms(rooms);
	    }
	    DiscountRate save = discountRepository.save(discountEnitity);
	     if(save.getDiscountId()==null || save.getDiscountId()==0) {
	    	 return null;
	     }
	     return save.getDiscountId();
	}

	@Override
	public DiscountDTO getDiscount(Long id) {
	    // 1. Find discount entity
	    DiscountRate discountEntity = discountRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Discount not found with id: " + id));

	    // 2. Map entity to DTO
	    DiscountDTO mapToDto = mapToDto(discountEntity);
	   return mapToDto;
	}


	private DiscountDTO mapToDto(DiscountRate discountEntity) {
		// TODO Auto-generated method stub
		 DiscountDTO dto = new DiscountDTO();

		    dto.setDiscountId(discountEntity.getDiscountId());
		    dto.setHotelId(discountEntity.getHotel().getHotelId());   // hotel is an object
		    dto.setSeasonId(discountEntity.getSeasonId());
		    dto.setRateCode(discountEntity.getRateCode());
		    dto.setBookDate(discountEntity.getBookDate());
		    dto.setBookDay(discountEntity.getBookDay());

		    dto.setAllDays(discountEntity.getIsAllDays());
		    dto.setRefund(discountEntity.getIsRefund());
		    dto.setWeekDay(discountEntity.getIsWeekDay());
		    dto.setWeekEnd(discountEntity.getIsWeekEnd());
		    dto.setExtraBed(discountEntity.getExtraBed());
		    dto.setRemark(discountEntity.getRemark());
		    
		    List<Long> countries = discountEntity.getDiscountExcludedCountries().stream()
		    	.map(exc -> exc.getCountry().getId())
		    	.collect(Collectors.toList());
		    dto.setExcludeCountry(countries);
		    
		    // 3. Market Types
		    List<Long> marketTypes = discountEntity.getDiscountMarketTypes().stream()
		            .map(mt -> mt.getMarketType().getMarketTypeId())   // adjust getter based on your entity
		            .collect(Collectors.toList());
		    dto.setMarketype(marketTypes);

		    // 4. Validity
		   List<DicountValidityDTO> validities = discountEntity.getDiscountValidities().stream()
		            .map(v -> {
		            	DicountValidityDTO valDTO  = new DicountValidityDTO();
		                valDTO.setDiscountValidityId(v.getDiscountValidityId());
		                valDTO.setValidityFrom(v.getValidityFrom());   // check if your DTO expects Date or String
		                valDTO.setValidityTo(v.getValidityTo());
		                valDTO.setIsType(v.getType());
		                return valDTO;
		            }).collect(Collectors.toList());
		    dto.setValidityDTO(validities);

		    // 5. Rooms
		     List<DiscountRoomDTO> rooms = discountEntity.getDiscountRooms().stream()
		            .map(r -> {
		            	DiscountRoomDTO roomDTO = new DiscountRoomDTO();
		                roomDTO.setRoomId(r.getDiscountRoomId());
		                roomDTO.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
		                roomDTO.setHotelRoomtypeId(r.getRoomType().getHotelRoomTypeId());
		                roomDTO.setDiscountPercent(r.getDiscountPercent());
		                roomDTO.setDiscountValue(r.getDiscountValue());
		                roomDTO.setLengthRestriction(String.valueOf(r.getLengthRestriction()));
		                return roomDTO;
		            }).collect(Collectors.toList());
		    dto.setRoomDTO(rooms);

		    return dto;
	}


	@Override
	public DiscountDTO editDiscount(Long id, @Valid DiscountDTO discountDTO) {
	    if (discountDTO == null) {
	        throw new MissingRequestBodyException("discountDTO cannot be null");
	    }

	    // 1. Fetch existing discount entity
	    DiscountRate discountEntity = discountRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Discount not found with id: " + id));

	    // 2. Update parent fields
	    Hotel hotel = hotelRepository.findById(discountDTO.getHotelId())
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + discountDTO.getHotelId()));

	    discountEntity.setHotel(hotel);
	    discountEntity.setSeasonId(discountDTO.getSeasonId());
	    discountEntity.setRateCode(discountDTO.getRateCode());
	    discountEntity.setBookDate(discountDTO.getBookDate());
	    discountEntity.setBookDay(discountDTO.getBookDay());
	    discountEntity.setIsAllDays(discountDTO.getAllDays());
	    discountEntity.setIsRefund(discountDTO.getRefund());
	    discountEntity.setIsWeekDay(discountDTO.getWeekDay());
	    discountEntity.setIsWeekEnd(discountDTO.getWeekEnd());
	    discountEntity.setExtraBed(discountDTO.getExtraBed());
	    discountEntity.setRemark(discountDTO.getRemark());
	    
		// Excluded countries
	    discountEntity.getDiscountExcludedCountries().clear();
		List<DiscountExcludedCountry> excludedCountries = Optional.ofNullable(discountDTO.getExcludeCountry())
		        .orElse(Collections.emptyList())
		        .stream()
		        .map(countryId -> {
		            DiscountExcludedCountry excludeCountry = new DiscountExcludedCountry();
		            
		            // Fetch the MasterCountry entity
		             MasterCountry country = countryRepository.findById(countryId)
		                    .orElseThrow(() -> new EntityNotFoundException("Country not found for id: " + countryId));
		            
		            excludeCountry.setCountry(country); // set entity
		            excludeCountry.setDiscountRate(discountEntity); // associate with discount
		            return excludeCountry;
		        })
		        .collect(Collectors.toList());

		// Set to discount entity
		discountEntity.getDiscountExcludedCountries().addAll(excludedCountries);

	    // 3. Clear and replace MarketTypes
	    discountEntity.getDiscountMarketTypes().clear();
		if(discountDTO.getMarketype()==null||discountDTO.getMarketype().isEmpty()) {
			throw new InvalidDataException("market type cannot be null or empty");
		}
	    List<DiscountMarketType> marketTypes = discountDTO.getMarketype().stream()
	            .map(mid -> {
	                DiscountMarketType mt = new DiscountMarketType();
	                MasterMarketType marketData = masterMarketTypeRepository.findById(mid)
	                        .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	                mt.setMarketType(marketData);
	                mt.setDiscountRate(discountEntity);
	                return mt;
	            }).collect(Collectors.toList());
	    discountEntity.getDiscountMarketTypes().addAll(marketTypes);

	    // 4. Clear and replace Validities
	    discountEntity.getDiscountValidities().clear();
	    List<DiscountValidity> validities = discountDTO.getValidityDTO().stream()
	            .map(v -> {
	                DiscountValidity validity = new DiscountValidity();
	                validity.setDiscountRate(discountEntity);
	                validity.setLive(false);
	                validity.setType(v.getIsType());
	                validity.setValidityFrom(v.getValidityFrom());
	                validity.setValidityTo(v.getValidityTo());
	                return validity;
	            }).collect(Collectors.toList());
	    discountEntity.getDiscountValidities().addAll(validities);

	    // 5. Clear and replace Rooms
	    discountEntity.getDiscountRooms().clear();
	    if(discountDTO.getRoomDTO()!=null && !discountDTO.getRoomDTO().isEmpty()) {
	    List<DiscountRoom> rooms = discountDTO.getRoomDTO().stream()
	            .map(r -> {
	                DiscountRoom room = new DiscountRoom();
	                room.setDiscountRate(discountEntity);
		            if(r.getHotelRoomcategoryId()==null || r.getHotelRoomcategoryId()<=0L) {
		            	throw new InvalidDataException("inavild room category id");
		            }
	                room.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                        .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException("Room Category not found")));
		            if(r.getHotelRoomtypeId()==null || r.getHotelRoomtypeId()<=0L) {
		            	throw new InvalidDataException("inavild room type id");
		            }
	                room.setRoomType(roomTypeRepo.findById(r.getHotelRoomtypeId())
	                        .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));

	                room.setDiscountPercent(r.getDiscountPercent());
	                room.setDiscountValue(r.getDiscountValue());
	                return room;
	            }).collect(Collectors.toList());
	    discountEntity.getDiscountRooms().addAll(rooms);
	    }
	    // 6. Save updated entity
	    DiscountRate updated = discountRepository.save(discountEntity);

	    // 7. Convert back to DTO (if needed)
	    DiscountDTO mapToDto = mapToDto(updated);
	    // map MarketTypes, Validities, and Rooms back if needed...
	    return mapToDto;
	}


	@Override
	public ResponseEntity<String> deleteDiscount(Long id) {
		// TODO Auto-generated method stub
		DiscountRate entity = discountRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Discount not found with id: " + id));
		
		discountRepository.delete(entity);
	    return ResponseEntity
	            .ok("Special rate deleted successfully with id: " + id);
	}

	@Override
	public Page<DiscountDTO> getAllDiscount(Pageable pageable, String search) {
	


		Page<DiscountRate> discountPage = discountRepository.findAll(
			    PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "discountId"))
			);

	 

	    // Convert Entity -> DTO
	    return discountPage.map(discount -> {
	        DiscountDTO dto = new DiscountDTO();
	        dto.setDiscountId(discount.getDiscountId());
	        dto.setHotelId(discount.getHotel().getHotelId());
	        dto.setSeasonId(discount.getSeasonId());
	        dto.setRateCode(discount.getRateCode());
	        dto.setBookDate(discount.getBookDate());
	        dto.setBookDay(discount.getBookDay());
	        dto.setAllDays(discount.getIsAllDays());
	        dto.setRefund(discount.getIsRefund());
	        dto.setWeekDay(discount.getIsWeekDay());
	        dto.setWeekEnd(discount.getIsWeekEnd());
	        dto.setExtraBed(discount.getExtraBed());
	        dto.setRemark(discount.getRemark());

	        if(discount.getDiscountExcludedCountries()!=null) {
	        	List<Long> countries = discount.getDiscountExcludedCountries().stream()
	        	.map(exc -> exc.getCountry().getId())
	        	.collect(Collectors.toList());
	        	dto.setExcludeCountry(countries);
	        }
	        
	        // Market Types
	        if (discount.getDiscountMarketTypes() != null) {
	            dto.setMarketype(
	                    discount.getDiscountMarketTypes().stream()
	                            .map(mt -> mt.getMarketType().getMarketTypeId()) // assuming MasterMarketType has getId()
	                            .collect(Collectors.toList())
	            );
	        }

	        // Validities
	        if (discount.getDiscountValidities() != null) {
	            dto.setValidityDTO(
	                    discount.getDiscountValidities().stream()
	                    .map(v -> {
	                    	DicountValidityDTO validityDTO = new DicountValidityDTO();
	                        validityDTO.setIsType(v.getType());
	                        validityDTO.setValidityFrom(v.getValidityFrom());
	                        validityDTO.setValidityTo(v.getValidityTo());
	                        return validityDTO;
	                    }).collect(Collectors.toList())
	            );
	        }

	        // Rooms
	        if (discount.getDiscountRooms() != null) {
	            dto.setRoomDTO(
	                    discount.getDiscountRooms().stream().map(r -> {
	                        DiscountRoomDTO roomDTO = new DiscountRoomDTO();
	                        roomDTO.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
	                        roomDTO.setHotelRoomtypeId(r.getRoomType().getHotelRoomTypeId());
	                        roomDTO.setDiscountPercent(r.getDiscountPercent());
	                        roomDTO.setDiscountValue(r.getDiscountValue());
	                        return roomDTO;
	                    }).collect(Collectors.toList())
	            );
	        }

	        return dto;
	    });
	}

	
}
