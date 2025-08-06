package com.choosenfly.hotelbookingsystem.agent.controller;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.service.AgentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAgent(@Valid @RequestBody AgentRegistrationRequestDTO request) {
     
    	agentService.registerAgent(request);
        return ResponseEntity.ok("Agent registered successfully");
    }
}