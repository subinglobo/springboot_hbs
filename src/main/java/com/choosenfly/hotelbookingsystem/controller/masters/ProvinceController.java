package com.choosenfly.hotelbookingsystem.controller.masters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.service.masters.province.ProvinceServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {
	
	private final ProvinceServiceInterface provinceServiceInterface;
	
	@Autowired
	public ProvinceController(ProvinceServiceInterface provinceServiceInterface) {
		this.provinceServiceInterface = provinceServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveProvince(@Valid @RequestBody MasterStateDTO stateDTO){
		return provinceServiceInterface.saveProvince(stateDTO);
	}
	
	@GetMapping("/{id}")
	private MasterStateDTO getProvinveById(@PathVariable("id") Long id) {
		
		return provinceServiceInterface.getProvinveById(id);
	}
	
	@PutMapping("/{id}")
	private MasterStateDTO editProvince(@PathVariable("id") Long id , @Valid @RequestBody MasterStateDTO stateDTO) {
		return provinceServiceInterface.editProvince(id , stateDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteProvince(@PathVariable("id") Long id) {
		return provinceServiceInterface.deleteProvince(id);
	}

}
