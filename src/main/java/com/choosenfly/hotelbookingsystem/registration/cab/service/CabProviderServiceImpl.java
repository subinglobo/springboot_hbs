package com.choosenfly.hotelbookingsystem.registration.cab.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.configuration.FileStorageProperties;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabListDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabLocationDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabProviderDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.Cab;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabLocation;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabProvider;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabLocationRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabProviderRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabRespository;
import com.choosenfly.hotelbookingsystem.registration.exceptions.FileStorageException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CabProviderServiceImpl implements CabProviderService{
	
	private final CabProviderRepository cabProviderRepository;
	
	private final MasterCountryRepository masterCountryRepository;
	
	private final MasterStateRepository masterStateRepository;
	
	private final CabRespository cabRespository;
	
	private final CabLocationRepository cabLocationRepository;
	
	 private final String uploadDir;
	
	public CabProviderServiceImpl(CabProviderRepository cabProviderRepository,
			MasterCountryRepository masterCountryRepository,MasterStateRepository masterStateRepository,CabRespository cabRespository,
			CabLocationRepository cabLocationRepository,FileStorageProperties fileStorageProperties){
		this.cabProviderRepository = cabProviderRepository;
		this.masterCountryRepository=masterCountryRepository;
		this.masterStateRepository=masterStateRepository;
		this.cabRespository=cabRespository;
		this.cabLocationRepository=cabLocationRepository;
		this.uploadDir = fileStorageProperties.getDirectory();
	}
	@Override
	public CabProviderDTO registerCabProvider(@Valid CabProviderDTO request) {
		
		System.out.println("request ::::"+request);
		// TODO Auto-generated method stub
		  CabProvider provider = new CabProvider();
		    provider.setProviderName(request.getProvidername());
		    provider.setContactPerson(request.getContactperson());
		    provider.setEmailId(request.getEmailid());
		    provider.setPhoneNumber(request.getPhonenumber());

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
		            
		            handleActivityImageUpload(cabDTO.getCabImage(), cab);
		            
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

	private void handleActivityImageUpload(MultipartFile cabImage, Cab cab) {
		// TODO Auto-generated method stub
	    if (cabImage == null || cabImage.isEmpty()) return;

	    try {
	        String filename = System.currentTimeMillis() + "_" + cabImage.getOriginalFilename();
	        File dest = new File(uploadDir, filename);

	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }

	        cabImage.transferTo(dest);
	        cab.setCabPic(dest.getAbsolutePath());

	    } catch (IOException e) {
	        throw new FileStorageException("Failed to store activity image", e);
	    }
	}
	private CabProviderDTO mapToDTO(CabProvider savedProvider) {
		// TODO Auto-generated method stub
		 CabProviderDTO response = new CabProviderDTO();
		    response.setCabprovider(savedProvider.getCabProviderId());
		    response.setProvidername(savedProvider.getProviderName());
		    response.setContactperson(savedProvider.getContactPerson());
		    response.setEmailid(savedProvider.getEmailId());
		    response.setPhonenumber(savedProvider.getPhoneNumber());

		    if (savedProvider.getCabs() != null) {
		        List<CabDTO> cabDTOList = savedProvider.getCabs().stream().map(cab -> {
		            CabDTO cabDTO = new CabDTO();
		            cabDTO.setCabId(cab.getCabId());
		            cabDTO.setName(cab.getName());
		            cabDTO.setCabCode(cab.getCabCode());
		            cabDTO.setCountryid(cab.getCountry().getId());
		            cabDTO.setPlaceid(cab.getPlace().getId());
		            cabDTO.setCabpic(cab.getCabPic());
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

	    // 2️⃣ Update main provider fields
	    provider.setProviderName(reqDTO.getProvidername());
	    provider.setContactPerson(reqDTO.getContactperson());
	    provider.setEmailId(reqDTO.getEmailid());
	    provider.setPhoneNumber(reqDTO.getPhonenumber());

	    // 3️⃣ Handle nested cabs
	    if (reqDTO.getCabList() != null) {
	        List<Cab> finalCabs = new ArrayList<>();

	        for (CabDTO cabDTO : reqDTO.getCabList()) {

	            // ✅ Fetch existing cab or create new
	            Cab cab = (cabDTO.getCabId() != null)
	                    ? cabRespository.findById(cabDTO.getCabId()).orElse(new Cab())
	                    : new Cab();

	            cab.setCabProvider(provider);

	            // ✅ Update cab fields
	            MasterCountry country = masterCountryRepository.findById(cabDTO.getCountryid())
	                    .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + cabDTO.getCountryid()));
	            MasterState state = masterStateRepository.findById(cabDTO.getPlaceid())
	                    .orElseThrow(() -> new EntityNotFoundException("State not found with id: " + cabDTO.getPlaceid()));

	            cab.setCountry(country);
	            cab.setPlace(state);
	            cab.setName(cabDTO.getName());
	            cab.setCabCode(cabDTO.getCabCode());
	            
	            MultipartFile newImage = cabDTO.getCabImage();
	            if (newImage != null && !newImage.isEmpty()) {
	                // delete old image if exists
	                if (cab.getCabPic() != null) {
	                    File oldFile = new File(cab.getCabPic());
	                    if (oldFile.exists()) oldFile.delete();
	                }

	                try {
	                    String filename = System.currentTimeMillis() + "_" + newImage.getOriginalFilename();
	                    File dest = new File(uploadDir, filename);
	                    if (!dest.getParentFile().exists()) dest.getParentFile().mkdirs();
	                    newImage.transferTo(dest);
	                    cab.setCabPic(dest.getAbsolutePath());
	                } catch (IOException e) {
	                    throw new FileStorageException("Failed to store cab image", e);
	                }
	            }
	            else {
	            	  if (cab.getCabPic() != null) {
	            	        File oldFile = new File(cab.getCabPic());
	            	        if (oldFile.exists()) oldFile.delete();
	            	        cab.setCabPic(null);
	            	    }
	            }

	            // ✅ Handle nested locations
	            List<CabLocation> finalLocations = new ArrayList<>();
	            if (cabDTO.getCabLocationDTOList() != null) {
	                for (CabLocationDTO locDTO : cabDTO.getCabLocationDTOList()) {
	                    CabLocation loc = (locDTO.getCablocationId() != null)
	                            ? cabLocationRepository.findById(locDTO.getCablocationId()).orElse(new CabLocation())
	                            : new CabLocation();

	                    loc.setCab(cab);
	                    loc.setPickup(locDTO.getPickup());
	                    loc.setDropoff(locDTO.getDropoff());
	                    finalLocations.add(loc);
	                }
	            }
//	            cab.setCabLocations(finalLocations);
	            cab.getCabLocations().clear();
	            cab.getCabLocations().addAll(finalLocations);

	            finalCabs.add(cab);
	        }

//	        provider.setCabs(finalCabs);
	        provider.getCabs().clear();
	        provider.getCabs().addAll(finalCabs);
	    }

	    // 4️⃣ Save provider
	    CabProvider savedProvider = cabProviderRepository.save(provider);
	    return mapToDTO(savedProvider);
	}

	
	@Transactional
	public ResponseEntity<String> deleteCabProviderRegistrationDetails(Long id) {
	    
	    // 1️⃣ Fetch provider by ID, throw exception if not found
	    CabProvider provider = cabProviderRepository.findById(id)
	            .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException(
	                    "CabProvider not found with id: " + id));
	    

	    // 2️⃣ Delete associated images from disk
	    if (provider.getCabs() != null) {
	        for (Cab cab : provider.getCabs()) {
	            String imagePath = cab.getCabPic();
	            if (imagePath != null) {
	                File file = new File(imagePath);
	                if (file.exists()) {
	                    file.delete();
	                }
	            }
	        }
	    }

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
//	        dto.setDeleted(false); // optional, set as needed

	        if (provider.getCabs() != null) {
	            List<CabDTO> cabDTOList = provider.getCabs().stream().map(cab -> {
	                CabDTO cabDTO = new CabDTO();
	                cabDTO.setCabId(cab.getCabId());
	                cabDTO.setName(cab.getName());
	                cabDTO.setCabCode(cab.getCabCode());
	                cabDTO.setCountryid(cab.getCountry().getId());
	                cabDTO.setPlaceid(cab.getPlace().getId());
	                cabDTO.setCabpic(cab.getCabPic());
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
	public List<CabListDTO> getCabList(Long providerId) {

	    // 1️⃣ Fetch provider
	    CabProvider provider = cabProviderRepository.findById(providerId)
	            .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException(
	                    "CabProvider not found with id: " + providerId));

	    // 2️⃣ Map Cabs to CabListDTO
	    if (provider.getCabs() == null || provider.getCabs().isEmpty()) {
	        return Collections.emptyList();
	    }

	    List<CabListDTO> cabList = provider.getCabs().stream().map(cab -> {
	        CabListDTO dto = new CabListDTO();
	        dto.setCabId(cab.getCabId());
	        dto.setCabName(cab.getName());
	        return dto;
	    }).toList();

	    return cabList;
	}
 
}
