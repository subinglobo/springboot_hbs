package com.choosenfly.hotelbookingsystem.agent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentApiExclusionRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentApiExclusionResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.ExternalApiRegistrationRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.ExternalApiResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.ExternalApiUpdateRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.service.AgentApiExclusionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agent-api-exclusion")
public class AgentApiExclusionController {

    @Autowired
    private AgentApiExclusionService agentApiExclusionService;

    /**
     * Test endpoint to verify controller is working
     * @return Simple test message
     */
    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> testEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Agent API Exclusion Controller is working");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    /**
     * Get all API exclusions for a specific agent
     * @param agentId The agent ID
     * @return ResponseEntity with List of AgentApiExclusionResponseDTO
     */
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<AgentApiExclusionResponseDTO>> getApiExclusionsByAgentId(@PathVariable Long agentId) {
        List<AgentApiExclusionResponseDTO> exclusions = agentApiExclusionService.getApiExclusionsByAgentId(agentId);
        return new ResponseEntity<>(exclusions, HttpStatus.OK);
    }

    /**
     * Get excluded API codes for a specific agent
     * @param agentId The agent ID
     * @return ResponseEntity with List of excluded API codes
     */
    @GetMapping("/agent/{agentId}/excluded-apis")
    public ResponseEntity<List<String>> getExcludedApiCodesForAgent(@PathVariable Long agentId) {
        List<String> excludedApiCodes = agentApiExclusionService.getExcludedApiCodesForAgent(agentId);
        return new ResponseEntity<>(excludedApiCodes, HttpStatus.OK);
    }

    /**
     * Add or update API exclusion for an agent
     * @param request AgentApiExclusionRequestDTO
     * @return ResponseEntity with AgentApiExclusionResponseDTO
     */
    @PostMapping("/exclude")
    public ResponseEntity<AgentApiExclusionResponseDTO> addOrUpdateApiExclusion(
            @Valid @RequestBody AgentApiExclusionRequestDTO request) {
        AgentApiExclusionResponseDTO response = agentApiExclusionService.addOrUpdateApiExclusion(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update API exclusion for an agent
     * @param request AgentApiExclusionRequestDTO
     * @return ResponseEntity with AgentApiExclusionResponseDTO
     */
    @PutMapping("/update")
    public ResponseEntity<AgentApiExclusionResponseDTO> updateApiExclusion(
            @Valid @RequestBody AgentApiExclusionRequestDTO request) {
        AgentApiExclusionResponseDTO response = agentApiExclusionService.addOrUpdateApiExclusion(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Remove API exclusion for an agent
     * @param agentId The agent ID
     * @param apiCode The API code
     * @return ResponseEntity with success message
     */
    @DeleteMapping("/agent/{agentId}/api/{apiCode}")
    public ResponseEntity<String> removeApiExclusion(@PathVariable Long agentId, @PathVariable String apiCode) {
        agentApiExclusionService.removeApiExclusion(agentId, apiCode);
        return new ResponseEntity<>("API exclusion removed successfully", HttpStatus.OK);
    }

    /**
     * Check if API is excluded for an agent
     * @param agentId The agent ID
     * @param apiCode The API code
     * @return ResponseEntity with boolean result
     */
    @GetMapping("/check-exclusion")
    public ResponseEntity<Boolean> isApiExcludedForAgent(
            @RequestParam Long agentId, 
            @RequestParam String apiCode) {
        boolean isExcluded = agentApiExclusionService.isApiExcludedForAgent(agentId, apiCode);
        return new ResponseEntity<>(isExcluded, HttpStatus.OK);
    }

    /**
     * Get all available external APIs
     * @return ResponseEntity with List of ExternalApiResponseDTO
     */
    @GetMapping("/external-apis")
    public ResponseEntity<List<ExternalApiResponseDTO>> getAllExternalApis() {
        List<ExternalApiResponseDTO> apis = agentApiExclusionService.getAllExternalApis();
        return new ResponseEntity<>(apis, HttpStatus.OK);
    }

    /**
     * Register a new external API
     * @param request ExternalApiRegistrationRequestDTO
     * @return ExternalApiResponseDTO
     */
    @PostMapping("/external-apis/register")
    public ResponseEntity<ExternalApiResponseDTO> registerExternalApi(
            @Valid @RequestBody ExternalApiRegistrationRequestDTO request) {
        ExternalApiResponseDTO response = agentApiExclusionService.registerExternalApi(
                request.getApiCode(), 
                request.getApiName(), 
                request.getDescription(),
                request.getIsActive(),
                request.getIsExternal());
        return ResponseEntity.ok(response);
    }

    /**
     * Update an existing external API
     * @param request ExternalApiUpdateRequestDTO
     * @return ExternalApiResponseDTO
     */
    @PutMapping("/external-apis/update")
    public ResponseEntity<ExternalApiResponseDTO> updateExternalApi(
            @Valid @RequestBody ExternalApiUpdateRequestDTO request) {
        ExternalApiResponseDTO response = agentApiExclusionService.updateExternalApi(
                request.getId(),
                request.getApiName(),
                request.getDescription(),
                request.getIsActive(),
                request.getIsExternal());
        return ResponseEntity.ok(response);
    }

    /**
     * Delete an external API
     * @param apiId API ID
     * @return Success message
     */
    @DeleteMapping("/external-apis/{apiId}")
    public ResponseEntity<Map<String, String>> deleteExternalApi(@PathVariable Long apiId) {
        agentApiExclusionService.deleteExternalApi(apiId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "External API deleted successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * Get external API by ID
     * @param apiId API ID
     * @return ExternalApiResponseDTO
     */
    @GetMapping("/external-apis/{apiId}")
    public ResponseEntity<ExternalApiResponseDTO> getExternalApiById(@PathVariable Long apiId) {
        ExternalApiResponseDTO response = agentApiExclusionService.getExternalApiById(apiId);
        return ResponseEntity.ok(response);
    }

    /**
     * Get external API by code
     * @param apiCode API code
     * @return ExternalApiResponseDTO
     */
    @GetMapping("/external-apis/code/{apiCode}")
    public ResponseEntity<ExternalApiResponseDTO> getExternalApiByCode(@PathVariable String apiCode) {
        ExternalApiResponseDTO response = agentApiExclusionService.getExternalApiByCode(apiCode);
        return ResponseEntity.ok(response);
    }
}
