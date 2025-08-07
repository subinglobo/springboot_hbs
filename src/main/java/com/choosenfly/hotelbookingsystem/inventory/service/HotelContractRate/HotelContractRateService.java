package com.choosenfly.hotelbookingsystem.inventory.service.HotelContractRate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.inventory.dto.contractrate.ContractRateDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.contractrate.ContractRateRoomDetailsDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.contractrate.ContractRateValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomType;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateExcludeCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale.HotelStopSale;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelContractRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomCategoryRepositoy;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomTypeRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomTypeRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class HotelContractRateService implements HotelContractRateServiceInterface{
	
	private final HotelContractRateRepository contractRateRepository;
	
	private final HotelRepository hotelRepository;

	private final HotelRoomCategoryRepositoy roomCategoryRepo;
	
	private final HotelRoomTypeRepository roomTypeRepo;
	
	private final OccupancyRepository occupancyRepo;
	
	private MasterMarketTypeRepository masterMarketTypeRepository;
	
	private MasterRoomCategoryRepository MasterRoomCategoryRepository;
	
	private MasterRoomTypeRepository masterRoomTypeRepository;

	public HotelContractRateService(HotelContractRateRepository contractRateRepository,HotelRepository hotelRepository
			,HotelRoomCategoryRepositoy roomCategoryRepo,HotelRoomTypeRepository roomTypeRepo,
			OccupancyRepository occupancyRepo,MasterMarketTypeRepository masterMarketTypeRepository,
			MasterRoomCategoryRepository MasterRoomCategoryRepository,
			MasterRoomTypeRepository masterRoomTypeRepository) {
		this.contractRateRepository=contractRateRepository;
		this.hotelRepository=hotelRepository;
		this.roomCategoryRepo=roomCategoryRepo;
		this.roomTypeRepo=roomTypeRepo;
		this.occupancyRepo=occupancyRepo;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
		this.MasterRoomCategoryRepository=MasterRoomCategoryRepository;
		this.masterRoomTypeRepository=masterRoomTypeRepository;
		
	}

	@Override
	@Transactional
	public Long saveContractRate(@Valid ContractRateDTO contractRateDTO) {
		// TODO Auto-generated method stub
		
		if(contractRateDTO==null) {
			throw new MissingRequestBodyException("contractRateDTO cannot be null");
		}
	    ContractRate contractEntity = new ContractRate();
	    contractEntity.setRateCode(contractRateDTO.getRateCode());
	    contractEntity.setIsAllDays(contractRateDTO.getAllDays());
	    contractEntity.setIsWeekDay(contractRateDTO.getWeekDay());
	    contractEntity.setIsWeekEndDay(contractRateDTO.getWeekEndDay());
	    contractEntity.setIsLive(contractRateDTO.getIsLive());
	    contractEntity.setSeasonId(contractRateDTO.getSeason_id());

		Hotel hotel = hotelRepository.findById(contractRateDTO.getHotel_id()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + contractRateDTO.getHotel_id()));

		contractEntity.setHotel(hotel);

	    // 1. MarketTypes
	    List<ContractRateMarketType> marketTypes = contractRateDTO.getMarketype().stream()
	        .map(mid -> {
	            ContractRateMarketType mt = new ContractRateMarketType();
	    		MasterMarketType marketData = masterMarketTypeRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            mt.setContractRate(contractEntity);
	            return mt;
	        }).collect(Collectors.toList());
	    contractEntity.setMarketTypes(marketTypes);
	    
	    //excludeded countryList
	    List<ContractRateExcludeCountry> collect = Optional.ofNullable(contractRateDTO.getExclude_country())
	    		.orElse(Collections.emptyList())
	    		.stream().map(excountryId ->{
	    	    ContractRateExcludeCountry contractRateExcludeCountry = new ContractRateExcludeCountry();
	    	    
	    	    contractRateExcludeCountry.setContractRate(contractEntity);
	    	    contractRateExcludeCountry.setCoutryId(excountryId);
	    	    return contractRateExcludeCountry;
	    	}).collect(Collectors.toList());
	    
	    contractEntity.setContractRateExcludeCountries(collect);
	    
	    // 2. Validity
	    List<ContractRateValidity> validities = contractRateDTO.getContractRateValidityDTO().stream()
	        .map(v -> {
	            ContractRateValidity validity = new ContractRateValidity();
	            validity.setContractRate(contractEntity);
	            validity.setIsLive(true);
	            validity.setPriority(1);
	            validity.setValidityFrom(v.getValidityFrom());
	            validity.setValidityTo(v.getValidityTo());
	            return validity;
	        }).collect(Collectors.toList());
	    contractEntity.setValidities(validities);

	    // 3. Room Rates
	     List<ContractRateRoom> collect2 = contractRateDTO.getContractRateRoomDTO().stream()
	        .map(r -> {
	            ContractRateRoom room = new ContractRateRoom();
	            room.setContractRate(contractEntity);

	            room.setRoomCategory(roomCategoryRepo.findById(r.getHotel_roomcategory_id())
	                                    .orElseThrow(() -> new EntityNotFoundException("Room Category not found")));
	            room.setRoomType(roomTypeRepo.findById(r.getHotel_roomtype_id())
	                                  .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));
	            room.setHotelOccupancy(occupancyRepo.findById(r.getOcuppancytype_id())
	                                  .orElseThrow(() -> new EntityNotFoundException("Occupancy not found")));

	            room.setRate(r.getRate());
	            room.setIsExtraBed(r.isExtraBed());
	            room.setIsMeal(r.isMeal());
	            room.setIsRefundable(r.isIsrefundable());
	            if (r.getAdultrate()!= null&&r.isExtraBed()==true) {
	                room.setAdultRate(r.getAdultrate());
	            }
	            if (r.getChildrate() != null&&r.isExtraBed()==true) {
	                room.setChildRate(r.getChildrate());
	            }

	            return room;
	        }).collect(Collectors.toList());
	    contractEntity.setRooms(collect2);

	     ContractRate save = contractRateRepository.save(contractEntity);
	     if(save.getId()==null || save.getId()==0) {
	    	 return null;
	     }
	     return save.getId();
		

	}

	@Override
	public ContractRateDTO getContractRate(Long id) {
	    ContractRate contractRate = contractRateRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("Contract Rate not found with id: " + id));
	    
	    return convertToDTO(contractRate);
	}

	private ContractRateDTO convertToDTO(ContractRate entity) {
	    ContractRateDTO dto = new ContractRateDTO();

	    dto.setContractrate_id(entity.getId());
	    dto.setRateCode(entity.getRateCode());
	    dto.setHotel_id(entity.getHotel().getHotelId());
	    dto.setSeason_id(entity.getSeasonId());
	    dto.setAllDays(entity.getIsAllDays());
	    dto.setWeekDay(entity.getIsWeekDay() );
	    dto.setWeekEndDay(entity.getIsWeekEndDay());
	    dto.setIsLive(entity.getIsLive());
	    // 1. Market Types
	    dto.setMarketype(entity.getMarketTypes().stream()
	        .map(mt -> mt.getMarketType().getMarketTypeId())
	        .collect(Collectors.toList()));

	    // 2. Excluded Countries
	    dto.setExclude_country(entity.getContractRateExcludeCountries().stream()
	        .map(ContractRateExcludeCountry::getCoutryId)
	        .collect(Collectors.toList()));

	    // 3. Validity
	    dto.setContractRateValidityDTO(entity.getValidities().stream().map(v -> {
	        ContractRateValidityDTO vdto = new ContractRateValidityDTO();
	        vdto.setContractValidityId(v.getId());
	        vdto.setValidityFrom(v.getValidityFrom());
	        vdto.setValidityTo(v.getValidityTo());
	        return vdto;
	    }).collect(Collectors.toList()));

	    // 4. Room DTOs
	    dto.setContractRateRoomDTO(entity.getRooms().stream().map(r -> {
	        ContractRateRoomDetailsDTO roomDTO = new ContractRateRoomDetailsDTO();
	        roomDTO.setHotel_roomcategory_id(r.getRoomCategory().getHotel_room_category_id());
	        roomDTO.setHotel_roomtype_id(r.getRoomType().getHotel_roomType_id());
	        roomDTO.setOcuppancytype_id(r.getHotelOccupancy().getId());
	        roomDTO.setRate(r.getRate());
	        roomDTO.setExtraBed(r.getIsExtraBed());
	        roomDTO.setMeal(r.getIsMeal());
	        if (r.getIsRefundable() != null) {
	            roomDTO.setIsrefundable(r.getIsRefundable());
	        } else {
	            roomDTO.setIsrefundable(false); // or true, or leave unset, depending on your logic
	        }
	        if (r.getAdultRate() != null) roomDTO.setAdultrate(r.getAdultRate());
	        if (r.getChildRate() != null) roomDTO.setChildrate(r.getChildRate());
	        return roomDTO;
	    }).collect(Collectors.toList()));

	    return dto;
	}

	@Override
	public ContractRateDTO editContractRate(Long id, @Valid ContractRateDTO contractRateDTO) {
	    ContractRate contractEntity = contractRateRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("Contract Rate not found with id: " + id));

	    // Basic fields
	    contractEntity.setRateCode(contractRateDTO.getRateCode());
	    contractEntity.setIsAllDays(contractRateDTO.getAllDays());
	    contractEntity.setIsWeekDay(contractRateDTO.getWeekDay());
	    contractEntity.setIsWeekEndDay(contractRateDTO.getWeekEndDay());
	    contractEntity.setIsLive(contractRateDTO.getIsLive());
	    contractEntity.setSeasonId(contractRateDTO.getSeason_id());

	    // Hotel
	    Hotel hotel = hotelRepository.findById(contractRateDTO.getHotel_id())
	        .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + contractRateDTO.getHotel_id()));
	    contractEntity.setHotel(hotel);

	    // 1. MarketTypes
	    List<ContractRateMarketType> marketTypes = contractRateDTO.getMarketype().stream()
	        .map(mid -> {
	            ContractRateMarketType mt = new ContractRateMarketType();
	            mt.setContractRate(contractEntity);
	            MasterMarketType marketData = masterMarketTypeRepository.findById(mid)
	                .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            return mt;
	        }).collect(Collectors.toList());
	    if (contractEntity.getMarketTypes() != null) {
	        contractEntity.getMarketTypes().clear();
	    }
	    contractEntity.getMarketTypes().addAll(marketTypes);
