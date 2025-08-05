package com.choosenfly.hotelbookingsystem.masters.service.itenaryDetails;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.configuration.FileStorageProperties;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterItenaryDetailsDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterVisaInformationDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterItenaryDetails;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterVisaInformation;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterAgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterItenaryDetailsRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ItenaryDetailsService implements ItenaryDetailsServiceInterface{
	
	private final MasterItenaryDetailsRepository itenaryDetailsRepository;
	
	 private final String uploadDir;
	
	@Autowired
	public ItenaryDetailsService(MasterItenaryDetailsRepository itenaryDetailsRepository,FileStorageProperties fileStorageProperties) {
		this.itenaryDetailsRepository = itenaryDetailsRepository;
		this.uploadDir = fileStorageProperties.getDirectory();
		
		File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()) {
			uploadDirFile.mkdir();
		}
		
	}

//	public Long saveItenaryDetails(@Valid MasterItenaryDetailsDTO itenaryDTO) {
//	    
//	    MasterItenaryDetails entity = new MasterItenaryDetails();
//	    entity.setItineraryCode(itenaryDTO.getItineraryCode());
//	    entity.setItineraryDesc(itenaryDTO.getItineraryDesc());
//	    entity.setItineraryHeading(itenaryDTO.getItineraryHeading());
//
//	    MultipartFile itineraryImg = itenaryDTO.getItineraryImg();
//	    System.out.println("itineraryImg:::" + itineraryImg);
//
//	    if (itineraryImg != null && !itineraryImg.isEmpty()) {
//	        try {
////	            String uploadDir = "D:/akhil sajeev/save/";
//	            String fileName = System.currentTimeMillis() + "_" + itineraryImg.getOriginalFilename(); // prevent overwrite
//
//	            File saveFile = new File(uploadDir + fileName);
//	            saveFile.getParentFile().mkdirs();
//
//	            itineraryImg.transferTo(saveFile);
//	            System.out.println("Image saved to disk at: " + saveFile.getAbsolutePath());
//	            entity.setItineraryImg(saveFile.getAbsolutePath()); // Assuming your entity has this field
//
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    itenaryDetailsRepository.save(entity);
//	    return entity.getItineraryId(); // Assuming your entity has an auto-generated ID
//	}
	
    public Long saveItenaryDetails(@Valid MasterItenaryDetailsDTO itenaryDTO) {
        MasterItenaryDetails entity = new MasterItenaryDetails();
        entity.setItineraryCode(itenaryDTO.getItineraryCode());
        entity.setItineraryDesc(itenaryDTO.getItineraryDesc());
        entity.setItineraryHeading(itenaryDTO.getItineraryHeading());

        MultipartFile itineraryImg = itenaryDTO.getItineraryImg();

        if (itineraryImg != null && !itineraryImg.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + itineraryImg.getOriginalFilename();
                File saveFile = new File(uploadDir + fileName);
                
                // Ensure parent directories exist
                saveFile.getParentFile().mkdirs();
                
                // Save the file
                itineraryImg.transferTo(saveFile);
                entity.setItineraryImg(saveFile.getAbsolutePath());
                
            } catch (IOException e) {
                // Continue without image path
                entity.setItineraryImg(null);
            } catch (Exception e) {
            	System.out.println("Unexpected error while saving itinerary image"+ e);
                // Continue without image path
                entity.setItineraryImg(null);
            }
        }

        // Save the entity
        MasterItenaryDetails savedEntity = itenaryDetailsRepository.save(entity);
        
        System.out.println("Saved itinerary with ID: {}"+ savedEntity.getItineraryId());
        
        return savedEntity.getItineraryId();
    
}
	
	@Override
	@Transactional
	public MasterItenaryDetailsDTO getItenaryDetailsById(Long id) {
	    MasterItenaryDetails itenaryDetails = itenaryDetailsRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("itinerary is not found Exception: " + id));

	    if (itenaryDetails.getItineraryId() != null) {
	        MasterItenaryDetailsDTO itenaryDetailsDTO = new MasterItenaryDetailsDTO();
	        itenaryDetailsDTO.setItineraryCode(itenaryDetails.getItineraryCode());
	        itenaryDetailsDTO.setItineraryDesc(itenaryDetails.getItineraryDesc());
	        itenaryDetailsDTO.setItineraryHeading(itenaryDetails.getItineraryHeading());
	        itenaryDetailsDTO.setItineraryId(itenaryDetails.getItineraryId());

	        itenaryDetailsDTO.setImagePath(itenaryDetails.getItineraryImg());

	        return itenaryDetailsDTO;
	    }

	    return null;
	}

	@Override
	public MasterItenaryDetailsDTO editItenaryDetails(Long id, @Valid MasterItenaryDetailsDTO itenaryDTO) {

	    // Step 1: Fetch existing record
	    MasterItenaryDetails itenaryDetails = itenaryDetailsRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Itinerary not found: " + id));

	    // Step 2: Update text fields
	    itenaryDetails.setItineraryCode(itenaryDTO.getItineraryCode());
	    itenaryDetails.setItineraryDesc(itenaryDTO.getItineraryDesc());
	    itenaryDetails.setItineraryHeading(itenaryDTO.getItineraryHeading());

	    // Step 3: Handle file replacement
	    MultipartFile newFile = itenaryDTO.getItineraryImg();
	    if (newFile != null && !newFile.isEmpty()) {
	        try {
	            // Define upload path
//	            String uploadDir = "D:/akhil sajeev/save/";
	            String newFileName = System.currentTimeMillis() + "_" + newFile.getOriginalFilename();
	            File destFile = new File(uploadDir + newFileName);
	            destFile.getParentFile().mkdirs();

	            // Save new file to disk
	            newFile.transferTo(destFile);

	            // Optionally: delete old file if needed
	            String oldFilePath = itenaryDetails.getItineraryImg();
	            if (oldFilePath != null) {
	                File oldFile = new File(oldFilePath);
	                if (oldFile.exists()) {
	                    oldFile.delete(); // delete old file
	                }
	            }

	            // Update path in entity
	            itenaryDetails.setItineraryImg(destFile.getAbsolutePath());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Step 4: Save updated entity
	    itenaryDetailsRepository.save(itenaryDetails);

	    // Step 5: Return updated DTO
	    MasterItenaryDetailsDTO responseDTO = new MasterItenaryDetailsDTO();
	    responseDTO.setItineraryId(itenaryDetails.getItineraryId());
	    responseDTO.setItineraryCode(itenaryDetails.getItineraryCode());
	    responseDTO.setItineraryDesc(itenaryDetails.getItineraryDesc());
	    responseDTO.setItineraryHeading(itenaryDetails.getItineraryHeading());
	    responseDTO.setImagePath(itenaryDetails.getItineraryImg());

	    return responseDTO;
	}

	@Override
	public ResponseEntity<String> deleteItenaryDetails(Long id) {
		// TODO Auto-generated method stub
		  MasterItenaryDetails itenaryDetails = itenaryDetailsRepository.findById(id)
		            .orElseThrow(() -> new com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException("Itinerary not found: " + id));
		  
		    String imagePath = itenaryDetails.getItineraryImg(); // or .getItineraryImg()
		    if (imagePath != null) {
		        File imageFile = new File(imagePath);
		        if (imageFile.exists()) {
		            imageFile.delete();
		        }
		    }
		    itenaryDetailsRepository.delete(itenaryDetails);
		    
		  return ResponseEntity.ok("Itenary with id " + id + " deleted successfully");

	}

	@Override
	@Transactional
	public Page<MasterItenaryDetailsDTO> getAllItenaryDetails(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		
	    Page<MasterItenaryDetails> itenaryPage;

	    if (StringUtils.hasText(search)) {
	    	itenaryPage = itenaryDetailsRepository.findByitineraryHeadingContainingIgnoreCase(search, pageable);
	    } else {
	    	itenaryPage = itenaryDetailsRepository.findAll(pageable);
	    }

	    return itenaryPage.map(dayActivity -> {
	    	MasterItenaryDetailsDTO dto = new MasterItenaryDetailsDTO();
	    	dto.setItineraryId(dayActivity.getItineraryId());
	    	dto.setImagePath(dayActivity.getItineraryImg());
	    	dto.setItineraryCode(dayActivity.getItineraryCode());
	    	dto.setItineraryDesc(dayActivity.getItineraryDesc());
	    	dto.setItineraryHeading(dayActivity.getItineraryHeading());
	        return dto;
	    });
	}
	

}
