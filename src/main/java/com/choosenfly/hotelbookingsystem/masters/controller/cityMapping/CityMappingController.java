package com.choosenfly.hotelbookingsystem.masters.controller.cityMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.CityMappingSearchDTO;
import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.CityMappingServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cityMapping")
public class CityMappingController {
	
	private final CityMappingServiceInterface cityMappingServiceInterface;
	
	@Autowired
	public CityMappingController(CityMappingServiceInterface cityMappingServiceInterface ) {
		this.cityMappingServiceInterface = cityMappingServiceInterface;
		
	
	}
	
	@PostMapping("/search")
	private Boolean cityMappingSearchData(@Valid @RequestBody CityMappingSearchDTO dto){
		return cityMappingServiceInterface.cityMappingSearchData(dto);
	}
	
//	@GetMapping("/{id}")
//	private ApiCityMappingDTO getIwtxCityMappingById(@PathVariable("id") Long id) {
//		
//		return iwtxCityMappingServiceInterface.getIwtxCityMappingById(id);
//	}
	
	

	
	
}
