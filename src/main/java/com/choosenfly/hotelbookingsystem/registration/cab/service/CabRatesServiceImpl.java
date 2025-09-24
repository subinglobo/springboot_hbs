package com.choosenfly.hotelbookingsystem.registration.cab.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabRateDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabRateDetailsDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabRateValidityDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.Cab;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabProvider;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabRateMarketType;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabRateValidity;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabRates;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.CabRatesDetails;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabProviderRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabRatesRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabRespository;

import jakarta.validation.Valid;

@Service
public class CabRatesServiceImpl implements CabRatesService{
	
	@Autowired
	private CabRatesRepository cabRatesRepository;
	
	@Autowired
	private MasterMarketTypeRepository marketTypeRepository;
	
	@Autowired
	private CabRespository cabRespository;
	
	private CabProviderRepository cabProviderRepository;
	
	public CabRatesServiceImpl(CabRatesRepository cabRatesRepository,
			MasterMarketTypeRepository marketTypeRepository,CabRespository cabRespository,
			CabProviderRepository cabProviderRepository) {
		this.cabRatesRepository = cabRatesRepository;
		this.marketTypeRepository = marketTypeRepository;
		this.cabRespository = cabRespository;
		this.cabProviderRepository=cabProviderRepository;
	}

	@Override
	public CabRateDTO registerCabRate(@Valid CabRateDTO request) {
		// TODO Auto-generated method stub
	       CabRates cabRates = new CabRates();

	        // set base cabRate fields
	        cabRates.setRateCode(request.getRateCode());
	        cabRates.setIsActive(true);

	        CabProvider cabProvider = cabProviderRepository.findById(request.getCabproviderId()).orElseThrow(()->new EntityNotFoundException("CabProvider not found with id: " + request.getCabproviderId()));
	        // find and set Cab
	        cabRates.setCabProvider(cabProvider);
	        
	        
	        Cab cab = cabRespository.findById(request.getCabId()).orElseThrow(() -> new EntityNotFoundException(
		                    "Cab not found with id: " + request.getCabId()));
	        cabRates.setCab(cab);

	        // ---------- Market Types ----------
	        List<CabRateMarketType> marketTypes = new ArrayList<>();
	        if (request.getMarketype() != null) {
	            for (String marketId : request.getMarketype()) {
	                MasterMarketType marketType = marketTypeRepository.findById(Long.valueOf(marketId))
	                        .orElseThrow(() -> new RuntimeException("MarketType not found with id: " + marketId));
	                CabRateMarketType cabRateMarketType = new CabRateMarketType();
	                cabRateMarketType.setCabRate(cabRates);
	                cabRateMarketType.setMarketType(marketType);
	                marketTypes.add(cabRateMarketType);
	            }
	        }
	        cabRates.setMarketTypes(marketTypes);

	        // ---------- Validities ----------
	        List<CabRateValidity> validities = new ArrayList<>();
	        if (request.getCabRateValidityDTOList() != null) {
	            for (CabRateValidityDTO dto : request.getCabRateValidityDTOList()) {
	                CabRateValidity validity = new CabRateValidity();
	                validity.setCabRate(cabRates);
	                try {
	                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                    validity.setValidityFrom(sdf.parse(dto.getValidityFrom()));
	                    validity.setValidityTo(sdf.parse(dto.getValidityTo()));
	                } catch (Exception e) {
	                    throw new RuntimeException("Invalid date format for validity", e);
	                }
	                validities.add(validity);
	            }
	        }
	        cabRates.setCabRateValidities(validities);

	        // ---------- Rate Details ----------
	        List<CabRatesDetails> detailsList = new ArrayList<>();
	        if (request.getCabRateDetailsDTOList() != null) {
	            for (CabRateDetailsDTO dto : request.getCabRateDetailsDTOList()) {
	                CabRatesDetails details = new CabRatesDetails();
	                details.setCabRate(cabRates);
	                details.setLocationId(dto.getLocationId());
	                details.setHourDetails(dto.getHourDetails());
	                details.setLuggage(dto.isLuggage());
	                details.setMinPax(dto.getMinpax());
	                details.setMaxPax(dto.getMaxpax());
	                details.setPrivateRate(dto.getPrivateRate());
	                details.setSicRate(dto.getSicRate());
	                details.setTravelType(dto.getTravelType());
	                details.setIsActive(true);
	                detailsList.add(details);
	            }
	        }
	        cabRates.setCabRatesDetails(detailsList);

	        // save cabRates (cascade will save children)
	        CabRates saved = cabRatesRepository.save(cabRates);

	        // Return DTO (simple example: only id + rateCode, map others if needed)
	        CabRateDTO response = new CabRateDTO();
	        response.setCabratesId(saved.getCabRatesId());
	        response.setRateCode(saved.getRateCode());
	        response.setCabId(saved.getCab().getCabId());

	        return response;
	}

