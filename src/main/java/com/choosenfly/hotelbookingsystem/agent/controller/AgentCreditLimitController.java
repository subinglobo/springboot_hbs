package com.choosenfly.hotelbookingsystem.agent.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.agent.dto.AddCreditRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentCreditLimitResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.UpdateCreditLimitRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.service.AgentCreditLimitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agent-credit-limit")
public class AgentCreditLimitController {

    @Autowired
    private AgentCreditLimitService agentCreditLimitService;

    /**
     * Get credit limit by agent ID
     * @param agentId The agent ID
     * @return ResponseEntity with AgentCreditLimitResponseDTO
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<AgentCreditLimitResponseDTO> getCreditLimitByAgentId(@PathVariable Long agentId) {
        AgentCreditLimitResponseDTO response = agentCreditLimitService.getCreditLimitByAgentId(agentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update credit limit for an agent
     * @param request UpdateCreditLimitRequestDTO
     * @return ResponseEntity with AgentCreditLimitResponseDTO
     */
    @PutMapping("/update")
    public ResponseEntity<AgentCreditLimitResponseDTO> updateCreditLimit(
            @Valid @RequestBody UpdateCreditLimitRequestDTO request) {
        AgentCreditLimitResponseDTO response = agentCreditLimitService.updateCreditLimit(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Add additional credit to an agent's available credit limit
     * @param request AddCreditRequestDTO
     * @return ResponseEntity with AgentCreditLimitResponseDTO
     */
    @PostMapping("/add-credit")
    public ResponseEntity<AgentCreditLimitResponseDTO> addAdditionalCredit(
            @Valid @RequestBody AddCreditRequestDTO request) {
        AgentCreditLimitResponseDTO response = agentCreditLimitService.addAdditionalCredit(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Create initial credit limit for a new agent
     * @param agentId The agent ID
     * @param totalCreditLimit Initial total credit limit
     * @return ResponseEntity with AgentCreditLimitResponseDTO
     */
    @PostMapping("/create")
    public ResponseEntity<AgentCreditLimitResponseDTO> createInitialCreditLimit(
            @RequestParam Long agentId, 
            @RequestParam BigDecimal totalCreditLimit) {
        AgentCreditLimitResponseDTO response = agentCreditLimitService.createInitialCreditLimit(agentId, totalCreditLimit);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Check if agent has sufficient credit
     * @param agentId The agent ID
     * @param requiredAmount The amount to check
     * @return ResponseEntity with boolean result
     */
    @GetMapping("/check-sufficient-credit")
    public ResponseEntity<Boolean> hasSufficientCredit(
            @RequestParam Long agentId, 
            @RequestParam BigDecimal requiredAmount) {
        boolean hasSufficientCredit = agentCreditLimitService.hasSufficientCredit(agentId, requiredAmount);
        return new ResponseEntity<>(hasSufficientCredit, HttpStatus.OK);
    }

    /**
     * Deduct credit from agent's available credit limit
     * @param agentId The agent ID
     * @param amount Amount to deduct
     * @return ResponseEntity with success message
     */
    @PostMapping("/deduct-credit")
    public ResponseEntity<String> deductCredit(
            @RequestParam Long agentId, 
            @RequestParam BigDecimal amount) {
        agentCreditLimitService.deductCredit(agentId, amount);
        return new ResponseEntity<>("Credit deducted successfully", HttpStatus.OK);
    }
}
