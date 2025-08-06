package com.choosenfly.hotelbookingsystem.agent.service;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;

public interface AgentService {
    void registerAgent(AgentRegistrationRequestDTO request);
}