//	    contractEntity.setMarketTypes(marketTypes);

	    // 2. Excluded Countries
	    List<ContractRateExcludeCountry> excluded = Optional.ofNullable(contractRateDTO.getExclude_country())
	        .orElse(Collections.emptyList())
	        .stream()
	        .map(cid -> {
	            ContractRateExcludeCountry e = new ContractRateExcludeCountry();
	            e.setContractRate(contractEntity);
	            e.setCoutryId(cid);
	            return e;
	        }).collect(Collectors.toList());
	    if (contractEntity.getContractRateExcludeCountries() != null) {
	        contractEntity.getContractRateExcludeCountries().clear();
	        contractEntity.getContractRateExcludeCountries().addAll(excluded);
	    } else {
	        contractEntity.setContractRateExcludeCountries(excluded);
	    }

	    // 3. Validities
	    List<ContractRateValidity> validities = contractRateDTO.getContractRateValidityDTO().stream()
	        .map(v -> {
	            ContractRateValidity validity = new ContractRateValidity();
	            validity.setContractRate(contractEntity);
	            validity.setIsLive(true);
	            validity.setPriority(1);
	            validity.setValidityFrom(v.getValidityFrom());
	            validity.setValidityTo(v.getValidityTo());
	            return validity;
	        }).collect(Collectors.toList());
	    if(contractEntity.getValidities()!=null) {
	    	contractEntity.getValidities().clear();
	    	contractEntity.getValidities().addAll(validities);
	    }else {
	    contractEntity.setValidities(validities);
	    }
	    // 4. Rooms
	    List<ContractRateRoom> roomList = contractRateDTO.getContractRateRoomDTO().stream()
	        .map(r -> {
	            ContractRateRoom room = new ContractRateRoom();
	            room.setContractRate(contractEntity);
	            room.setRoomCategory(roomCategoryRepo.findById(r.getHotel_roomcategory_id())
	                .orElseThrow(() -> new EntityNotFoundException("Room Category not found")));
	            room.setRoomType(roomTypeRepo.findById(r.getHotel_roomtype_id())
	                .orElseThrow(() -> new EntityNotFoundException("Room Type not found")));
	            room.setHotelOccupancy(occupancyRepo.findById(r.getOcuppancytype_id())
	                .orElseThrow(() -> new EntityNotFoundException("Occupancy not found")));
	            room.setRate(r.getRate());
	            room.setIsExtraBed(r.isExtraBed());
	            room.setIsMeal(r.isMeal());
	            room.setIsRefundable(r.isIsrefundable());
	            if (r.getAdultrate() != null && r.isExtraBed()) {
	                room.setAdultRate(r.getAdultrate());
	            }
	            if (r.getChildrate() != null && r.isExtraBed()) {
	                room.setChildRate(r.getChildrate());
	            }
	            return room;
	        }).collect(Collectors.toList());
	    if (contractEntity.getRooms() != null) {
	        contractEntity.getRooms().clear();                    // Remove old
	        contractEntity.getRooms().addAll(roomList);           // Add new
	    } else {
	        contractEntity.setRooms(new ArrayList<>(roomList));   // First-time set
	    }

	    // Save updated
	    ContractRate updated = contractRateRepository.save(contractEntity);
	    return convertToDTO(updated);
	}

	@Override
	public ResponseEntity<String> deleteContractRate(Long id) {
		// TODO Auto-generated method stub
		
		ContractRate contractRate = contractRateRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("contract rate not found for id: " + id));

	    contractRateRepository.delete(contractRate);
	        return ResponseEntity.ok("contractRate with id " + id + " deleted successfully");
	}

	
	@Override
	@Transactional
	public Page<ContractRateDTO> getAllContractRate(Pageable pageable, String search) {
	    Page<ContractRate> contractRatePage = contractRateRepository.findAll(pageable);

	    return contractRatePage.map(contractRate -> {
	        if (contractRate == null) {
	            return null;
	        }

	        ContractRateDTO dto = new ContractRateDTO();
	        dto.setContractrate_id(contractRate.getId());
	        dto.setRateCode(contractRate.getRateCode());
	        dto.setHotel_id(contractRate.getHotel() != null ? contractRate.getHotel().getHotelId() : null);
	        dto.setSeason_id(contractRate.getSeasonId() != null ? contractRate.getSeasonId() : null);
	        dto.setAllDays(contractRate.getIsAllDays());
	        dto.setWeekDay(contractRate.getIsWeekDay() );
	        dto.setWeekEndDay(contractRate.getIsWeekEndDay());
	        dto.setIsLive(contractRate.getIsLive());
	        // Market Types
	        if (contractRate.getMarketTypes() != null) {
	            dto.setMarketype(
	                contractRate.getMarketTypes().stream()
	                    .filter(Objects::nonNull)
	                    .map(mt -> mt.getMarketType().getMarketTypeId())
	                    .collect(Collectors.toList())
	            );
	        } else {
	            dto.setMarketype(Collections.emptyList());
	        }

	        // Exclude Country List
	        if (contractRate.getContractRateExcludeCountries() != null) {
	            dto.setExclude_country(
	                contractRate.getContractRateExcludeCountries().stream()
	                    .filter(Objects::nonNull)
	                    .map(ContractRateExcludeCountry::getCoutryId)
	                    .collect(Collectors.toList())
	            );
	        } else {
	            dto.setExclude_country(Collections.emptyList());
	        }

	        // Validity List
	        if (contractRate.getValidities() != null) {
	            dto.setContractRateValidityDTO(
	                contractRate.getValidities().stream()
	                    .filter(Objects::nonNull)
	                    .map(validity -> {
	                        ContractRateValidityDTO validityDTO = new ContractRateValidityDTO();
	                        validityDTO.setContractValidityId(validity.getId());
	                        validityDTO.setValidityFrom(validity.getValidityFrom());
	                        validityDTO.setValidityTo(validity.getValidityTo());
	                        return validityDTO;
	                    }).collect(Collectors.toList())
	            );
	        } else {
	            dto.setContractRateValidityDTO(Collections.emptyList());
	        }

	        // Room List
	        if (contractRate.getRooms() != null) {
	            dto.setContractRateRoomDTO(
	                contractRate.getRooms().stream()
	                    .filter(Objects::nonNull)
	                    .map(room -> {
	                        ContractRateRoomDetailsDTO roomDTO = new ContractRateRoomDetailsDTO();
	                        roomDTO.setHotel_roomcategory_id(
	                            room.getRoomCategory() != null ? room.getRoomCategory().getHotel_room_category_id() : null
	                        );
	                        roomDTO.setHotel_roomtype_id(
	                            room.getRoomType() != null ? room.getRoomType().getHotel_roomType_id() : null
	                        );
	                        roomDTO.setOcuppancytype_id(
	                            room.getHotelOccupancy() != null ? room.getHotelOccupancy().getId() : null
	                        );
	                        roomDTO.setRate(room.getRate());
	                        roomDTO.setExtraBed(room.getIsExtraBed());
	                        roomDTO.setMeal(room.getIsMeal());
	                        if (room.getIsRefundable() != null) {
	                            roomDTO.setIsrefundable(room.getIsRefundable());
	                        } else {
	                            roomDTO.setIsrefundable(false); // or true, or leave unset, depending on your logic
	                        }
	                        if (room.getAdultRate() != null) {
	                            roomDTO.setAdultrate(room.getAdultRate());
	                        }
	                        if (room.getChildRate() != null) {
	                            roomDTO.setChildrate(room.getChildRate());
	                        }
	                        return roomDTO;
	                    }).collect(Collectors.toList())
	            );
	        } else {
	            dto.setContractRateRoomDTO(Collections.emptyList());
	        }

	        return dto;
	    });
	}
}