	@Override
	public CabRateDTO getCabRateDetailsById(Long id) {
		// TODO Auto-generated method stub
		CabRates cabRates = cabRatesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
	                    "CabRate not found with id: " + id));
	    // Map to DTO
	    CabRateDTO response = new CabRateDTO();
	    response.setCabratesId(cabRates.getCabRatesId());
	    response.setRateCode(cabRates.getRateCode());
	    response.setCabId(cabRates.getCab().getCabId());
	    CabProvider cabProvider = cabRates.getCabProvider();
	    response.setCabproviderId(cabProvider.getCabProviderId());

	    // ---------- Market Types ----------
	    if (cabRates.getMarketTypes() != null) {
	        List<String> marketTypeIds = cabRates.getMarketTypes().stream().map(mt -> String.valueOf(mt.getMarketType().getMarketTypeId())).toList();
	        response.setMarketype(marketTypeIds);
	    }
	    // ---------- Validities ----------
	    if (cabRates.getCabRateValidities() != null) {
	        List<CabRateValidityDTO> validityDTOs = cabRates.getCabRateValidities().stream().map(validity -> {
	            CabRateValidityDTO dto = new CabRateValidityDTO();
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            dto.setCabValidityId(validity.getCabValidityId());
	            dto.setValidityFrom(sdf.format(validity.getValidityFrom()));
	            dto.setValidityTo(sdf.format(validity.getValidityTo()));
	            return dto;
	        }).toList();
	        response.setCabRateValidityDTOList(validityDTOs);
	    }

	    // ---------- Rate Details ----------
	    if (cabRates.getCabRatesDetails() != null) {
	        List<CabRateDetailsDTO> detailsDTOs = cabRates.getCabRatesDetails().stream().map(details -> {
	            CabRateDetailsDTO dto = new CabRateDetailsDTO();
	            dto.setCabRatesdetailsId(details.getCabRatesDetailsId());
	            dto.setLocationId(details.getLocationId());
	            dto.setHourDetails(details.getHourDetails());
	            dto.setLuggage(details.getLuggage() );
	            dto.setMinpax(details.getMinPax());
	            dto.setMaxpax(details.getMaxPax());
	            dto.setPrivateRate(details.getPrivateRate());
	            dto.setSicRate(details.getSicRate());
	            dto.setTravelType(details.getTravelType());
	            return dto;
	        }).toList();
	        response.setCabRateDetailsDTOList(detailsDTOs);
	    }

	    return response;
	}

	@Override
	public CabRateDTO editCabRateDetails(Long id, @Valid CabRateDTO reqDTO) {
		// TODO Auto-generated method stub
	    // Fetch existing entity
	    CabRates cabRates = cabRatesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CabRate not found with id: " + id));

	    // Update base fields
	    cabRates.setRateCode(reqDTO.getRateCode());
	    cabRates.setIsActive(true);

	    // Update Cab
	    Cab cab = cabRespository.findById(reqDTO.getCabId()).orElseThrow(() -> new EntityNotFoundException("CabProvider not found with id: " + reqDTO.getCabId()));
	    cabRates.setCab(cab);

	    // ---------- Update Market Types ----------
	    List<CabRateMarketType> marketTypes = new ArrayList<>();
	    if (reqDTO.getMarketype() != null) {
	        for (String marketId : reqDTO.getMarketype()) {
	            MasterMarketType marketType = marketTypeRepository.findById(Long.valueOf(marketId)).orElseThrow(() -> new RuntimeException("MarketType not found with id: " + marketId));
	            CabRateMarketType cabRateMarketType = new CabRateMarketType();
	            cabRateMarketType.setCabRate(cabRates);
	            cabRateMarketType.setMarketType(marketType);
	            marketTypes.add(cabRateMarketType);
	        }
	    }
	    cabRates.setMarketTypes(marketTypes);

	    // ---------- Update Validities ----------
	    List<CabRateValidity> validities = new ArrayList<>();
	    if (reqDTO.getCabRateValidityDTOList() != null) {
	        for (CabRateValidityDTO dto : reqDTO.getCabRateValidityDTOList()) {
	            CabRateValidity validity = new CabRateValidity();
	            validity.setCabRate(cabRates);
	            try {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                validity.setValidityFrom(sdf.parse(dto.getValidityFrom()));
	                validity.setValidityTo(sdf.parse(dto.getValidityTo()));
	            } catch (Exception e) {
	                throw new RuntimeException("Invalid date format for validity", e);
	            }
	            validities.add(validity);
	        }
	    }
	    cabRates.setCabRateValidities(validities);

	    // ---------- Update Rate Details ----------
	    List<CabRatesDetails> detailsList = new ArrayList<>();
	    if (reqDTO.getCabRateDetailsDTOList() != null) {
	        for (CabRateDetailsDTO dto : reqDTO.getCabRateDetailsDTOList()) {
	            CabRatesDetails details = new CabRatesDetails();
	            details.setCabRate(cabRates);
	            details.setLocationId(Long.valueOf(dto.getLocationId()));
	            details.setHourDetails(dto.getHourDetails());
	            details.setLuggage(dto.isLuggage());
	            details.setMinPax(Integer.valueOf(dto.getMinpax()));
	            details.setMaxPax(Integer.valueOf(dto.getMaxpax()));
	            details.setPrivateRate(Double.valueOf(dto.getPrivateRate()));
	            details.setSicRate(Double.valueOf(dto.getSicRate()));
	            details.setTravelType(dto.getTravelType());
	            details.setIsActive(true);
	            detailsList.add(details);
	        }
	    }
	    cabRates.setCabRatesDetails(detailsList);

	    // Save updated entity
	    CabRates updated = cabRatesRepository.save(cabRates);

	    // Map back to DTO
	    CabRateDTO response = new CabRateDTO();
	    response.setCabratesId(updated.getCabRatesId());

	    return response;
	}

	@Override
	public ResponseEntity<String> deleteCabRateDetails(Long id) {
	    // Fetch the entity
	    CabRates cabRates = cabRatesRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("CabRate not found with id: " + id));

	    // Delete the entity (cascade will delete children if set up)
	    cabRatesRepository.delete(cabRates);

	    // Return response
	    return ResponseEntity.ok("CabRate with ID " + id + " deleted successfully.");
	}

	@Override
	public Page<CabRateDTO> getAllCabRates(Pageable pageable, String search) {
		// TODO Auto-generated method stub
	    Page<CabRates> cabRatesPage= cabRatesRepository.findAll(pageable);

	    // Convert entities to DTOs with full details
	    return cabRatesPage.map(cabRates -> {
	        CabRateDTO dto = new CabRateDTO();
	        dto.setCabratesId(cabRates.getCabRatesId());
	        dto.setRateCode(cabRates.getRateCode());
	        dto.setCabId(cabRates.getCab().getCabId());

	        // ---------- Market Types ----------
	        if (cabRates.getMarketTypes() != null) {
	            dto.setMarketype(cabRates.getMarketTypes().stream()
	                    .map(mt -> String.valueOf(mt.getMarketType().getMarketTypeId()))
	                    .toList());
	        }

	        // ---------- Validities ----------
	        if (cabRates.getCabRateValidities() != null) {
	            List<CabRateValidityDTO> validityDTOs = cabRates.getCabRateValidities().stream().map(validity -> {
	                CabRateValidityDTO vDto = new CabRateValidityDTO();
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                vDto.setValidityFrom(sdf.format(validity.getValidityFrom()));
	                vDto.setValidityTo(sdf.format(validity.getValidityTo()));
	                return vDto;
	            }).toList();
	            dto.setCabRateValidityDTOList(validityDTOs);
	        }

	        // ---------- Rate Details ----------
	        if (cabRates.getCabRatesDetails() != null) {
	            List<CabRateDetailsDTO> detailsDTOs = cabRates.getCabRatesDetails().stream().map(details -> {
	                CabRateDetailsDTO dDto = new CabRateDetailsDTO();
	                dDto.setLocationId(details.getLocationId());
	                dDto.setHourDetails(details.getHourDetails());
	                dDto.setLuggage(details.getLuggage());
	                dDto.setMinpax(details.getMinPax());
	                dDto.setMaxpax(details.getMaxPax());
	                dDto.setPrivateRate(details.getPrivateRate());
	                dDto.setSicRate(details.getSicRate());
	                dDto.setTravelType(details.getTravelType());
	                return dDto;
	            }).toList();
	            dto.setCabRateDetailsDTOList(detailsDTOs);
	        }

	        return dto;
	    });
	}
  
}
