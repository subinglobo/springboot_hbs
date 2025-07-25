package com.choosenfly.hotelbookingsystem.service.masters.itenaryDetails;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterItenaryDetailsDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterItenaryDetails;
import com.choosenfly.hotelbookingsystem.repository.master.MasterAgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterItenaryDetailsRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ItenaryDetailsService implements ItenaryDetailsServiceInterface{
	
	private final MasterItenaryDetailsRepository itenaryDetailsRepository;
	
	@Autowired
	public ItenaryDetailsService(MasterItenaryDetailsRepository itenaryDetailsRepository) {
		this.itenaryDetailsRepository = itenaryDetailsRepository;
		
	}

	@Override
	@Transactional
	public Long saveItenaryDetails(@Valid MasterItenaryDetailsDTO itenaryDTO) {
		// TODO Auto-generated method stub
		MasterItenaryDetails entity = new MasterItenaryDetails();
		entity.setItineraryCode(itenaryDTO.getItineraryCode());
		entity.setItineraryDesc(itenaryDTO.getItineraryDesc());
		entity.setItineraryHeading(itenaryDTO.getItineraryHeading());
	    // Handle image upload
		MultipartFile itineraryImg = itenaryDTO.getItineraryImg();
	    if (itineraryImg != null && !itineraryImg.isEmpty()) {
	//        String uploadDir = ContextConstants.fileStoreLocationSave + "/itineraryImages/";
//	        String imageUrl = saveImage(uploadDir, itineraryImg);
//	        entity.setItineraryImg(imageUrl);
	    }

		return null;
	}
	
	private String saveImage(String directory, MultipartFile file) throws IOException {
	    if (file == null || file.isEmpty()) {
	        throw new IllegalArgumentException("File is null or empty");
	    }
	    
	    String originalFilename = file.getOriginalFilename();
	    if (originalFilename == null) {
	        originalFilename = "unnamed_file";
	    }
	    
	    String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
	    String fileName = System.currentTimeMillis() + "_" + sanitizedFilename;
	    File dest = new File(directory + fileName);
	    dest.getParentFile().mkdirs();
	    file.transferTo(dest);
	    
	    // Return the public URL (adjust this based on your actual public URL structure)
	    String publicUrlPrefix = "https://b2b.choosenfly.com/assets/itineraryImages/";
	    return publicUrlPrefix + fileName;
	}

	@Override
	public MasterAgentCategoryDTO getItenaryDetailsById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MasterAgentCategoryDTO editItenaryDetails(Long id, @Valid MasterItenaryDetailsDTO itenaryDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteItenaryDetails(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
