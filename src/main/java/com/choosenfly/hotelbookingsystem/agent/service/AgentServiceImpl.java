package com.choosenfly.hotelbookingsystem.agent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentGSTDetailsDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;
import com.choosenfly.hotelbookingsystem.agent.dto.AgentResponseDTO;
import com.choosenfly.hotelbookingsystem.agent.entity.Agent;
import com.choosenfly.hotelbookingsystem.agent.entity.AgentCategory;
import com.choosenfly.hotelbookingsystem.agent.entity.AgentGSTDetails;
import com.choosenfly.hotelbookingsystem.agent.exception.AgentRegistrationException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidAgentCategoryException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidContactDetailsException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidCountryException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidGSTDetailsException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidPlaceException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidProvinceException;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterPlaceRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;

import jakarta.validation.Valid;

@Service
public class AgentServiceImpl implements AgentService {

	private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);

	private final AgentCategoryRepository agentCategoryRepository;
	private final MasterCountryRepository countryRepository;
	private final MasterStateRepository stateRepository;
	private final MasterPlaceRepository placeRepository;
	private final AgentRepository agentRepository;

	@Autowired
	public AgentServiceImpl(AgentCategoryRepository agentCategoryRepository, MasterCountryRepository countryRepository,
			MasterStateRepository stateRepository, MasterPlaceRepository placeRepository,
			AgentRepository agentRepository) {
		this.agentCategoryRepository = agentCategoryRepository;
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.placeRepository = placeRepository;
		this.agentRepository = agentRepository;
	}

	@Override
	@Transactional
	public AgentResponseDTO registerAgent(AgentRegistrationRequestDTO request) {
		if (request == null) {
			throw new AgentRegistrationException("Agent registration request cannot be null");
		}

		// Check if agent with the same email already exists
		if (request.getPersonalEmail() != null && !request.getPersonalEmail().trim().isEmpty()) {
			if (agentRepository.existsByPersonalEmail(request.getPersonalEmail())) {
				throw new AgentRegistrationException(
						"An agent with email " + request.getPersonalEmail() + " already exists");
			}
		} else {
			throw new InvalidContactDetailsException("Personal email cannot be null or empty");
		}

		Agent agent = new Agent();

		// Validate and set company name
		if (request.getCompanyName() == null || request.getCompanyName().trim().isEmpty()) {
			throw new AgentRegistrationException("Company name cannot be null or empty");
		}
		agent.setCompanyName(request.getCompanyName());

		agent.setFirstName(request.getFirstName());
		agent.setLastName(request.getLastName());
		agent.setBusinessType(request.getBusinessType());

		// Set agent category
		AgentCategory category = agentCategoryRepository.findById(request.getAgentCategoryId())
				.orElseThrow(() -> new InvalidAgentCategoryException(
						"Invalid or deleted agent category ID: " + request.getAgentCategoryId()));
		agent.setAgentCategoryId(category);

		// Validate and set location entities
		MasterCountry country = countryRepository.findById(request.getCountryId()).orElseThrow(
				() -> new InvalidCountryException("Invalid or deleted country ID: " + request.getCountryId()));
		agent.setCountry(country);

		MasterState state = stateRepository.findById(request.getProvinceId()).orElseThrow(
				() -> new InvalidProvinceException("Invalid or deleted province ID: " + request.getProvinceId()));
		if (!state.getCountry().getId().equals(request.getCountryId())) {
			throw new InvalidProvinceException("Province ID " + request.getProvinceId()
					+ " does not belong to country ID " + request.getCountryId());
		}
		agent.setProvince(state);

		MasterPlace place = placeRepository.findById(request.getPlaceId())
				.orElseThrow(() -> new InvalidPlaceException("Invalid or deleted place ID: " + request.getPlaceId()));
		if (!place.getState().getId().equals(request.getProvinceId())) {
			throw new InvalidPlaceException(
					"Place ID " + request.getPlaceId() + " does not belong to province ID " + request.getProvinceId());
		}
		if (!place.getCountry().getId().equals(request.getCountryId())) {
			throw new InvalidPlaceException("Place ID " + request.getPlaceId()
					+ " is associated with a country that does not match country ID " + request.getCountryId());
		}
		agent.setPlace(place);

		// Set basic personal/contact details
		agent.setPersonalEmail(request.getPersonalEmail());
		if (request.getMobileNumber() == null || request.getMobileNumber().trim().isEmpty()) {
			throw new InvalidContactDetailsException("Mobile number cannot be null or empty");
		}
		agent.setMobileNumber(request.getMobileNumber());
		agent.setAddress(request.getAddress());

		if (request.getShortName() != null) {
			agent.setShortName(request.getShortName());
		}

		if (request.getCompanyCode() != null) {
			agent.setCompanyCode(request.getCompanyCode());
		}

		if (request.getAgentUrl() != null) {
			agent.setAgentUrl(request.getAgentUrl());
		}

		if (request.getZipCode() != null) {
			agent.setZipCode(request.getZipCode());
		}

		if (request.getContactPerson() != null) {
			agent.setContactPerson(request.getContactPerson());
		}

		if (request.getMarkup() != null) {
			agent.setMarkup(request.getMarkup());
		}

		if (request.getCurrencyId() != 0) {
			agent.setCurrencyId(request.getCurrencyId());
		}

		if (request.getStatus() != null) {
			agent.setStatus(request.getStatus());
		}

		// Set GST details
		if (request.getAgentGSTDetailsDTO() != null) {
			AgentGSTDetails gst = new AgentGSTDetails();
			if (request.getAgentGSTDetailsDTO().getAgentClassification() == null) {
				throw new InvalidGSTDetailsException("Agent classification cannot be null when GST is provided");
			}
			gst.setAgentClassification(request.getAgentGSTDetailsDTO().getAgentClassification());
			gst.setAgentGstIn(request.getAgentGSTDetailsDTO().getAgentGstIn());
			gst.setAgentProvisionalGstno(request.getAgentGSTDetailsDTO().getAgentProvisionalGstno());
			gst.setAgentCorrespondmail(request.getAgentGSTDetailsDTO().getAgentCorrespondmail());
			gst.setAgentRegisterstatus(request.getAgentGSTDetailsDTO().getAgentRegisterstatus());
			gst.setAgentHsncode(request.getAgentGSTDetailsDTO().getAgentHsncode());
			gst.setAgentStatus(request.getAgentGSTDetailsDTO().getAgentStatus());
			agent.setGstDetails(gst);
		}

		try {
			Agent savedAgent = agentRepository.save(agent);
			logger.info("Agent registered successfully with email: {}", savedAgent.getPersonalEmail());
			return new AgentResponseDTO(savedAgent.getId(), savedAgent.getPersonalEmail());
		} catch (Exception e) {
			logger.error("Failed to register agent: {}", e.getMessage(), e);
			throw new AgentRegistrationException("Failed to save agent: " + e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AgentRegistrationRequestDTO getAgentRegistrationDetailsById(Long id) {
		Agent agent = agentRepository.findById(id)
				.orElseThrow(() -> new AgentRegistrationException("Agent not found with ID: " + id));

		AgentRegistrationRequestDTO dto = new AgentRegistrationRequestDTO();
		dto.setId(agent.getId());
		dto.setCompanyName(agent.getCompanyName());
		dto.setFirstName(agent.getFirstName());
		dto.setLastName(agent.getLastName());
		dto.setBusinessType(agent.getBusinessType());
		dto.setAgentCategoryId(agent.getAgentCategoryId().getAgentCategoryId());
		dto.setCountryId(agent.getCountry().getId());
		dto.setProvinceId(agent.getProvince().getId());
		dto.setPlaceId(agent.getPlace().getId());
		dto.setPersonalEmail(agent.getPersonalEmail());
		dto.setMobileNumber(agent.getMobileNumber());
		dto.setAddress(agent.getAddress());
		
		if (agent.getShortName() != null) {
			dto.setShortName(agent.getShortName());
		}

		if (agent.getCompanyCode() != null) {
			dto.setCompanyCode(agent.getCompanyCode());
		}

		if (agent.getAgentUrl() != null) {
			dto.setAgentUrl(agent.getAgentUrl());
		}

		if (agent.getZipCode() != null) {
			dto.setZipCode(agent.getZipCode());
		}

		if (agent.getContactPerson() != null) {
			dto.setContactPerson(agent.getContactPerson());
		}

		if (agent.getMarkup() != null) {
			dto.setMarkup(agent.getMarkup());
		}

		if (agent.getCurrencyId() != 0) {
			dto.setCurrencyId(agent.getCurrencyId());
		}

		if (agent.getStatus() != null) {
			dto.setStatus(agent.getStatus());
		}

		if (agent.getGstDetails() != null) {
			AgentGSTDetailsDTO gstDto = new AgentGSTDetailsDTO();
			gstDto.setAgentClassification(agent.getGstDetails().getAgentClassification());
			gstDto.setAgentGstIn(agent.getGstDetails().getAgentGstIn());
			gstDto.setAgentProvisionalGstno(agent.getGstDetails().getAgentProvisionalGstno());
			gstDto.setAgentCorrespondmail(agent.getGstDetails().getAgentCorrespondmail());
			gstDto.setAgentRegisterstatus(agent.getGstDetails().getAgentRegisterstatus());
			gstDto.setAgentHsncode(agent.getGstDetails().getAgentHsncode());
			gstDto.setAgentStatus(agent.getGstDetails().getAgentStatus());
			dto.setAgentGSTDetailsDTO(gstDto);
		}

		return dto;
	}

	@Override
	@Transactional
	public AgentRegistrationRequestDTO editAgentRegistrationDetails(Long id,
			@Valid AgentRegistrationRequestDTO reqDTO) {
		Agent agent = agentRepository.findById(id)
				.orElseThrow(() -> new AgentRegistrationException("Agent not found with ID: " + id));

		// Check for existing email conflict (excluding the current agent)
		if (reqDTO.getPersonalEmail() != null && !reqDTO.getPersonalEmail().trim().isEmpty()
				&& !reqDTO.getPersonalEmail().equals(agent.getPersonalEmail())) {
			if (agentRepository.existsByPersonalEmail(reqDTO.getPersonalEmail())) {
				throw new AgentRegistrationException(
						"An agent with email " + reqDTO.getPersonalEmail() + " already exists");
			}
		}

		// Validate and update company name
		if (reqDTO.getCompanyName() == null || reqDTO.getCompanyName().trim().isEmpty()) {
			throw new AgentRegistrationException("Company name cannot be null or empty");
		}
		agent.setCompanyName(reqDTO.getCompanyName());
		agent.setFirstName(reqDTO.getFirstName());
		agent.setLastName(reqDTO.getLastName());
		agent.setBusinessType(reqDTO.getBusinessType());

		// Update agent category
		AgentCategory category = agentCategoryRepository.findById(reqDTO.getAgentCategoryId())
				.orElseThrow(() -> new InvalidAgentCategoryException(
						"Invalid or deleted agent category ID: " + reqDTO.getAgentCategoryId()));
		agent.setAgentCategoryId(category);

		// Update location entities
		MasterCountry country = countryRepository.findById(reqDTO.getCountryId()).orElseThrow(
				() -> new InvalidCountryException("Invalid or deleted country ID: " + reqDTO.getCountryId()));
		agent.setCountry(country);

		MasterState state = stateRepository.findById(reqDTO.getProvinceId()).orElseThrow(
				() -> new InvalidProvinceException("Invalid or deleted province ID: " + reqDTO.getProvinceId()));
		if (!state.getCountry().getId().equals(reqDTO.getCountryId())) {
			throw new InvalidProvinceException("Province ID " + reqDTO.getProvinceId()
					+ " does not belong to country ID " + reqDTO.getCountryId());
		}
		agent.setProvince(state);

		MasterPlace place = placeRepository.findById(reqDTO.getPlaceId())
				.orElseThrow(() -> new InvalidPlaceException("Invalid or deleted place ID: " + reqDTO.getPlaceId()));
		if (!place.getState().getId().equals(reqDTO.getProvinceId())) {
			throw new InvalidPlaceException(
					"Place ID " + reqDTO.getPlaceId() + " does not belong to province ID " + reqDTO.getProvinceId());
		}
		if (!place.getCountry().getId().equals(reqDTO.getCountryId())) {
			throw new InvalidPlaceException("Place ID " + reqDTO.getPlaceId()
					+ " is associated with a country that does not match country ID " + reqDTO.getCountryId());
		}
		agent.setPlace(place);

		// Update contact details
		agent.setPersonalEmail(reqDTO.getPersonalEmail());
		if (reqDTO.getMobileNumber() == null || reqDTO.getMobileNumber().trim().isEmpty()) {
			throw new InvalidContactDetailsException("Mobile number cannot be null or empty");
		}
		agent.setMobileNumber(reqDTO.getMobileNumber());
		agent.setAddress(reqDTO.getAddress());
		
		if (reqDTO.getShortName() != null) {
			agent.setShortName(reqDTO.getShortName());
		}

		if (reqDTO.getCompanyCode() != null) {
			agent.setCompanyCode(reqDTO.getCompanyCode());
		}

		if (reqDTO.getAgentUrl() != null) {
			agent.setAgentUrl(reqDTO.getAgentUrl());
		}

		if (reqDTO.getZipCode() != null) {
			agent.setZipCode(reqDTO.getZipCode());
		}

		if (reqDTO.getContactPerson() != null) {
			agent.setContactPerson(reqDTO.getContactPerson());
		}

		if (reqDTO.getMarkup() != null) {
			agent.setMarkup(reqDTO.getMarkup());
		}

		if (reqDTO.getCurrencyId() != 0) {
			agent.setCurrencyId(reqDTO.getCurrencyId());
		}

		if (reqDTO.getStatus() != null) {
			agent.setStatus(reqDTO.getStatus());
		}

		// Update GST details
		if (reqDTO.getAgentGSTDetailsDTO() != null) {
			AgentGSTDetails gst = agent.getGstDetails() != null ? agent.getGstDetails() : new AgentGSTDetails();
			if (reqDTO.getAgentGSTDetailsDTO().getAgentClassification() == null) {
				throw new InvalidGSTDetailsException("Agent classification cannot be null when GST is provided");
			}
			gst.setAgentClassification(reqDTO.getAgentGSTDetailsDTO().getAgentClassification());
			gst.setAgentGstIn(reqDTO.getAgentGSTDetailsDTO().getAgentGstIn());
			gst.setAgentProvisionalGstno(reqDTO.getAgentGSTDetailsDTO().getAgentProvisionalGstno());
			gst.setAgentCorrespondmail(reqDTO.getAgentGSTDetailsDTO().getAgentCorrespondmail());
			gst.setAgentRegisterstatus(reqDTO.getAgentGSTDetailsDTO().getAgentRegisterstatus());
			gst.setAgentHsncode(reqDTO.getAgentGSTDetailsDTO().getAgentHsncode());
			gst.setAgentStatus(reqDTO.getAgentGSTDetailsDTO().getAgentStatus());
			agent.setGstDetails(gst);
		} else if (agent.getGstDetails() != null) {
			agent.setGstDetails(null); // Remove GST details if not provided
		}

		try {
			Agent updatedAgent = agentRepository.save(agent);
			logger.info("Agent updated successfully with email: {}", updatedAgent.getPersonalEmail());
			return reqDTO; // Return the updated DTO
		} catch (Exception e) {
			logger.error("Failed to update agent: {}", e.getMessage(), e);
			throw new AgentRegistrationException("Failed to update agent: " + e.getMessage());
		}
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteAgentRegistrationDetails(Long id) {
		if (!agentRepository.existsById(id)) {
			throw new AgentRegistrationException("Agent not found with ID: " + id);
		}

		try {
			agentRepository.deleteById(id);
			logger.info("Agent deleted successfully with ID: {}", id);
			return ResponseEntity.ok("Agent deleted successfully");
		} catch (Exception e) {
			logger.error("Failed to delete agent with ID {}: {}", id, e.getMessage(), e);
			throw new AgentRegistrationException("Failed to delete agent: " + e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AgentRegistrationRequestDTO> getAllAgentRegistrationDetails(Pageable pageable, String searchTerm) {
		Page<Agent> agentPage;
		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			agentPage = agentRepository.findByCompanyNameContainingIgnoreCaseOrPersonalEmailContainingIgnoreCase(
					searchTerm.trim(), searchTerm.trim(), pageable);
		} else {
			agentPage = agentRepository.findAll(pageable);
		}

		return agentPage.map(agent -> {
			AgentRegistrationRequestDTO dto = new AgentRegistrationRequestDTO();
			dto.setId(agent.getId());
			dto.setCompanyName(agent.getCompanyName());
			dto.setFirstName(agent.getFirstName());
			dto.setLastName(agent.getLastName());
			dto.setBusinessType(agent.getBusinessType());
			dto.setAgentCategoryId(agent.getAgentCategoryId().getAgentCategoryId());
			dto.setCountryId(agent.getCountry().getId());
			dto.setProvinceId(agent.getProvince().getId());
			dto.setPlaceId(agent.getPlace().getId());
			dto.setPersonalEmail(agent.getPersonalEmail());
			dto.setMobileNumber(agent.getMobileNumber());
			dto.setAddress(agent.getAddress());
			
			if (agent.getShortName() != null) {
				dto.setShortName(agent.getShortName());
			}

			if (agent.getCompanyCode() != null) {
				dto.setCompanyCode(agent.getCompanyCode()); 
			}

			if (agent.getAgentUrl() != null) {
				dto.setAgentUrl(agent.getAgentUrl());
			}

			if (agent.getZipCode() != null) {
				dto.setZipCode(agent.getZipCode());
			}

			if (agent.getContactPerson() != null) {
				dto.setContactPerson(agent.getContactPerson());
			}

			if (agent.getMarkup() != null) {
				dto.setMarkup(agent.getMarkup());
			}

			if (agent.getCurrencyId() != 0) {
				dto.setCurrencyId(agent.getCurrencyId());
			}

			if (agent.getStatus() != null) {
				dto.setStatus(agent.getStatus());
			}
			
			if (agent.getGstDetails() != null) {
				AgentGSTDetailsDTO gstDto = new AgentGSTDetailsDTO();
				gstDto.setAgentClassification(agent.getGstDetails().getAgentClassification());
				gstDto.setAgentGstIn(agent.getGstDetails().getAgentGstIn());
				gstDto.setAgentProvisionalGstno(agent.getGstDetails().getAgentProvisionalGstno());
				gstDto.setAgentCorrespondmail(agent.getGstDetails().getAgentCorrespondmail());
				gstDto.setAgentRegisterstatus(agent.getGstDetails().getAgentRegisterstatus());
				gstDto.setAgentHsncode(agent.getGstDetails().getAgentHsncode());
				gstDto.setAgentStatus(agent.getGstDetails().getAgentStatus());
				dto.setAgentGSTDetailsDTO(gstDto);
			}
			return dto;
		});
	}
}