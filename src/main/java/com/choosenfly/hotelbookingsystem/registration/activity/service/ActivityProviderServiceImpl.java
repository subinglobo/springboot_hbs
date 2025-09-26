package com.choosenfly.hotelbookingsystem.registration.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityProviderDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityProvider;
import com.choosenfly.hotelbookingsystem.registration.activity.repository.ActivityProviderRepository;
import com.choosenfly.hotelbookingsystem.registration.exceptions.BadRequestException;
import com.choosenfly.hotelbookingsystem.registration.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@Service
public class ActivityProviderServiceImpl implements ActivityProviderService{
	
	@Autowired
	private final ActivityProviderRepository activityProviderRepository;
	
	public ActivityProviderServiceImpl(ActivityProviderRepository activityProviderRepository) {
		this.activityProviderRepository = activityProviderRepository;
	}

	@Override
	public ActivityProviderDTO registerActivityProvider(@Valid ActivityProviderDTO requestDTO) {
		// TODO Auto-generated method stub
		ActivityProvider activityProvider = new ActivityProvider();
		activityProvider.setProviderCode(requestDTO.getProviderCode());
		activityProvider.setProviderName(requestDTO.getProviderName());
		activityProvider.setFirstName(requestDTO.getFirstName());
		activityProvider.setLastName(requestDTO.getLastName());
		activityProvider.setMobileNo(requestDTO.getMobileNo());
		activityProvider.setEmailId(requestDTO.getEmailId());
		activityProvider.setAddress(requestDTO.getAddress());
		ActivityProvider save = activityProviderRepository.save(activityProvider);
		
		ActivityProviderDTO activityProviderDTO = new ActivityProviderDTO();
		activityProviderDTO.setProviderId(save.getProviderId());
		return activityProviderDTO;
	}

	@Override
	@Transactional()
	public ActivityProviderDTO getActivityProviderById(Long id) {
	    ActivityProvider activityProvider = activityProviderRepository.findById(id)
	            .orElseThrow(() -> new BadRequestException("ActivityProvider not found with id: " + id));

	    ActivityProviderDTO dto = new ActivityProviderDTO();
	    dto.setProviderId(activityProvider.getProviderId());
	    dto.setProviderName(activityProvider.getProviderName());
	    dto.setFirstName(activityProvider.getFirstName());
	    dto.setLastName(activityProvider.getLastName());
	    dto.setMobileNo(activityProvider.getMobileNo());
	    dto.setEmailId(activityProvider.getEmailId());
	    dto.setAddress(activityProvider.getAddress());
	    dto.setProviderCode(activityProvider.getProviderCode());

	    return dto;
	}

	@Override
	public ActivityProviderDTO editActivityProvider(Long id, @Valid ActivityProviderDTO reqDTO) {
	    // 1️⃣ Fetch existing provider
	    ActivityProvider activityProvider = activityProviderRepository.findById(id)
	            .orElseThrow(() -> new BadRequestException("ActivityProvider not found with id: " + id));

	    // 2️⃣ Update fields
	    activityProvider.setProviderName(reqDTO.getProviderName());
	    activityProvider.setFirstName(reqDTO.getFirstName());
	    activityProvider.setLastName(reqDTO.getLastName());
	    activityProvider.setMobileNo(reqDTO.getMobileNo());
	    activityProvider.setEmailId(reqDTO.getEmailId());
	    activityProvider.setAddress(reqDTO.getAddress());
	    activityProvider.setProviderCode(reqDTO.getProviderCode());

	    // 3️⃣ Save updated entity
	    ActivityProvider updatedProvider = activityProviderRepository.save(activityProvider);

	    // 4️⃣ Map to DTO and return
	    ActivityProviderDTO dto = new ActivityProviderDTO();
	    dto.setProviderId(updatedProvider.getProviderId());
	    dto.setProviderName(updatedProvider.getProviderName());
	    dto.setFirstName(updatedProvider.getFirstName());
	    dto.setLastName(updatedProvider.getLastName());
	    dto.setMobileNo(updatedProvider.getMobileNo());
	    dto.setEmailId(updatedProvider.getEmailId());
	    dto.setAddress(updatedProvider.getAddress());
	    dto.setProviderCode(updatedProvider.getProviderCode());

	    return dto;
	}

	@Override
	public ResponseEntity<String> deleteActivityProvider(Long id) {
	    // 1️⃣ Fetch the existing provider
	    ActivityProvider activityProvider = activityProviderRepository.findById(id)
	            .orElseThrow(() -> new BadRequestException("ActivityProvider not found with id: " + id));

	    // 2️⃣ Delete the provider
	    activityProviderRepository.delete(activityProvider);

	    // 3️⃣ Return success response
	    return ResponseEntity.ok("ActivityProvider deleted successfully!  with id"+id);
	}

	@Override
	public Page<ActivityProviderDTO> getActivityProviders(Pageable pageable, String search) {
	    Page<ActivityProvider> providers;

	    if (search != null && !search.isEmpty()) {
	        // Search by provider name, first name, or last name
	        providers = activityProviderRepository.findByProviderNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
	                search, search, search, pageable
	        );
	    } else {
	        // Fetch all providers paginated
	        providers = activityProviderRepository.findAll(pageable);
	    }

	    // Convert to DTOs
	    return providers.map(provider -> {
	        ActivityProviderDTO dto = new ActivityProviderDTO();
	        dto.setProviderId(provider.getProviderId());
	        dto.setProviderName(provider.getProviderName());
	        dto.setFirstName(provider.getFirstName());
	        dto.setLastName(provider.getLastName());
	        dto.setMobileNo(provider.getMobileNo());
	        dto.setEmailId(provider.getEmailId());
	        dto.setAddress(provider.getAddress());
	        dto.setProviderCode(provider.getProviderCode());
	        return dto;
	    });
	}

}
