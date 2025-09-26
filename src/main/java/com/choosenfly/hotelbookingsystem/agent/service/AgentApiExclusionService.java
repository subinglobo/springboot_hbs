package com.choosenfly.hotelbookingsystem.agent.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentApiExclusionRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentApiExclusionResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.ExternalApiResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.entity.Agent;
import com.choosenfly.hotelbookingsystem.agent.entity.AgentApiExclusion;
import com.choosenfly.hotelbookingsystem.agent.entity.ExternalApi;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentApiExclusionRepository;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentRepository;
import com.choosenfly.hotelbookingsystem.agent.repository.ExternalApiRepository;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;

@Service
@Transactional
public class AgentApiExclusionService {

    @Autowired
    private AgentApiExclusionRepository agentApiExclusionRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ExternalApiRepository externalApiRepository;

    /**
     * Get all API exclusions for a specific agent
     * @param agentId The agent ID
     * @return List of AgentApiExclusionResponseDTO
     */
    @Transactional(readOnly = true)
    public List<AgentApiExclusionResponseDTO> getApiExclusionsByAgentId(Long agentId) {
        // Verify agent exists
        agentRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + agentId));

        List<AgentApiExclusion> exclusions = agentApiExclusionRepository.findByAgentId(agentId);
        return exclusions.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Get all excluded APIs for a specific agent
     * @param agentId The agent ID
     * @return List of excluded API codes
     */
    @Transactional(readOnly = true)
    public List<String> getExcludedApiCodesForAgent(Long agentId) {
        // Verify agent exists
        agentRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + agentId));

        return agentApiExclusionRepository.getExcludedApiCodesForAgent(agentId);
    }

    /**
     * Add or update API exclusion for an agent
     * @param request AgentApiExclusionRequestDTO
     * @return AgentApiExclusionResponseDTO
     */
    public AgentApiExclusionResponseDTO addOrUpdateApiExclusion(AgentApiExclusionRequestDTO request) {
        // Verify agent exists
        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + request.getAgentId()));

        // Verify external API exists
        ExternalApi externalApi = externalApiRepository.findByApiCode(request.getApiCode())
                .orElseThrow(() -> new EntityNotFoundException("External API not found with code: " + request.getApiCode()));

        // Check if exclusion already exists
        Optional<AgentApiExclusion> existingExclusion = agentApiExclusionRepository
                .findByAgentIdAndApiCode(request.getAgentId(), request.getApiCode());

        AgentApiExclusion exclusion;
        if (existingExclusion.isPresent()) {
            // Update existing exclusion
            exclusion = existingExclusion.get();
            exclusion.setIsExcluded(request.getIsExcluded());
            exclusion.setExclusionReason(request.getExclusionReason());
        } else {
            // Create new exclusion
            exclusion = new AgentApiExclusion(agent, externalApi, request.getIsExcluded(), request.getExclusionReason());
        }

        AgentApiExclusion savedExclusion = agentApiExclusionRepository.save(exclusion);
        return mapToResponseDTO(savedExclusion);
    }

    /**
     * Remove API exclusion for an agent
     * @param agentId The agent ID
     * @param apiCode The API code
     */
    public void removeApiExclusion(Long agentId, String apiCode) {
        // Verify agent exists
        agentRepository.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found with ID: " + agentId));

        // Verify external API exists
        externalApiRepository.findByApiCode(apiCode)
                .orElseThrow(() -> new EntityNotFoundException("External API not found with code: " + apiCode));

        // Find and delete the exclusion
        Optional<AgentApiExclusion> exclusion = agentApiExclusionRepository
                .findByAgentIdAndApiCode(agentId, apiCode);
        
        if (exclusion.isPresent()) {
            agentApiExclusionRepository.delete(exclusion.get());
        } else {
            throw new EntityNotFoundException("API exclusion not found for agent ID: " + agentId + " and API code: " + apiCode);
        }
    }

    /**
     * Check if API is excluded for an agent
     * @param agentId The agent ID
     * @param apiCode The API code
     * @return true if excluded, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean isApiExcludedForAgent(Long agentId, String apiCode) {
        return agentApiExclusionRepository.isApiExcludedForAgent(agentId, apiCode);
    }

    /**
     * Get all available external APIs
     * @return List of ExternalApiResponseDTO
     */
    @Transactional(readOnly = true)
    public List<ExternalApiResponseDTO> getAllExternalApis() {
        List<ExternalApi> apis = externalApiRepository.findAllActiveExternalApis();
        return apis.stream()
                .map(this::mapToExternalApiResponseDTO)
                .toList();
    }

    /**
     * Register a new external API
     * @param apiCode The API code
     * @param apiName The API name
     * @param description The API description
     * @param isActive The API active status
     * @param isExternal The API external status
     * @return ExternalApiResponseDTO
     */
    public ExternalApiResponseDTO registerExternalApi(String apiCode, String apiName, String description, boolean isActive, boolean isExternal) {
        // Check if API already exists
        if (externalApiRepository.existsByApiCode(apiCode)) {
            throw new RuntimeException("API with code '" + apiCode + "' already exists");
        }

        ExternalApi api = new ExternalApi();
        api.setApiCode(apiCode);
        api.setApiName(apiName);
        api.setDescription(description);
        api.setIsActive(isActive);
        api.setIsExternal(isExternal);
        api.setCreatedBy("SYSTEM");
        api.setCreatedDate(LocalDateTime.now());

        ExternalApi savedApi = externalApiRepository.save(api);
        return mapToExternalApiResponseDTO(savedApi);
    }

    /**
     * Update an existing external API
     * @param apiId The API ID
     * @param apiName The API name
     * @param description The API description
     * @param isActive The API active status
     * @param isExternal The API external status
     * @return ExternalApiResponseDTO
     */
    public ExternalApiResponseDTO updateExternalApi(Long apiId, String apiName, String description, boolean isActive, boolean isExternal) {
        ExternalApi api = externalApiRepository.findById(apiId)
                .orElseThrow(() -> new RuntimeException("External API not found with ID: " + apiId));

        api.setApiName(apiName);
        api.setDescription(description);
        api.setIsActive(isActive);
        api.setIsExternal(isExternal);
        api.setUpdatedBy("SYSTEM");
        api.setUpdatedDate(LocalDateTime.now());

        ExternalApi updatedApi = externalApiRepository.save(api);
        return mapToExternalApiResponseDTO(updatedApi);
    }

    /**
     * Delete an external API
     * @param apiId The API ID
     */
    public void deleteExternalApi(Long apiId) {
        ExternalApi api = externalApiRepository.findById(apiId)
                .orElseThrow(() -> new RuntimeException("External API not found with ID: " + apiId));

        // Check if API is being used in any exclusions
        boolean hasExclusions = agentApiExclusionRepository.existsByExternalApiId(apiId);
        if (hasExclusions) {
            throw new RuntimeException("Cannot delete API as it is referenced in agent exclusions. Please remove all exclusions first.");
        }

        externalApiRepository.delete(api);
    }

    /**
     * Get an external API by ID
     * @param apiId The API ID
     * @return ExternalApiResponseDTO
     */
    public ExternalApiResponseDTO getExternalApiById(Long apiId) {
        ExternalApi api = externalApiRepository.findById(apiId)
                .orElseThrow(() -> new RuntimeException("External API not found with ID: " + apiId));
        return mapToExternalApiResponseDTO(api);
    }

    /**
     * Get an external API by code
     * @param apiCode The API code
     * @return ExternalApiResponseDTO
     */
    public ExternalApiResponseDTO getExternalApiByCode(String apiCode) {
        ExternalApi api = externalApiRepository.findByApiCode(apiCode)
                .orElseThrow(() -> new RuntimeException("External API not found with code: " + apiCode));
        return mapToExternalApiResponseDTO(api);
    }

    /**
     * Map AgentApiExclusion entity to ResponseDTO
     * @param exclusion AgentApiExclusion entity
     * @return AgentApiExclusionResponseDTO
     */
    private AgentApiExclusionResponseDTO mapToResponseDTO(AgentApiExclusion exclusion) {
        String agentName = exclusion.getAgent().getCompanyName() != null ? 
                          exclusion.getAgent().getCompanyName() : 
                          exclusion.getAgent().getFirstName() + " " + exclusion.getAgent().getLastName();
        
        return new AgentApiExclusionResponseDTO(
                exclusion.getId(),
                exclusion.getAgent().getId(),
                agentName,
                exclusion.getExternalApi().getApiCode(),
                exclusion.getExternalApi().getApiName(),
                exclusion.getIsExcluded(),
                exclusion.getExclusionReason()
        );
    }

    /**
     * Map ExternalApi entity to ResponseDTO
     * @param externalApi ExternalApi entity
     * @return ExternalApiResponseDTO
     */
    private ExternalApiResponseDTO mapToExternalApiResponseDTO(ExternalApi externalApi) {
        return new ExternalApiResponseDTO(
                externalApi.getId(),
                externalApi.getApiCode(),
                externalApi.getApiName(),
                externalApi.getDescription(),
                externalApi.getIsActive(),
                externalApi.getIsExternal()
        );
    }
}
