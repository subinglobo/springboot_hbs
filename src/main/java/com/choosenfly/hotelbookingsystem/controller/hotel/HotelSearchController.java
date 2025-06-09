package com.choosenfly.hotelbookingsystem.controller.hotel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;
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
	public ResponseEntity<List<HotelSearchResult>> getResults(@PathVariable String searchId) {
		List<HotelSearchResult> results = hotelSearchService.getResults(searchId);
		return ResponseEntity.ok(results);
	}
}
