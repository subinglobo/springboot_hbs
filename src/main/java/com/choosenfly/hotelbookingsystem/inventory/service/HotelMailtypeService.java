package com.choosenfly.hotelbookingsystem.inventory.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelMailCentreDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelContactDetails;
import com.choosenfly.hotelbookingsystem.inventory.entities.LinkedHotelContactDetailsMailType;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelContactDetailsRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelMailCentreRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMailType;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class HotelMailtypeService implements HotelMailtypeServiceInterface {

	public final HotelRepository hotelRepository;

	public final HotelMailCentreRepository hotelMailCentreRepository;
	
	public final HotelContactDetailsRepository hotelContactDetailsRepository;

	public HotelMailtypeService(HotelRepository hotelRepository, HotelMailCentreRepository hotelMailCentreRepository,HotelContactDetailsRepository hotelContactDetailsRepository) {
		this.hotelRepository = hotelRepository;
		this.hotelMailCentreRepository = hotelMailCentreRepository;
		this.hotelContactDetailsRepository = hotelContactDetailsRepository;
	}

	@Override
	@Transactional
	public String addMailCentre(Long hotelId, HotelMailCentreDTO mailCentre) {
		

		
	    // Fetch the hotel, throwing an exception if not found
	    Hotel hotel = hotelRepository.findById(hotelId)
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + hotelId));

	   
	    List<HotelContactDetails> contactDetails = Optional.ofNullable(hotel.getContactDetails())
	            .orElse(Collections.emptyList()); // Handle null

	    
	    boolean isUpdated = false;
	 
	    for (HotelContactDetails hotelContactDetails : contactDetails) {
	        if (hotelContactDetails.getId() != null && hotelContactDetails.getId().equals(mailCentre.getHotelContactDetailsId())) {

	            // Fetch existing linked mail types safely
	            List<LinkedHotelContactDetailsMailType> existingMailTypes = Optional.ofNullable(hotelContactDetails.getMailType())
	                    .orElse(new ArrayList<>());

	            // clearing the existing mail types
	            existingMailTypes.clear();
	            
	            // Process new mail types safely
	            List<LinkedHotelContactDetailsMailType> newMailTypes = Optional.ofNullable(mailCentre.getMailCentreIds())
	                    .orElse(Collections.emptyList()) // Handle null safely
	                    .stream()
	                    .map(mailCentreId -> {
	                        MasterMailType masterMailType = hotelMailCentreRepository.findById(mailCentreId)
	                                .orElseThrow(() -> new EntityNotFoundException(
	                                        "Mail Type not found with ID: " + mailCentreId));
	                        

	                        return new LinkedHotelContactDetailsMailType(hotelContactDetails, masterMailType);
	                    })
	                    .collect(Collectors.toList());

	            // Add new mail types without overriding existing ones
	            existingMailTypes.addAll(newMailTypes);
	            
	            if(!existingMailTypes.isEmpty())
	            {
	            	isUpdated = true;
	            }
	            hotelContactDetails.setMailType(existingMailTypes);

	            // Explicitly save to ensure persistence
	            hotelContactDetailsRepository.save(hotelContactDetails);
	            
	            
	        }
	    }
	    return isUpdated ? "Mail Centre details successfully added." : "No changes made. Contact details not found or mail centre IDs already exist.";
	}


	@Override
	@Transactional
	public List<HotelContactDetailsDTO> getMailCentre(Long hotelId) {
		// TODO Auto-generated method stub

		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + hotelId));

		return Optional.ofNullable(hotel.getContactDetails())
				.orElse(Collections.emptyList())
				.stream()
				.map(hotelContactDetails -> {
					HotelContactDetailsDTO dto = new HotelContactDetailsDTO();
					
					dto.setId(hotelContactDetails.getId());
					dto.setContactPerson(hotelContactDetails.getContactPerson());
					dto.setContactTypeId(hotelContactDetails.getContactType() != null
							? hotelContactDetails.getContactType().getContacttypeId()
							: null);
					dto.setHotelId(hotelContactDetails.getHotel() != null ? hotelContactDetails.getHotel().getHotelId()
							: null);
					dto.setMobileNumber(hotelContactDetails.getMobileNumber());
					dto.setPersonalEmail(hotelContactDetails.getPersonalEmail());
					dto.setTeleNumber(hotelContactDetails.getTeleNumber());

					
					// Handle mail type safely
					List<Long> mailTypeIds = Optional
							.ofNullable(hotelContactDetails.getMailType())
							.orElse(Collections.emptyList())
							.stream()
							.filter(a -> a.getMasterMailType() != null)
							.map(a -> a.getMasterMailType().getId())
							.collect(Collectors.toList());

					dto.setMailTyIds(mailTypeIds);
					return dto;
				}).collect(Collectors.toList());

}
	
}