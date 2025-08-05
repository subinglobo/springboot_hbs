package com.choosenfly.hotelbookingsystem.masters.service.termsAndCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterTermsAndConditionDTO;

import jakarta.validation.Valid;

public interface TermsAndConditionInterface {

	Long saveTermsAndCondition(@Valid MasterTermsAndConditionDTO termsDTO);

	MasterTermsAndConditionDTO getTermsAndConditionById(Long id);

	MasterTermsAndConditionDTO editTermsAndCondition(Long id, @Valid MasterTermsAndConditionDTO termsDTO);

	ResponseEntity<String> deleteTermsAndCondition(Long id);

	Page<MasterTermsAndConditionDTO> getAllTermsAndCondition(Pageable pageable, String search);

}
