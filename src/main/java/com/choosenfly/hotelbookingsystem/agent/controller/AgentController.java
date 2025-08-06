package com.choosenfly.hotelbookingsystem.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.service.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/register")
    public ResponseEntity<AgentResponseDTO> registerAgent(
            @Valid @RequestBody AgentRegistrationRequestDTO request) {
        
        AgentResponseDTO agent = agentService.registerAgent(request);
        
        return new ResponseEntity<>(agent, HttpStatus.CREATED);
    }
}