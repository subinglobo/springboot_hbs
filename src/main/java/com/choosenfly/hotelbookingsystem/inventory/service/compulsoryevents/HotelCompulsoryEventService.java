package com.choosenfly.hotelbookingsystem.inventory.service.compulsoryevents;

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

import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplyValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplymentsDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplymentsRateDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplyMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplyValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplyments;
import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplymentsRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountExcludedCountry;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountValidity;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.InvalidDataException;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelCompulsoryEventRepository;
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
public class HotelCompulsoryEventService implements HotelCompulsoryEventServiceInterface{
	
	private final HotelCompulsoryEventRepository eventRepository;
	
	private final HotelRepository hotelRepository;

	private final HotelRoomCategoryRepositoy roomCategoryRepo;
	
	private MasterMarketTypeRepository masterMarketTypeRepository;
	
	private final OccupancyRepository occupancyRepo;
	
	
	public HotelCompulsoryEventService(HotelCompulsoryEventRepository eventRepository,
			HotelRepository hotelRepository,HotelRoomCategoryRepositoy roomCategoryRepo,
			MasterMarketTypeRepository masterMarketTypeRepository, OccupancyRepository occupancyRepo) {
		this.eventRepository=eventRepository;
		this.hotelRepository=hotelRepository;
		this.roomCategoryRepo=roomCategoryRepo;
		this.masterMarketTypeRepository=masterMarketTypeRepository;
		this.occupancyRepo=occupancyRepo;
		
	}

