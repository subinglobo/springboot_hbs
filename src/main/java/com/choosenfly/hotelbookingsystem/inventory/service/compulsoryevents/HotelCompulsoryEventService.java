package com.choosenfly.hotelbookingsystem.inventory.service.compulsoryevents;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplymentsDTO;
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
			throw new com.choosenfly.hotelbookingsystem.inventory.exceptions.MissingRequestBodyException("discountDTO cannot be null");
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
	    		MasterMarketType marketData = masterMarketTypeRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + mid));
	            mt.setMarketType(marketData);
	            mt.setMarketType(marketData);
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
	         
	            rate.setHotelOccupancy(occupancyRepo.findById(r.getOcuppancytype_id())
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompulsorySupplymentsDTO editcompulsorySupplyment(Long id,
			@Valid CompulsorySupplymentsDTO compulsorySupplymentsDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteCompulsorySupplyment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CompulsorySupplymentsDTO> getAllCompulsorySupplyment(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
