package com.choosenfly.hotelbookingsystem.agent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentResponseDTO;

import jakarta.validation.Valid;

public interface AgentService {
	AgentResponseDTO registerAgent(AgentRegistrationRequestDTO request);

	AgentRegistrationRequestDTO getAgentRegistrationDetailsById(Long id);

	AgentRegistrationRequestDTO editAgentRegistrationDetails(Long id, @Valid AgentRegistrationRequestDTO reqDTO);

	ResponseEntity<String> deleteAgentRegistrationDetails(Long id);

	Page<AgentRegistrationRequestDTO> getAllAgentRegistrationDetails(Pageable pageable, String searchTerm);

}