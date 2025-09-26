package com.choosenfly.hotelbookingsystem.registration.activity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityProviderDTO;

import jakarta.validation.Valid;

public interface ActivityProviderService {

	ActivityProviderDTO registerActivityProvider(@Valid ActivityProviderDTO requestDTO);

	ActivityProviderDTO getActivityProviderById(Long id);

	ActivityProviderDTO editActivityProvider(Long id, @Valid ActivityProviderDTO reqDTO);

	ResponseEntity<String> deleteActivityProvider(Long id);

	Page<ActivityProviderDTO> getActivityProviders(Pageable pageable, String search);

}
