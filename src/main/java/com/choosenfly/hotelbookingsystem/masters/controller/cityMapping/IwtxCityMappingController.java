package com.choosenfly.hotelbookingsystem.masters.controller.cityMapping;

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

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterPlaceDTO;
import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.iwtx.IwtxCityMappingServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/iwtxCityMapping")
public class IwtxCityMappingController {
	
	private final IwtxCityMappingServiceInterface iwtxCityMappingServiceInterface;
	
	@Autowired
	public IwtxCityMappingController(IwtxCityMappingServiceInterface iwtxCityMappingServiceInterface ) {
		this.iwtxCityMappingServiceInterface = iwtxCityMappingServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveIwtxCityMapping(@Valid @RequestBody ApiCityMappingDTO dto){
		return iwtxCityMappingServiceInterface.saveIwtxCityMapping(dto);
	}
	
	@GetMapping("/{id}")
	private ApiCityMappingDTO getIwtxCityMappingById(@PathVariable("id") Long id) {
		
		return iwtxCityMappingServiceInterface.getIwtxCityMappingById(id);
	}
	
	@PutMapping("/{id}")
	private ApiCityMappingDTO editIwtxCityMapping(@PathVariable("id") Long id , @Valid @RequestBody ApiCityMappingDTO placeDTO) {
		return iwtxCityMappingServiceInterface.editIwtxCityMapping(id , placeDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteIwtxCityMapping(@PathVariable("id") Long id) {
		return iwtxCityMappingServiceInterface.deleteIwtxCityMapping(id);
	}
	
	@GetMapping
	public ResponseEntity<List<ApiCityMappingDTO>> getAllIwtxCityMappingList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(required = false, name = "search") String search) {  

		Pageable pageable = PageRequest.of(page, limit);
		Page<ApiCityMappingDTO> iwtxCityMappingPage = iwtxCityMappingServiceInterface.getAllIwtxCityMappingList(pageable, search);
		List<ApiCityMappingDTO> iwtxCityMappingListDTO = Optional.ofNullable(iwtxCityMappingPage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(iwtxCityMappingListDTO, HttpStatus.OK);
	}

	
	
}
