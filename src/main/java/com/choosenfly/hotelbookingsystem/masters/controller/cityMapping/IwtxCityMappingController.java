package com.choosenfly.hotelbookingsystem.masters.controller.cityMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.iwtx.IwtxCityMappingServiceInterface;

@RestController
@RequestMapping("/api/iwtxCityMapping")
public class IwtxCityMappingController {
	
	private final IwtxCityMappingServiceInterface iwtxCityMappingServiceInterface;
	
	@Autowired
	public IwtxCityMappingController(IwtxCityMappingServiceInterface iwtxCityMappingServiceInterface ) {
		this.iwtxCityMappingServiceInterface = iwtxCityMappingServiceInterface;
	}

	
	
}
