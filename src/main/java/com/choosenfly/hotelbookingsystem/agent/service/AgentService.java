package com.choosenfly.hotelbookingsystem.agent.service;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentResponseDTO;

public interface AgentService {
	AgentResponseDTO registerAgent(AgentRegistrationRequestDTO request);
}