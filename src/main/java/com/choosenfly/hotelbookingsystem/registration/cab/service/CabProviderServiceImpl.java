package com.choosenfly.hotelbookingsystem.registration.cab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabLocationDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabProviderDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.Cab;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabLocation;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabProvider;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabProviderRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CabProviderServiceImpl implements CabProviderService{
	
	private final CabProviderRepository cabProviderRepository;
	
	private final MasterCountryRepository masterCountryRepository;
	
	private final MasterStateRepository masterStateRepository;
	
	public CabProviderServiceImpl(CabProviderRepository cabProviderRepository,
			MasterCountryRepository masterCountryRepository,MasterStateRepository masterStateRepository){
		this.cabProviderRepository = cabProviderRepository;
		this.masterCountryRepository=masterCountryRepository;
		this.masterStateRepository=masterStateRepository;
	}
	@Override
	public CabProviderDTO registerCabProvider(@Valid CabProviderDTO request) {
		// TODO Auto-generated method stub
		  CabProvider provider = new CabProvider();
		    provider.setProviderName(request.getProvidername());
		    provider.setContactPerson(request.getContactperson());
		    provider.setEmailId(request.getEmailid());
		    provider.setPhoneNumber(request.getPhonenumber());
		    provider.setIsActive(request.isActive());

		    // 2️⃣ Validate & map nested Cabs
		    if (request.getCabList() != null) {
		        List<Cab> cabEntities = request.getCabList().stream().map(cabDTO -> {

		        	Cab cab = new Cab();
		        	MasterCountry countryEntity = 
		    				masterCountryRepository.findById(cabDTO.getCountryid()).orElseThrow(() -> new com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException("Country not found for id : " +cabDTO.getCountryid()));
		        	cab.setCountry(countryEntity);
		            // ✅ Validate placeId (city)
		        	MasterState stateEntity = 
		    				masterStateRepository.findById(cabDTO.getPlaceid()).orElseThrow(() -> new EntityNotFoundException("State not found for id: " + cabDTO.getPlaceid()));

		        	cab.setPlace(stateEntity);
		            cab.setName(cabDTO.getName());
		            cab.setCabCode(cabDTO.getCabCode());
		            cab.setIsActive(cabDTO.isActive());

		            // Set the relationship to provider
		            cab.setCabProvider(provider);

		            // Map nested locations
		            if (cabDTO.getCabLocationDTOList() != null) {
		                List<CabLocation> cabLocations = cabDTO.getCabLocationDTOList().stream().map(locDTO -> {
		                    CabLocation loc = new CabLocation();
		                    loc.setPickup(locDTO.getPickup());
		                    loc.setDropoff(locDTO.getDropoff());
		                    loc.setCab(cab);
		                    return loc;
		                }).toList();

		                cab.setCabLocations(cabLocations);
		            }

		            return cab;
		        }).toList();

		        provider.setCabs(cabEntities);
		    }
		    
		    // 3️⃣ Save provider (Cascade saves Cabs & Locations)
		    CabProvider savedProvider = cabProviderRepository.save(provider);
		    
		    CabProviderDTO mapToDTO = mapToDTO(savedProvider);

		    // 4️⃣ Map saved entity back to DTO with IDs
		   return mapToDTO;

	}

	private CabProviderDTO mapToDTO(CabProvider savedProvider) {
		// TODO Auto-generated method stub
		 CabProviderDTO response = new CabProviderDTO();
		    response.setCabprovider(savedProvider.getCabProviderId());
		    response.setProvidername(savedProvider.getProviderName());
		    response.setContactperson(savedProvider.getContactPerson());
		    response.setEmailid(savedProvider.getEmailId());
		    response.setPhonenumber(savedProvider.getPhoneNumber());
		    response.setActive(savedProvider.getIsActive());

		    if (savedProvider.getCabs() != null) {
		        List<CabDTO> cabDTOList = savedProvider.getCabs().stream().map(cab -> {
		            CabDTO cabDTO = new CabDTO();
		            cabDTO.setCabId(cab.getCabId());
		            cabDTO.setName(cab.getName());
		            cabDTO.setCabCode(cab.getCabCode());
		            cabDTO.setCountryid(cab.getCountry().getId());
		            cabDTO.setPlaceid(cab.getPlace().getId());
		            cabDTO.setActive(cab.getIsActive());

		            if (cab.getCabLocations() != null) {
		                List<CabLocationDTO> locDTOList = cab.getCabLocations().stream().map(loc -> {
		                    CabLocationDTO locDTO = new CabLocationDTO();
		                    locDTO.setCablocationId(loc.getCabLocationId());
		                    locDTO.setPickup(loc.getPickup());
		                    locDTO.setDropoff(loc.getDropoff());
		                    return locDTO;
		                }).toList();

		                cabDTO.setCabLocationDTOList(locDTOList);
		            }

		            return cabDTO;
		        }).toList();

		        response.setCabList(cabDTOList);
		    }

		    return response;
	}
	@Override
	@Transactional
	public CabProviderDTO getCabProviderRegistrationDetailsById(Long id) {
	    // 1️⃣ Fetch CabProvider by ID, throw exception if not found
	    CabProvider provider = cabProviderRepository.findById(id)
	            .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException(
	                    "CabProvider not found with id: " + id));

	    // 2️⃣ Map entity to DTO using your existing mapper
	    return mapToDTO(provider);
	}
	@Transactional
	public CabProviderDTO editCabProviderRegistrationDetails(Long id, @Valid CabProviderDTO reqDTO) {

	    // 1️⃣ Fetch existing provider
	    CabProvider provider = cabProviderRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("CabProvider not found with id: " + id));

	    // Update main provider fields
	    provider.setProviderName(reqDTO.getProvidername());
	    provider.setContactPerson(reqDTO.getContactperson());
	    provider.setEmailId(reqDTO.getEmailid());
	    provider.setPhoneNumber(reqDTO.getPhonenumber());
	    provider.setIsActive(reqDTO.isActive());

	    // Handle nested cabs
	    if (reqDTO.getCabList() != null) {
	        Map<Long, Cab> existingCabs = provider.getCabs().stream()
	                .collect(Collectors.toMap(Cab::getCabId, c -> c));

	        List<Cab> finalCabs = new ArrayList<>();
	        for (CabDTO cabDTO : reqDTO.getCabList()) {
	            Cab cab;
	            if (cabDTO.getCabId() != null && existingCabs.containsKey(cabDTO.getCabId())) {
	                cab = existingCabs.get(cabDTO.getCabId());
	            } else {
	                cab = new Cab();
	                cab.setCabProvider(provider);
	            }

	            // Update cab fields
	            MasterCountry country = masterCountryRepository.findById(cabDTO.getCountryid())
	                    .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + cabDTO.getCountryid()));
	            MasterState state = masterStateRepository.findById(cabDTO.getPlaceid())
	                    .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + cabDTO.getPlaceid()));

	            cab.setCountry(country);
	            cab.setPlace(state);
	            cab.setName(cabDTO.getName());
	            cab.setCabCode(cabDTO.getCabCode());
	            cab.setIsActive(cabDTO.isActive());

	            // Handle locations
	            if (cabDTO.getCabLocationDTOList() != null) {
	                Map<Long, CabLocation> existingLocations = cab.getCabLocations().stream()
	                        .collect(Collectors.toMap(CabLocation::getCabLocationId, l -> l));

	                List<CabLocation> finalLocations = new ArrayList<>();
	                for (CabLocationDTO locDTO : cabDTO.getCabLocationDTOList()) {
	                    CabLocation loc;
	                    if (locDTO.getCablocationId() != null && existingLocations.containsKey(locDTO.getCablocationId())) {
	                        loc = existingLocations.get(locDTO.getCablocationId());
	                    } else {
	                        loc = new CabLocation();
	                        loc.setCab(cab);
	                    }
	                    loc.setPickup(locDTO.getPickup());
	                    loc.setDropoff(locDTO.getDropoff());
	                    finalLocations.add(loc);
	                }
	                cab.setCabLocations(finalLocations);
	            }

	            finalCabs.add(cab);
	        }

	        provider.setCabs(finalCabs);
	    }

	    CabProvider savedProvider = cabProviderRepository.save(provider);
	    return mapToDTO(savedProvider);
	}

	@Transactional
	public ResponseEntity<String> deleteCabProviderRegistrationDetails(Long id) {
	    
	    // 1️⃣ Fetch provider by ID, throw exception if not found
	    CabProvider provider = cabProviderRepository.findById(id)
	            .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException(
	                    "CabProvider not found with id: " + id));

	    // 2️⃣ Option 1: Hard delete (remove from DB)
	    cabProviderRepository.delete(provider);

	    // 2️⃣ Option 2: Soft delete (mark as deleted)
	    // provider.setDeleted(true);
	    // cabProviderRepository.save(provider);

	    // 3️⃣ Return success response
	    return ResponseEntity.ok("CabProvider with id " + id + " deleted successfully.");
	}

	@Override
	@Transactional
	public Page<CabProviderDTO> getAllCabProviders(Pageable pageable, String search) {

	    Page<CabProvider> cabProviders;

	    if (search != null && !search.isEmpty()) {
	        // Assuming you have a method in repository to search by provider name or contact person
	        cabProviders = cabProviderRepository.findByProviderNameContainingIgnoreCaseOrContactPersonContainingIgnoreCase(
	                search, search, pageable);
	    } else {
	        cabProviders = cabProviderRepository.findAll(pageable);
	    }

	    // Map entities to DTOs
	    return cabProviders.map(provider -> {
	        CabProviderDTO dto = new CabProviderDTO();
	        dto.setCabprovider(provider.getCabProviderId());
	        dto.setProvidername(provider.getProviderName());
	        dto.setContactperson(provider.getContactPerson());
	        dto.setEmailid(provider.getEmailId());
	        dto.setPhonenumber(provider.getPhoneNumber());
	        dto.setActive(provider.getIsActive());
//	        dto.setDeleted(false); // optional, set as needed

	        if (provider.getCabs() != null) {
	            List<CabDTO> cabDTOList = provider.getCabs().stream().map(cab -> {
	                CabDTO cabDTO = new CabDTO();
	                cabDTO.setCabId(cab.getCabId());
	                cabDTO.setName(cab.getName());
	                cabDTO.setCabCode(cab.getCabCode());
	                cabDTO.setCountryid(cab.getCountry().getId());
	                cabDTO.setPlaceid(cab.getPlace().getId());
	                cabDTO.setActive(cab.getIsActive());
//	                cabDTO.setDeleted(false);

	                if (cab.getCabLocations() != null) {
	                    List<CabLocationDTO> locDTOList = cab.getCabLocations().stream().map(loc -> {
	                        CabLocationDTO locDTO = new CabLocationDTO();
	                        locDTO.setCablocationId(loc.getCabLocationId());
	                        locDTO.setPickup(loc.getPickup());
	                        locDTO.setDropoff(loc.getDropoff());
//	                        locDTO.setDeleted(false);
	                        return locDTO;
	                    }).toList();

	                    cabDTO.setCabLocationDTOList(locDTOList);
	                }

	                return cabDTO;
	            }).toList();

	            dto.setCabList(cabDTOList);
	        }

	        return dto;
	    });
	}

}
