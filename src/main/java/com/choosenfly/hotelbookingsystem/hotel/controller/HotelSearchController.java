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
	public ResponseEntity<SearchResponse> getResults(
	        @PathVariable String searchId,
	        @RequestParam Long agentId,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "baseRate") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortOrder,
	        @RequestParam(required = false) Integer starRating,
	        @RequestParam(required = false) String apiType
	) {
	    try {
	        // Get full results from Redis
	        List<HotelSearchResult> results = hotelSearchService.getResults(searchId);

	        // Apply filtering
	        results = hotelSearchService.applyFilters(results, starRating, apiType);

	        // Apply sorting
	        results = hotelSearchService.sortResults(results, sortBy, sortOrder);

	        // Paginate
	        int start = Math.min(page * size, results.size());
	        int end = Math.min(start + size, results.size());
	        List<HotelSearchResult> paginatedResults = results.subList(start, end);

	        // Build response
	        Map<String, String> statusMap = hotelSearchService.getSearchStatusMap(searchId, agentId);
	        boolean isComplete = hotelSearchService.isSearchComplete(searchId, agentId);

	        SearchResponse response = new SearchResponse();
	        response.setResult(paginatedResults);
	        response.setStatus(statusMap);
	        response.setFinalStatus(isComplete ? "COMPLETED" : "IN_PROGRESS");
	        response.setTotalResults(results.size());
	        response.setPage(page);
	        response.setSize(size);

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
  
}