	@Override
	public Long saveCompulsorySupplyment(@Valid CompulsorySupplymentsDTO compulsorySupplymentsDTO) {
		// TODO Auto-generated method stub
		if(compulsorySupplymentsDTO==null) {
			throw new com.choosenfly.hotelbookingsystem.inventory.exceptions.MissingRequestBodyException("CompulsorySupplymentsDTO cannot be null");
		}
		CompulsorySupplyments entity = new CompulsorySupplyments();
		Hotel hotel = hotelRepository.findById(compulsorySupplymentsDTO.getHotelId()).orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + compulsorySupplymentsDTO.getHotelId()));

		entity.setHotel(hotel);
		entity.setSupplymentCode(compulsorySupplymentsDTO.getSupplymentCode());
		entity.setSupplyments(compulsorySupplymentsDTO.getSupplyments());
		

	    // 1. MarketTypes		
		List<CompulsorySupplyMarketType> marketTypes = compulsorySupplymentsDTO.getMarketypeIds().stream()
			    .map(mid -> {
			        CompulsorySupplyMarketType mt = new CompulsorySupplyMarketType();
			        MasterMarketType marketData = masterMarketTypeRepository.findById(mid)
			            .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
			        mt.setMarketType(marketData);

			        // 🔑 Fix: set parent compulsory supplyment
			        mt.setCompulsorySupplyments(entity);

			        return mt;
			    }).collect(Collectors.toList());
			entity.setCompulsorySupplyMarketTypes(marketTypes);
	    
	    // 2. Validity
	   List<CompulsorySupplyValidity> validities = compulsorySupplymentsDTO.getCompulsorySupplyValidityDTO().stream()
	        .map(v -> {
	        	CompulsorySupplyValidity validity = new CompulsorySupplyValidity();
	            validity.setCompulsorySupplyments(entity);
	            validity.setLive(false);
	            validity.setValidityFrom(v.getValidityFrom());
	            validity.setValidityTo(v.getValidityTo());
	            return validity;
	        }).collect(Collectors.toList());
	   entity.setCompulsorySupplyValidities(validities);

	    // 3. Room Rates
	    if(compulsorySupplymentsDTO.getCompulsorySupplymentsRateDTO()!=null && !compulsorySupplymentsDTO.getCompulsorySupplymentsRateDTO().isEmpty()) {
	    	
	    	List<CompulsorySupplymentsRate> rates = compulsorySupplymentsDTO.getCompulsorySupplymentsRateDTO().stream()
	        .map(r -> {
	        	CompulsorySupplymentsRate rate = new CompulsorySupplymentsRate();
	        	rate.setCompulsorySupplyments(entity);
	            if(r.getHotelRoomcategoryId()==null || r.getHotelRoomcategoryId()<=0L) {
	            	throw new InvalidDataException("inavild room category id");
	            }
	            rate.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                                    .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException("Room Category not found")));
	         
	            rate.setHotelOccupancy(occupancyRepo.findById(r.getOcuppancytypeId())
                    .orElseThrow(() -> new EntityNotFoundException("Occupancy not found")));
	            rate.setRate(r.getRate());
	            rate.setAdultRate(r.getRateAdult());
	            rate.setChildRate(r.getRateChild());
	            
	            return rate;
	        }).collect(Collectors.toList());
	    entity.setCompulsorySupplymentsRates(rates);
	    }
	    CompulsorySupplyments save = eventRepository.save(entity);
	     if(save.getSupplymentId() ==null || save.getSupplymentId()==0) {
	    	 return null;
	     }
	     return save.getSupplymentId();
	}

	@Override
	public CompulsorySupplymentsDTO getCompulsorySupplyment(Long id) {
	    // 1. Fetch entity
	    CompulsorySupplyments entity = eventRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("Compulsory Supplyment not found with id: " + id));

	    // 2. Map to DTO
	    CompulsorySupplymentsDTO dto = new CompulsorySupplymentsDTO();
	    dto.setSupplymentId(entity.getSupplymentId());
	    dto.setHotelId(entity.getHotel().getHotelId());
	    dto.setSupplymentCode(entity.getSupplymentCode());
	    dto.setSupplyments(entity.getSupplyments());

	    // 🔹 3. Market Types
	    if (entity.getCompulsorySupplyMarketTypes() != null) {
	        List<Long> marketTypeIds = entity.getCompulsorySupplyMarketTypes().stream()
	            .map(mt -> mt.getMarketType().getMarketTypeId()) // replace `getId()` with actual field name
	            .collect(Collectors.toList());
	        dto.setMarketypeIds(marketTypeIds);
	    }

	    // 🔹 4. Validities
	    if (entity.getCompulsorySupplyValidities() != null) {
	        List<CompulsorySupplyValidityDTO> validityDTOs = entity.getCompulsorySupplyValidities().stream()
	            .map(v -> {
	                CompulsorySupplyValidityDTO vDto = new CompulsorySupplyValidityDTO();
	                vDto.setValidityFrom(v.getValidityFrom());
	                vDto.setValidityTo(v.getValidityTo());
	                vDto.setSupplymentValidityId(v.getSupplymentValidityId());
	                return vDto;
	            }).collect(Collectors.toList());
	        dto.setCompulsorySupplyValidityDTO(validityDTOs);
	    }

	    // 🔹 5. Rates
	    if (entity.getCompulsorySupplymentsRates() != null) {
	        List<CompulsorySupplymentsRateDTO> rateDTOs = entity.getCompulsorySupplymentsRates().stream()
	            .map(r -> {
	                CompulsorySupplymentsRateDTO rDto = new CompulsorySupplymentsRateDTO();
	                rDto.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id()); // replace with actual field name
	                rDto.setOcuppancytypeId(r.getHotelOccupancy().getId());   // replace with actual field name
	                rDto.setRate(r.getRate());
	                rDto.setRateAdult(r.getAdultRate());
	                rDto.setRateChild(r.getChildRate());
	                rDto.setSupplymentrateId(r.getSupplymentrateId());
	                return rDto;
	            }).collect(Collectors.toList());
	        dto.setCompulsorySupplymentsRateDTO(rateDTOs);
	    }

	    return dto;
	}

	@Override
	public CompulsorySupplymentsDTO editcompulsorySupplyment(Long id,
	        @Valid CompulsorySupplymentsDTO compulsorySupplymentsDTO) {

	    // 1. Fetch existing entity
	    CompulsorySupplyments entity = eventRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Compulsory Supplyment not found with id: " + id));

	    // 2. Update basic fields
	    Hotel hotel = hotelRepository.findById(compulsorySupplymentsDTO.getHotelId())
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + compulsorySupplymentsDTO.getHotelId()));
	    entity.setHotel(hotel);
	    entity.setSupplymentCode(compulsorySupplymentsDTO.getSupplymentCode());
	    entity.setSupplyments(compulsorySupplymentsDTO.getSupplyments());

	    // 3. Clear and replace MarketTypes
	    entity.getCompulsorySupplyMarketTypes().clear();
		if(compulsorySupplymentsDTO.getMarketypeIds()==null || compulsorySupplymentsDTO.getMarketypeIds().isEmpty()) {
			throw new InvalidDataException("market type cannot be null or empty");
		}
	    List<CompulsorySupplyMarketType> marketTypes = compulsorySupplymentsDTO.getMarketypeIds().stream()
	            .map(mid -> {
	            	CompulsorySupplyMarketType mt = new CompulsorySupplyMarketType();
	                MasterMarketType marketData = masterMarketTypeRepository.findById(mid)
	                        .orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	                mt.setMarketType(marketData);
	                mt.setCompulsorySupplyments(entity);
	                return mt;
	            }).collect(Collectors.toList());
	    entity.getCompulsorySupplyMarketTypes().addAll(marketTypes);

	    // 4. Update Validities
	    entity.getCompulsorySupplyValidities().clear();
	    if (compulsorySupplymentsDTO.getCompulsorySupplyValidityDTO() != null) {
	        List<CompulsorySupplyValidity> validities = compulsorySupplymentsDTO.getCompulsorySupplyValidityDTO().stream()
	                .map(v -> {
	                    CompulsorySupplyValidity validity = new CompulsorySupplyValidity();
	                    validity.setCompulsorySupplyments(entity);
	                    validity.setSupplymentValidityId(null); // always new
	                    validity.setValidityFrom(v.getValidityFrom());
	                    validity.setValidityTo(v.getValidityTo());
	                    return validity;
	                }).collect(Collectors.toList());
	        entity.getCompulsorySupplyValidities().addAll(validities);
	    }
	    // 5. Update Rates
	    entity.getCompulsorySupplymentsRates().clear();
	    if (compulsorySupplymentsDTO.getCompulsorySupplymentsRateDTO() != null) {
	        List<CompulsorySupplymentsRate> rates = compulsorySupplymentsDTO.getCompulsorySupplymentsRateDTO().stream()
	                .map(r -> {
	                    CompulsorySupplymentsRate rate = new CompulsorySupplymentsRate();
	                    rate.setCompulsorySupplyments(entity);
	                    rate.setSupplymentrateId(null) ;// always new

	                    if (r.getHotelRoomcategoryId() == null || r.getHotelRoomcategoryId() <= 0L) {
	                        throw new InvalidDataException("Invalid room category id");
	                    }

	                    rate.setRoomCategory(roomCategoryRepo.findById(r.getHotelRoomcategoryId())
	                            .orElseThrow(() -> new EntityNotFoundException("Room Category not found")));

	                    rate.setHotelOccupancy(occupancyRepo.findById(r.getOcuppancytypeId())
	                            .orElseThrow(() -> new EntityNotFoundException("Occupancy not found")));

	                    rate.setRate(r.getRate());
	                    rate.setAdultRate(r.getRateAdult());
	                    rate.setChildRate(r.getRateChild());

	                    return rate;
	                }).collect(Collectors.toList());
	        entity.getCompulsorySupplymentsRates().addAll(rates);
	    }

	    // 6. Save updated entity
	    CompulsorySupplyments updated = eventRepository.save(entity);

	    // 7. Return DTO (reuse getCompulsorySupplyment for mapping)
	    CompulsorySupplyments updatedEntity = eventRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Compulsory Supplyment not found with id: " + id));
	    
	    CompulsorySupplymentsDTO mapToDTO = mapToDto(updatedEntity);
	    return mapToDTO;
	}

	private CompulsorySupplymentsDTO mapToDto(CompulsorySupplyments entity) {
		// TODO Auto-generated method stub
	    CompulsorySupplymentsDTO dto = new CompulsorySupplymentsDTO();
	    dto.setSupplymentId(entity.getSupplymentId());
	    dto.setHotelId(entity.getHotel().getHotelId());
	    dto.setSupplymentCode(entity.getSupplymentCode());
	    dto.setSupplyments(entity.getSupplyments());

	    // 🔹 3. Market Types
	    if (entity.getCompulsorySupplyMarketTypes() != null) {
	        List<Long> marketTypeIds = entity.getCompulsorySupplyMarketTypes().stream()
	            .map(mt -> mt.getMarketType().getMarketTypeId()) // replace `getId()` with actual field name
	            .collect(Collectors.toList());
	        dto.setMarketypeIds(marketTypeIds);
	    }

	    // 🔹 4. Validities
	    if (entity.getCompulsorySupplyValidities() != null) {
	        List<CompulsorySupplyValidityDTO> validityDTOs = entity.getCompulsorySupplyValidities().stream()
	            .map(v -> {
	                CompulsorySupplyValidityDTO vDto = new CompulsorySupplyValidityDTO();
	                vDto.setValidityFrom(v.getValidityFrom());
	                vDto.setValidityTo(v.getValidityTo());
	                vDto.setSupplymentValidityId(v.getSupplymentValidityId());
	                return vDto;
	            }).collect(Collectors.toList());
	        dto.setCompulsorySupplyValidityDTO(validityDTOs);
	    }

	    // 🔹 5. Rates
	    if (entity.getCompulsorySupplymentsRates() != null) {
	        List<CompulsorySupplymentsRateDTO> rateDTOs = entity.getCompulsorySupplymentsRates().stream()
	            .map(r -> {
	                CompulsorySupplymentsRateDTO rDto = new CompulsorySupplymentsRateDTO();
	                rDto.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id()); // replace with actual field name
	                rDto.setOcuppancytypeId(r.getHotelOccupancy().getId());   // replace with actual field name
	                rDto.setRate(r.getRate());
	                rDto.setRateAdult(r.getAdultRate());
	                rDto.setRateChild(r.getChildRate());
	                rDto.setSupplymentrateId(r.getSupplymentrateId());
	                return rDto;
	            }).collect(Collectors.toList());
	        dto.setCompulsorySupplymentsRateDTO(rateDTOs);
	    }

	    return dto;
	}

	@Override
	public ResponseEntity<String> deleteCompulsorySupplyment(Long id) {
	    // 1. Check existence
	    CompulsorySupplyments entity = eventRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Compulsory Supplyment not found with id: " + id));

	    // 2. Delete
	    eventRepository.delete(entity);

	    // 3. Return response
	    return ResponseEntity.ok("Compulsory Supplyment with ID " + id + " deleted successfully");
	}
	
	
	@Override
	public Page<CompulsorySupplymentsDTO> getAllCompulsorySupplyment(Pageable pageable, String search) {

	    // 1. Fetch page (you can extend with search filter later)
	    Page<CompulsorySupplyments> page = eventRepository.findAll(
	            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "supplymentId"))
	    );

	    // 2. Map Entity -> DTO
	    return page.map(entity -> {
	        CompulsorySupplymentsDTO dto = new CompulsorySupplymentsDTO();
	        dto.setSupplymentId(entity.getSupplymentId());
	        dto.setHotelId(entity.getHotel().getHotelId());
	        dto.setSupplymentCode(entity.getSupplymentCode());
	        dto.setSupplyments(entity.getSupplyments());

	        // 🔹 Market Types
	        if (entity.getCompulsorySupplyMarketTypes() != null) {
	            List<Long> marketTypeIds = entity.getCompulsorySupplyMarketTypes().stream()
	                    .map(mt -> mt.getMarketType().getMarketTypeId())
	                    .collect(Collectors.toList());
	            dto.setMarketypeIds(marketTypeIds);
	        }

	        // 🔹 Validities
	        if (entity.getCompulsorySupplyValidities() != null) {
	            List<CompulsorySupplyValidityDTO> validityDTOs = entity.getCompulsorySupplyValidities().stream()
	                    .map(v -> {
	                        CompulsorySupplyValidityDTO vDto = new CompulsorySupplyValidityDTO();
	                        vDto.setSupplymentValidityId(v.getSupplymentValidityId());
	                        vDto.setValidityFrom(v.getValidityFrom());
	                        vDto.setValidityTo(v.getValidityTo());
	                        return vDto;
	                    }).collect(Collectors.toList());
	            dto.setCompulsorySupplyValidityDTO(validityDTOs);
	        }

	        // 🔹 Rates
	        if (entity.getCompulsorySupplymentsRates() != null) {
	            List<CompulsorySupplymentsRateDTO> rateDTOs = entity.getCompulsorySupplymentsRates().stream()
	                    .map(r -> {
	                        CompulsorySupplymentsRateDTO rDto = new CompulsorySupplymentsRateDTO();
	                        rDto.setSupplymentrateId(r.getSupplymentrateId());
	                        rDto.setHotelRoomcategoryId(r.getRoomCategory().getHotel_room_category_id());
	                        rDto.setOcuppancytypeId(r.getHotelOccupancy().getId());
	                        rDto.setRate(r.getRate());
	                        rDto.setRateAdult(r.getAdultRate());
	                        rDto.setRateChild(r.getChildRate());
	                        return rDto;
	                    }).collect(Collectors.toList());
	            dto.setCompulsorySupplymentsRateDTO(rateDTOs);
	        }

	        return dto;
	    });
	}

}
