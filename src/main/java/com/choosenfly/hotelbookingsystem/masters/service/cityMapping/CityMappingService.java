package com.choosenfly.hotelbookingsystem.masters.service.cityMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.CityMappingSearchDTO;
import com.choosenfly.hotelbookingsystem.masters.repository.CityMappingRepository;

@Service
public class CityMappingService implements CityMappingServiceInterface{
	
	@Autowired
	private CityMappingRepository cityMappingRepository;

	@Override
	@Transactional
	public Boolean cityMappingSearchData(CityMappingSearchDTO dto) {
		// TODO Auto-generated method stub
		
		String apiProvider = dto.getApiProvider();
		String apiCountryId = dto.getApiCountryId();
		String apiCityId = dto.getApiCityId();
		
		
		try {
			ApiCityMappingDTO status = cityMappingRepository.checkDataExistinDb(apiProvider , apiCountryId , apiCityId );
			if(status != null) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
