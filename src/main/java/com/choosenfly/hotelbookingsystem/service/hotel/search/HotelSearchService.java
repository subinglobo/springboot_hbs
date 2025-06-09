package com.choosenfly.hotelbookingsystem.service.hotel.search;

import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.SearchMessage;
import com.choosenfly.hotelbookingsystem.repository.hotel.ResultRepository;

@Service
public class HotelSearchService {

	    @Autowired
	    private RabbitTemplate rabbitTemplate;

	    @Autowired
	    private HotelApiCallerContext callerContext;

	    @Autowired
	    private ResultRepository resultRepository;

	    public String initiateSearch(HotelSearchRequest request) {
	        String searchId = UUID.randomUUID().toString();
	        List<HotelSearchApiCaller> enabledCallers = callerContext.getCallersForAgent(request.getAgentId());

	        for (HotelSearchApiCaller caller : enabledCallers) {
	            SearchMessage message = new SearchMessage(searchId, request, caller.getApiKey());
	            String routingKey = "hotel-search-" + caller.getApiKey();
	            rabbitTemplate.convertAndSend("hotel-search-exchange", routingKey, message);
	        }
	        return searchId;
	    }

	    public List<HotelSearchResult> getResults(String searchId) {
	        return resultRepository.getResults(searchId);
	    }
}
