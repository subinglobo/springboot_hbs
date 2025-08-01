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

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.SearchResponse;
import com.choosenfly.hotelbookingsystem.service.hotel.search.HotelSearchService;

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

    /**
     * Checks if all API responses for a search are complete.
     * @param searchId The unique identifier for the search.
     * @param agentId The agent identifier to determine enabled APIs.
     * @return A response containing the searchId and completion status.
     */
    @GetMapping("/status/{searchId}")
    public ResponseEntity<Map<String, Object>> getSearchStatus(
            @PathVariable String searchId,
            @RequestParam Long agentId) {
        try {
            boolean isComplete = hotelSearchService.isSearchComplete(searchId, agentId);
            Map<String, Object> response = new HashMap<>();
            response.put("searchId", searchId);
            response.put("status", isComplete ? "completed" : "in_progress");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to check status: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
