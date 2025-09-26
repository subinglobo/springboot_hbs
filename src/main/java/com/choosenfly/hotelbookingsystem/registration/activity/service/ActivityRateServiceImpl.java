package com.choosenfly.hotelbookingsystem.registration.activity.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.configuration.FileStorageProperties;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityInclusionAndTermsDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityRateDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityValidityDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityInclusionAndTerms;
import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityProvider;
import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityRate;
import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityRateMarketType;
import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityRateValidity;
import com.choosenfly.hotelbookingsystem.registration.activity.repository.ActivityInclusionAndTermsRepository;
import com.choosenfly.hotelbookingsystem.registration.activity.repository.ActivityProviderRepository;
import com.choosenfly.hotelbookingsystem.registration.activity.repository.ActivityRateRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.exceptions.BadRequestException;
import com.choosenfly.hotelbookingsystem.registration.exceptions.FileStorageException;

import jakarta.validation.Valid;

@Service
public class ActivityRateServiceImpl implements  ActivityRateService{

	private final ActivityRateRepository activityRateRepository;
	
	private final ActivityProviderRepository activityProviderRepository;
	
	private final MasterCountryRepository masterCountryRepository;
	
	private final MasterStateRepository masterStateRepository;
	
	private MasterMarketTypeRepository marketTypeRepository;
	
	private ActivityInclusionAndTermsRepository inclusionAndTermsRepository; 
	
	 private final String uploadDir;
	
	@Autowired
	public ActivityRateServiceImpl(ActivityRateRepository activityRateRepository,
			ActivityProviderRepository activityProviderRepository,MasterCountryRepository masterCountryRepository,
			MasterStateRepository masterStateRepository,MasterMarketTypeRepository marketTypeRepository,
			FileStorageProperties fileStorageProperties,ActivityInclusionAndTermsRepository inclusionAndTermsRepository) {
		this.activityRateRepository=activityRateRepository;
		this.activityProviderRepository=activityProviderRepository;
		this.masterCountryRepository=masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
		this.marketTypeRepository=marketTypeRepository;		
		this.uploadDir = fileStorageProperties.getDirectory();
		this.inclusionAndTermsRepository=inclusionAndTermsRepository;
	}

	public ActivityRateDTO saveActivityRate(@Valid ActivityRateDTO requestDTO) {
	    // Fetch provider
	    ActivityProvider activityProvider = activityProviderRepository.findById(requestDTO.getProviderId())
	            .orElseThrow(() -> new BadRequestException("ActivityProvider not found with id: " + requestDTO.getProviderId()));

	    // Fetch country
	    MasterCountry countryEntity = masterCountryRepository.findById(requestDTO.getCountryId())
	            .orElseThrow(() -> new BadRequestException("Country not found for id: " + requestDTO.getCountryId()));

	    // Fetch state/place
	    MasterState stateEntity = masterStateRepository.findById(requestDTO.getPlaceId())
	            .orElseThrow(() -> new BadRequestException("State not found for id: " + requestDTO.getPlaceId()));

	    // Create main entity
	    ActivityRate activityRate = new ActivityRate();
	    activityRate.setProviderId(activityProvider);
	    activityRate.setCountry(countryEntity);
	    activityRate.setPlaceId(stateEntity);

	    // Map simple fields
	    activityRate.setActivityName(requestDTO.getActivityName());
	    activityRate.setActivityCode(requestDTO.getActivityCode());
	    activityRate.setActivityDetails(requestDTO.getActivityDetails());
	    activityRate.setReportingpoint(requestDTO.getReportingPoint());
	    activityRate.setDurationHr(requestDTO.getDurationHr());
	    activityRate.setDurationMin(requestDTO.getDurationMin());
	    activityRate.setTotalUsersAllowed(requestDTO.getTotalUsersAllowed());
	    activityRate.setChildAgeMin(requestDTO.getChildAgeMin());
	    activityRate.setChildAgeMax(requestDTO.getChildAgeMax());
	    activityRate.setActivityType(requestDTO.getActivityType());
	    activityRate.setActivityRate(requestDTO.getActivityRate());
	    activityRate.setMaxPax(requestDTO.getMaxPax());
	    activityRate.setRating(requestDTO.getRating());
	    // Validate and map MarketTypes
	    if (requestDTO.getMarketType() == null || requestDTO.getMarketType().isEmpty()) {
	        throw new BadRequestException("Market is required");
	    }
	    List<ActivityRateMarketType> marketTypes = requestDTO.getMarketType().stream()
	            .map(marketTypeId -> {
	                MasterMarketType market = marketTypeRepository.findById(marketTypeId)
	                        .orElseThrow(() -> new BadRequestException("MarketType not found with id: " + marketTypeId));
	                ActivityRateMarketType mt = new ActivityRateMarketType();
	                mt.setMarketType(market);
	                mt.setActivityRate(activityRate);
	                return mt;
	            })
	            .collect(Collectors.toList());
	    activityRate.setMarketTypes(marketTypes);

	    // Map Validities
	    List<ActivityRateValidity> validities = requestDTO.getValidity().stream()
	            .map(v -> {
	                ActivityRateValidity validity = new ActivityRateValidity();
	                validity.setValidityFrom(v.getValidityFrom());
	                validity.setValidityTo(v.getValidityTo());
	                validity.setActivityRate(activityRate);
	                return validity;
	            })
	            .collect(Collectors.toList());
	    activityRate.setValidities(validities);

	    // Handle image upload
	    handleActivityImageUpload(requestDTO.getActivityImage(), activityRate);

	    // Save and return DTO
	    ActivityRate saved = activityRateRepository.save(activityRate);

	    ActivityRateDTO response = new ActivityRateDTO();
	    response.setActivityRateId(saved.getActivityRateId());
	    return response;
	}

