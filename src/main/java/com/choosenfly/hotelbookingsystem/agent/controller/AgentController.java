package com.choosenfly.hotelbookingsystem.agent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    @GetMapping("/{id}")
	private AgentRegistrationRequestDTO getAgentRegistrationDetailsById(@PathVariable("id") Long id) {
		
		return agentService.getAgentRegistrationDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private AgentRegistrationRequestDTO editAgentRegistrationDetails(@PathVariable("id") Long id , @Valid @RequestBody AgentRegistrationRequestDTO reqDTO) {
		return agentService.editAgentRegistrationDetails(id , reqDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteAgentRegistrationDetails(@PathVariable("id") Long id) {
		return agentService.deleteAgentRegistrationDetails(id);
	}
	
	@GetMapping
	public ResponseEntity<List<AgentRegistrationRequestDTO>> getAllAgentRegistrationDetails(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int limit,
			@RequestParam(required = false, name = "search") String searchTerm) {   

		Pageable pageable = PageRequest.of(page, limit);
		Page<AgentRegistrationRequestDTO> bankPage = agentService.getAllAgentRegistrationDetails(pageable, searchTerm);
		List<AgentRegistrationRequestDTO> bankListDTO = Optional.ofNullable(bankPage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(bankListDTO, HttpStatus.OK);
	}
    
   
}