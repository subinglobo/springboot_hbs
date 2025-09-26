package com.choosenfly.hotelbookingsystem.masters.controller.cityMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.CityMappingSearchDTO;
import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.CityMappingServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.iwtx.IwtxCityMappingServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.x3.X3CityMappingServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cityMapping")
public class CityMappingController {
	
	private final CityMappingServiceInterface cityMappingServiceInterface;
	private final IwtxCityMappingServiceInterface iwtxCityMappingServiceInterface;
	private final X3CityMappingServiceInterface x3CityMappingServiceInterface;
	
	@Autowired
	public CityMappingController(CityMappingServiceInterface cityMappingServiceInterface,
			IwtxCityMappingServiceInterface iwtxCityMappingServiceInterface,
			X3CityMappingServiceInterface x3CityMappingServiceInterface) {
		this.cityMappingServiceInterface = cityMappingServiceInterface;
		this.iwtxCityMappingServiceInterface = iwtxCityMappingServiceInterface;
		this.x3CityMappingServiceInterface = x3CityMappingServiceInterface;
	}
	
	@PostMapping("/search")
	public Boolean cityMappingSearchData(@Valid @RequestBody CityMappingSearchDTO dto) {
		return cityMappingServiceInterface.cityMappingSearchData(dto);
	}
	
	@PostMapping("/save")
	public Long saveCityMapping(@Valid @RequestBody ApiCityMappingDTO dto) {
		
		// Route to appropriate service based on provider from DTO
		String apiProvider = dto.getApiProvider();
		if (apiProvider == null || apiProvider.trim().isEmpty()) {
			throw new IllegalArgumentException("API provider is required in the request body");
		}
		
		String lowerCaseApiProvider = apiProvider.toLowerCase();
		dto.setApiProvider(lowerCaseApiProvider);
		
		switch (lowerCaseApiProvider) {
			case "iwtx":
				return iwtxCityMappingServiceInterface.saveIwtxCityMapping(dto);
			case "x3":
				return x3CityMappingServiceInterface.saveX3CityMapping(dto);
			case "darina":
				// TODO: Add Darina service implementation when available
				throw new UnsupportedOperationException("Darina provider not yet implemented");
			default:
				throw new IllegalArgumentException("Unsupported API provider: " + apiProvider);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<ApiCityMappingDTO>> getAllCityMappingList(
			@RequestParam String apiProvider,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(required = false, name = "search") String search) {  

		Pageable pageable = PageRequest.of(page, limit);
		Page<ApiCityMappingDTO> cityMappingPage;
		
		// Route to appropriate service based on provider
		switch (apiProvider.toLowerCase()) {
			case "iwtx":
				cityMappingPage = iwtxCityMappingServiceInterface.getAllIwtxCityMappingList(pageable, search);
				break;
			case "x3":
				cityMappingPage = x3CityMappingServiceInterface.getAllX3CityMappingList(pageable, search);
				break;
			case "darina":
				// TODO: Add Darina service implementation when available
				throw new UnsupportedOperationException("Darina provider not yet implemented");
			default:
				throw new IllegalArgumentException("Unsupported API provider: " + apiProvider);
		}
		
		List<ApiCityMappingDTO> cityMappingListDTO = Optional.ofNullable(cityMappingPage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(cityMappingListDTO, HttpStatus.OK);
	}
}