	/**
	 * Utility method for handling activity image upload
	 */
	private void handleActivityImageUpload(MultipartFile activityImage, ActivityRate activityRate) {
	    if (activityImage == null || activityImage.isEmpty()) return;

	    try {
	        String filename = System.currentTimeMillis() + "_" + activityImage.getOriginalFilename();
	        File dest = new File(uploadDir, filename);

	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }

	        activityImage.transferTo(dest);
	        activityRate.setActivityImage(dest.getAbsolutePath());

	    } catch (IOException e) {
	        throw new FileStorageException("Failed to store activity image", e);
	    }
	}

	@Override
	public ActivityRateDTO getActivityRateById(Long id) {
	    ActivityRate activityRate = activityRateRepository.findById(id)
	            .orElseThrow(() -> new BadRequestException("ActivityRate not found with id: " + id));

	    ActivityRateDTO activityRateDTO = mapToDto(activityRate);
	    
		return activityRateDTO;
	   
	}

	private ActivityRateDTO mapToDto(ActivityRate activityRate) {
		// TODO Auto-generated method stub
		 ActivityRateDTO dto = new ActivityRateDTO();
		    dto.setActivityRateId(activityRate.getActivityRateId());
		    dto.setProviderId(activityRate.getProviderId().getProviderId());
		    dto.setActivityName(activityRate.getActivityName());
		    dto.setActivityCode(activityRate.getActivityCode());
		    dto.setActivityDetails(activityRate.getActivityDetails());
		    dto.setReportingPoint(activityRate.getReportingpoint());
		    dto.setDurationHr(activityRate.getDurationHr());
		    dto.setDurationMin(activityRate.getDurationMin());
		    dto.setTotalUsersAllowed(activityRate.getTotalUsersAllowed());
		    dto.setChildAgeMin(activityRate.getChildAgeMin());
		    dto.setChildAgeMax(activityRate.getChildAgeMax());
		    dto.setActivityType(activityRate.getActivityType());
		    dto.setActivityRate(activityRate.getActivityRate());
		    dto.setMaxPax(activityRate.getMaxPax());
		    dto.setRating(activityRate.getRating());
		    // Country and Place (state)
		    if (activityRate.getCountry() != null) {
		        dto.setCountryId(activityRate.getCountry().getId());
		    }
		    if (activityRate.getPlaceId() != null) {
		        dto.setPlaceId(activityRate.getPlaceId().getId());
		    }

		    // Market types
		    List<Long> marketTypeIds = activityRate.getMarketTypes().stream()
		            .map(mt -> mt.getMarketType().getMarketTypeId())
		            .collect(Collectors.toList());
		    dto.setMarketType(marketTypeIds);

		    // Validities
		    List<ActivityValidityDTO> validities = activityRate.getValidities().stream()
		            .map(v -> {
		                ActivityValidityDTO validityDTO = new ActivityValidityDTO();
		                validityDTO.setValidityId(v.getValidityId());
		                validityDTO.setValidityFrom(v.getValidityFrom());
		                validityDTO.setValidityTo(v.getValidityTo());
		                return validityDTO;
		            })
		            .collect(Collectors.toList());
		    dto.setValidity(validities);

		    // Image
		    dto.setImagePath(activityRate.getActivityImage());

		    return dto;
	}

	@Override
	public ActivityRateDTO editActivityRate(Long id, @Valid ActivityRateDTO reqDTO) {
	    // 1️⃣ Fetch existing record
	    ActivityRate activityRate = activityRateRepository.findById(id)
	            .orElseThrow(() -> new BadRequestException("ActivityRate not found with id: " + id));

	    // 2️⃣ Update provider
	    ActivityProvider activityProvider = activityProviderRepository.findById(reqDTO.getProviderId())
	            .orElseThrow(() -> new BadRequestException("ActivityProvider not found with id: " + reqDTO.getProviderId()));
	    activityRate.setProviderId(activityProvider);

	    // 3️⃣ Update country & place
	    MasterCountry countryEntity = masterCountryRepository.findById(reqDTO.getCountryId())
	            .orElseThrow(() -> new BadRequestException("Country not found for id : " + reqDTO.getCountryId()));
	    activityRate.setCountry(countryEntity);

	    MasterState stateEntity = masterStateRepository.findById(reqDTO.getPlaceId())
	            .orElseThrow(() -> new BadRequestException("State not found for id: " + reqDTO.getPlaceId()));
	    activityRate.setPlaceId(stateEntity);

	    // 4️⃣ Update basic fields
	    activityRate.setActivityName(reqDTO.getActivityName());
	    activityRate.setActivityCode(reqDTO.getActivityCode());
	    activityRate.setActivityDetails(reqDTO.getActivityDetails());
	    activityRate.setReportingpoint(reqDTO.getReportingPoint());
	    activityRate.setDurationHr(reqDTO.getDurationHr());
	    activityRate.setDurationMin(reqDTO.getDurationMin());
	    activityRate.setTotalUsersAllowed(reqDTO.getTotalUsersAllowed());
	    activityRate.setChildAgeMin(reqDTO.getChildAgeMin());
	    activityRate.setChildAgeMax(reqDTO.getChildAgeMax());
	    activityRate.setActivityType(reqDTO.getActivityType());
	    activityRate.setActivityRate(reqDTO.getActivityRate());
	    activityRate.setMaxPax(reqDTO.getMaxPax());
	    activityRate.setRating(reqDTO.getRating());
	    // 5️⃣ Update market types
	    if (reqDTO.getMarketType() == null || reqDTO.getMarketType().isEmpty()) {
	        throw new BadRequestException("Market is required");
	    }
	    activityRate.getMarketTypes().clear(); // remove old entries
	    List<ActivityRateMarketType> marketTypes = reqDTO.getMarketType().stream()
	            .map(marketTypeId -> {
	                MasterMarketType market = marketTypeRepository.findById(marketTypeId)
	                        .orElseThrow(() -> new BadRequestException("MarketType not found for id: " + marketTypeId));
	                ActivityRateMarketType mt = new ActivityRateMarketType();
	                mt.setMarketType(market);
	                mt.setActivityRate(activityRate);
	                return mt;
	            })
	            .collect(Collectors.toList());
	    activityRate.getMarketTypes().addAll(marketTypes);

	    // 6️⃣ Update validities
	    activityRate.getValidities().clear(); // remove old entries
	    List<ActivityRateValidity> validities = reqDTO.getValidity().stream()
	            .map(v -> {
	                ActivityRateValidity validity = new ActivityRateValidity();
	                validity.setValidityFrom(v.getValidityFrom());
	                validity.setValidityTo(v.getValidityTo());
	                validity.setActivityRate(activityRate);
	                return validity;
	            })
	            .collect(Collectors.toList());
	    activityRate.getValidities().addAll(validities);

	    // 7️⃣ Handle image update (replace if new image provided)
	    MultipartFile newImage = reqDTO.getActivityImage();
	    if (newImage != null && !newImage.isEmpty()) {
	        // delete old image if exists
	        if (activityRate.getActivityImage() != null) {
	            File oldFile = new File(activityRate.getActivityImage());
	            if (oldFile.exists()) {
	                oldFile.delete();
	            }
	        }

	        try {
	            String filename = System.currentTimeMillis() + "_" + newImage.getOriginalFilename();
	            File dest = new File(uploadDir, filename);
	            if (!dest.getParentFile().exists()) {
	                dest.getParentFile().mkdirs();
	            }
	            newImage.transferTo(dest);
	            activityRate.setActivityImage(dest.getAbsolutePath());
	        } catch (IOException e) {
	            throw new FileStorageException("Failed to store activity image", e);
	        }
	    }
	    else {
	        if (activityRate.getActivityImage() != null) {
	            File oldFile = new File(activityRate.getActivityImage());
	            if (oldFile.exists()) {
	                oldFile.delete();
	            }
	        }
	        activityRate.setActivityImage(null);
	    }

	    // 8️⃣ Save and return updated DTO
	    ActivityRate updated = activityRateRepository.save(activityRate);

	    ActivityRateDTO response = mapToDto(updated);

	    return response;
	}
	
	@Override
	public ResponseEntity<String> deleteActivityRate(Long id) {
	    ActivityRate activityRate = activityRateRepository.findById(id)
	            .orElseThrow(() -> new BadRequestException("ActivityRate not found with id: " + id));

	    // 🗑️ Delete image file if exists
	    if (activityRate.getActivityImage() != null) {
	        File imageFile = new File(activityRate.getActivityImage());
	        if (imageFile.exists()) {
	            imageFile.delete(); // optional: ignore result, or log if fails
	        }
	    }

	    // 🗑️ Delete entity (JPA will handle cascading if configured)
	    activityRateRepository.delete(activityRate);

	    return ResponseEntity.ok("ActivityRate deleted successfully with id: " + id);
	}

	@Override
	public Page<ActivityRateDTO> getActivityRates(Pageable pageable, String search) {
	    Page<ActivityRate> page;

	    if (search != null && !search.trim().isEmpty()) {
	        // 🔍 Search by name/code/details
	        page = activityRateRepository.findByActivityNameContainingIgnoreCase(search, pageable);
	    } else {
	        // 📄 Fetch all
	        page = activityRateRepository.findAll(pageable);
	    }

	    // Map entity -> DTO
	    return page.map(this::mapToDto);
	}
	
	public String saveInclutionAndTerms(@Valid List<ActivityInclusionAndTermsDTO> requestDTOList) {

	    if (requestDTOList == null || requestDTOList.isEmpty()) {
	        throw new BadRequestException("Inclusion and Terms list cannot be empty");
	    }

	    List<ActivityInclusionAndTerms> entities = new ArrayList<>();

	    for (ActivityInclusionAndTermsDTO dto : requestDTOList) {

	        // 1️⃣ Fetch the associated ActivityRate
	        ActivityRate activityRate = activityRateRepository.findById(dto.getActivityRateId())
	                .orElseThrow(() -> new BadRequestException("ActivityRate not found with id: " + dto.getActivityRateId()));

	        // 2️⃣ Map DTO to entity
	        ActivityInclusionAndTerms entity = new ActivityInclusionAndTerms();
	        entity.setDatas(dto.getData());
	        entity.setType(dto.getType());
	        entity.setActivityRate(activityRate);

	        entities.add(entity);
	    }

	    // 3️⃣ Save all entities
	    inclusionAndTermsRepository.saveAll(entities);

	    return "Inclusions and Terms saved successfully";
	}

}
