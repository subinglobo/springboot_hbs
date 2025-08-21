package com.choosenfly.hotelbookingsystem.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchResponse;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelSearchService;

@RestController
@RequestMapping("/hotel-search")
public class HotelSearchController {

	@Autowired
	private HotelSearchService hotelSearchService;

	@PostMapping("/search")
	public ResponseEntity<Map<String, Object>> search(@RequestBody HotelSearchRequest searchRequest) {
		String searchId = hotelSearchService.initiateSearch(searchRequest);
		Map<String, Object> response = new HashMap<>();
		response.put("searchId", searchId);
		response.put("status", "search_in_progress");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/results/{searchId}")
	public ResponseEntity<SearchResponse> getResults(@PathVariable String searchId, @RequestParam Long agentId) {
	    try {
	        List<HotelSearchResult> results = hotelSearchService.getResults(searchId);

	        Map<String, String> statusMap = hotelSearchService.getSearchStatusMap(searchId, agentId);
	        boolean isComplete = hotelSearchService.isSearchComplete(searchId, agentId);

	        SearchResponse response = new SearchResponse();
	        response.setResult(results);
	        response.setStatus(statusMap);
	        response.setFinalStatus(isComplete ? "COMPLETED" : "IN_PROGRESS");

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

  
}
