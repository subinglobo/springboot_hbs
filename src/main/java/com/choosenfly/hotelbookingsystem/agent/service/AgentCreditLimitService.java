package com.choosenfly.hotelbookingsystem.agent.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.agent.dto.AddCreditRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentCreditLimitResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.UpdateCreditLimitRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.entity.Agent;
import com.choosenfly.hotelbookingsystem.agent.entity.AgentCreditLimit;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentCreditLimitRepository;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentRepository;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;

@Service
@Transactional
public class AgentCreditLimitService {

    @Autowired
    private AgentCreditLimitRepository agentCreditLimitRepository;

    @Autowired
    private AgentRepository agentRepository;

    /**
     * Get credit limit by agent ID
     * @param agentId The agent ID
     * @return AgentCreditLimitResponseDTO
     * @throws EntityNotFoundException if agent or credit limit not found
     */
    @Transactional(readOnly = true)
    public AgentCreditLimitResponseDTO getCreditLimitByAgentId(Long agentId) {
        // Verify agent exists
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + agentId));

        // Get credit limit
        AgentCreditLimit creditLimit = agentCreditLimitRepository.findByAgentId(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Credit limit not found for agent ID: " + agentId));

        return mapToResponseDTO(creditLimit);
    }

    /**
     * Update credit limit for an agent
     * @param request UpdateCreditLimitRequestDTO
     * @return AgentCreditLimitResponseDTO
     * @throws EntityNotFoundException if agent not found
     */
    public AgentCreditLimitResponseDTO updateCreditLimit(UpdateCreditLimitRequestDTO request) {
        // Verify agent exists
        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + request.getAgentId()));

        // Validate that available credit doesn't exceed total credit
        if (request.getAvailableCreditLimit().compareTo(request.getTotalCreditLimit()) > 0) {
            throw new IllegalArgumentException("Available credit limit cannot exceed total credit limit");
        }

        // Check if credit limit exists, create or update
        Optional<AgentCreditLimit> existingCreditLimit = agentCreditLimitRepository.findByAgentId(request.getAgentId());
        
        AgentCreditLimit creditLimit;
        if (existingCreditLimit.isPresent()) {
            creditLimit = existingCreditLimit.get();
            creditLimit.setTotalCreditLimit(request.getTotalCreditLimit());
            creditLimit.setAvailableCreditLimit(request.getAvailableCreditLimit());
        } else {
            creditLimit = new AgentCreditLimit(agent, request.getTotalCreditLimit(), request.getAvailableCreditLimit());
        }

        AgentCreditLimit savedCreditLimit = agentCreditLimitRepository.save(creditLimit);
        return mapToResponseDTO(savedCreditLimit);
    }

    /**
     * Add additional credit to an agent's available credit limit
     * @param request AddCreditRequestDTO
     * @return AgentCreditLimitResponseDTO
     * @throws EntityNotFoundException if agent or credit limit not found
     */
    public AgentCreditLimitResponseDTO addAdditionalCredit(AddCreditRequestDTO request) {
        // Verify agent exists
        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + request.getAgentId()));

        // Get existing credit limit
        AgentCreditLimit creditLimit = agentCreditLimitRepository.findByAgentId(request.getAgentId())
                .orElseThrow(() -> new EntityNotFoundException("Credit limit not found for agent ID: " + request.getAgentId()));

        // Add additional credit to available credit limit
        BigDecimal newAvailableCredit = creditLimit.getAvailableCreditLimit().add(request.getAdditionalCredit());
        creditLimit.setAvailableCreditLimit(newAvailableCredit);

        // Also increase total credit limit by the same amount (addon credit increases both)
        BigDecimal newTotalCredit = creditLimit.getTotalCreditLimit().add(request.getAdditionalCredit());
        creditLimit.setTotalCreditLimit(newTotalCredit);

        AgentCreditLimit savedCreditLimit = agentCreditLimitRepository.save(creditLimit);
        return mapToResponseDTO(savedCreditLimit);
    }

    /**
     * Create initial credit limit for a new agent
     * @param agentId The agent ID
     * @param totalCreditLimit Initial total credit limit
     * @return AgentCreditLimitResponseDTO
     */
    public AgentCreditLimitResponseDTO createInitialCreditLimit(Long agentId, BigDecimal totalCreditLimit) {
        // Verify agent exists
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + agentId));

        // Check if credit limit already exists
        if (agentCreditLimitRepository.existsByAgentId(agentId)) {
            throw new IllegalArgumentException("Credit limit already exists for agent ID: " + agentId);
        }

        // Create new credit limit with total and available credit being the same initially
        AgentCreditLimit creditLimit = new AgentCreditLimit(agent, totalCreditLimit, totalCreditLimit);
        AgentCreditLimit savedCreditLimit = agentCreditLimitRepository.save(creditLimit);
        
        return mapToResponseDTO(savedCreditLimit);
    }

    /**
     * Check if agent has sufficient credit
     * @param agentId The agent ID
     * @param requiredAmount The amount to check
     * @return true if sufficient credit available, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean hasSufficientCredit(Long agentId, BigDecimal requiredAmount) {
        Optional<AgentCreditLimit> creditLimit = agentCreditLimitRepository.findByAgentId(agentId);
        if (creditLimit.isPresent()) {
            return creditLimit.get().getAvailableCreditLimit().compareTo(requiredAmount) >= 0;
        }
        return false;
    }

    /**
     * Deduct credit from agent's available credit limit
     * @param agentId The agent ID
     * @param amount Amount to deduct
     * @throws EntityNotFoundException if credit limit not found
     * @throws IllegalArgumentException if insufficient credit
     */
    public void deductCredit(Long agentId, BigDecimal amount) {
        AgentCreditLimit creditLimit = agentCreditLimitRepository.findByAgentId(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Credit limit not found for agent ID: " + agentId));

        if (creditLimit.getAvailableCreditLimit().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient credit. Available: " + 
                creditLimit.getAvailableCreditLimit() + ", Required: " + amount);
        }

        BigDecimal newAvailableCredit = creditLimit.getAvailableCreditLimit().subtract(amount);
        creditLimit.setAvailableCreditLimit(newAvailableCredit);
        agentCreditLimitRepository.save(creditLimit);
    }

    /**
     * Map AgentCreditLimit entity to ResponseDTO
     * @param creditLimit AgentCreditLimit entity
     * @return AgentCreditLimitResponseDTO
     */
    private AgentCreditLimitResponseDTO mapToResponseDTO(AgentCreditLimit creditLimit) {
        String agentName = creditLimit.getAgent().getCompanyName() != null ? 
                          creditLimit.getAgent().getCompanyName() : 
                          creditLimit.getAgent().getFirstName() + " " + creditLimit.getAgent().getLastName();
        
        return new AgentCreditLimitResponseDTO(
                creditLimit.getId(),
                creditLimit.getAgent().getId(),
                agentName,
                creditLimit.getTotalCreditLimit(),
                creditLimit.getAvailableCreditLimit(),
                creditLimit.getUsedCreditLimit()
        );
    }
}
