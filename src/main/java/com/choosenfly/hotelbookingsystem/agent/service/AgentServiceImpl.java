package com.choosenfly.hotelbookingsystem.agent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.agent.dto.AgentRegistrationRequestDTO;
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

@Service
public class AgentServiceImpl implements AgentService {

    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);
	
	private final AgentCategoryRepository agentCategoryRepository;
    private final MasterCountryRepository countryRepository;
    private final MasterStateRepository stateRepository;
    private final MasterPlaceRepository placeRepository;
    private final AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(
            AgentCategoryRepository agentCategoryRepository,
            MasterCountryRepository countryRepository,
            MasterStateRepository stateRepository,
            MasterPlaceRepository placeRepository,
            AgentRepository agentRepository) {
        this.agentCategoryRepository = agentCategoryRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.placeRepository = placeRepository;
        this.agentRepository = agentRepository;
    }
    
   
    @Override
    @Transactional
    public void registerAgent(AgentRegistrationRequestDTO request) {
        if (request == null) {
            throw new AgentRegistrationException("Agent registration request cannot be null");
        }

        // Check if agent with the same email already exists
        if (request.getPersonalEmail() != null && !request.getPersonalEmail().trim().isEmpty()) {
            if (agentRepository.existsByPersonalEmail(request.getPersonalEmail())) {
                throw new AgentRegistrationException("An agent with email " + request.getPersonalEmail() + " already exists");
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

        // Set business type
        agent.setBusinessType(request.getBusinessType());

        // Set agent category
        AgentCategory category = agentCategoryRepository.findById(request.getAgentCategoryId())
                .orElseThrow(() -> new InvalidAgentCategoryException("Invalid or deleted agent category ID: " + request.getAgentCategoryId()));
        agent.setAgentCategoryId(category);

        // Validate and set location entities
        MasterCountry country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new InvalidCountryException("Invalid or deleted country ID: " + request.getCountryId()));
        agent.setCountry(country);

        // Validate province belongs to the country
        MasterState state = stateRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new InvalidProvinceException("Invalid or deleted province ID: " + request.getProvinceId()));
        if (!state.getCountry().getId().equals(request.getCountryId())) {
            throw new InvalidProvinceException("Province ID " + request.getProvinceId() + " does not belong to country ID " + request.getCountryId());
        }
        agent.setProvince(state);

        // Validate place belongs to the state and country
        MasterPlace place = placeRepository.findById(request.getPlaceId())
                .orElseThrow(() -> new InvalidPlaceException("Invalid or deleted place ID: " + request.getPlaceId()));
        if (!place.getState().getId().equals(request.getProvinceId())) {
            throw new InvalidPlaceException("Place ID " + request.getPlaceId() + " does not belong to province ID " + request.getProvinceId());
        }
        if (!place.getCountry().getId().equals(request.getCountryId())) {
            throw new InvalidPlaceException("Place ID " + request.getPlaceId() + " is associated with a country that does not match country ID " + request.getCountryId());
        }
        agent.setPlace(place);

        // Set basic personal/contact details
        agent.setPersonalEmail(request.getPersonalEmail());

        if (request.getMobileNumber() == null || request.getMobileNumber().trim().isEmpty()) {
            throw new InvalidContactDetailsException("Mobile number cannot be null or empty");
        }
        agent.setMobileNumber(request.getMobileNumber());

        agent.setAddress(request.getAddress());

        // Set GST details
        if (request.getAgentGSTDetailsDTO() == null) {
            throw new InvalidGSTDetailsException("GST details cannot be null");
        }

        AgentGSTDetails gst = new AgentGSTDetails();
        if (request.getAgentGSTDetailsDTO().getAgentClassification() == null) {
            throw new InvalidGSTDetailsException("Agent classification cannot be null");
        }

        gst.setAgentClassification(request.getAgentGSTDetailsDTO().getAgentClassification());
        gst.setAgentGstIn(request.getAgentGSTDetailsDTO().getAgentGstIn());
        gst.setAgentProvisionalGstno(request.getAgentGSTDetailsDTO().getAgentProvisionalGstno());
        gst.setAgentCorrespondmail(request.getAgentGSTDetailsDTO().getAgentCorrespondmail());
        gst.setAgentRegisterstatus(request.getAgentGSTDetailsDTO().getAgentRegisterstatus());
        gst.setAgentHsncode(request.getAgentGSTDetailsDTO().getAgentHsncode());
        gst.setAgentStatus(request.getAgentGSTDetailsDTO().getAgentStatus());
        
        agent.setGstDetails(gst);

        try {
            agentRepository.save(agent);
            logger.info("Agent registered successfully with email: {}", request.getPersonalEmail());
        } catch (Exception e) {
            logger.error("Failed to register agent: {}", e.getMessage(), e);
            throw new AgentRegistrationException("Failed to save agent: " + e.getMessage());
        }
    }

}