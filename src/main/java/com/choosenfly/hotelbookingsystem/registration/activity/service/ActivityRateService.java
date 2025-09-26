package com.choosenfly.hotelbookingsystem.registration.activity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityInclusionAndTermsDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityRateDTO;

import jakarta.validation.Valid;

public interface ActivityRateService {

	ActivityRateDTO saveActivityRate(@Valid ActivityRateDTO requestDTO);

	ActivityRateDTO getActivityRateById(Long id);

	ActivityRateDTO editActivityRate(Long id, @Valid ActivityRateDTO reqDTO);

	ResponseEntity<String> deleteActivityRate(Long id);

	Page<ActivityRateDTO> getActivityRates(Pageable pageable, String search);

	String saveInclutionAndTerms(@Valid List<ActivityInclusionAndTermsDTO> requestDTO);

}
