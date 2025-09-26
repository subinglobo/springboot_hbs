package com.choosenfly.hotelbookingsystem.registration.cab.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.configuration.FileStorageProperties;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterItenaryDetailsRepository;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabImageUploadDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabImageUploadRequest;
import com.choosenfly.hotelbookingsystem.registration.cab.entities.Cab;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.cab.repository.CabRespository;

import jakarta.transaction.Transactional;

@Service
public class CabImageUploadServiceImpl implements  CabImageUploadService{

	private final CabRespository cabRespository;
	
	 private final String uploadDir;

	@Autowired
	public CabImageUploadServiceImpl(CabRespository cabRespository,FileStorageProperties fileStorageProperties) {
		this.cabRespository = cabRespository;
		this.uploadDir = fileStorageProperties.getDirectory();
		
		File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()) {
			uploadDirFile.mkdir();
		}
		
	}
	
	 @Transactional
	    public void saveCabImages(CabImageUploadRequest request) {
	        try {
	            // ensure upload directory exists
	            Files.createDirectories(Paths.get(uploadDir));

	            for (CabImageUploadDTO dto : request.getCabImages()) {
	                Cab cab = cabRespository.findById(dto.getCabId())
	                        .orElseThrow(() -> new EntityNotFoundException("Cab not found with id: " + dto.getCabId()));

	                MultipartFile file = dto.getFile();
	                if (file != null && !file.isEmpty()) {
	                    String fileName = dto.getCabId() + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
	                    Path filePath = Paths.get(uploadDir, fileName);

	                    // write file to disk
	                 // Write file to disk
	                    Files.write(filePath, file.getBytes());

	                    // Save full path to cab entity
	                    cab.setCabPic(filePath.toAbsolutePath().toString());  // <-- full path
	                    cabRespository.save(cab);
	                }
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to save cab images", e);
	        }
	    }

	 @Override
	 @Transactional
	 public void updateCabImages(CabImageUploadRequest request) {
	     try {
	         Files.createDirectories(Paths.get(uploadDir));

	         for (CabImageUploadDTO dto : request.getCabImages()) {
	             Cab cab = cabRespository.findById(dto.getCabId())
	                     .orElseThrow(() -> new EntityNotFoundException("Cab not found with id: " + dto.getCabId()));

	             MultipartFile file = dto.getFile();
	             if (file != null && !file.isEmpty()) {
	                 // Delete old file if exists
	                 if (cab.getCabPic() != null) {
	                     Path oldFilePath = Paths.get(cab.getCabPic());
	                     Files.deleteIfExists(oldFilePath);
	                 }

	                 // Save new file
	                 String fileName = dto.getCabId() + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
	                 Path filePath = Paths.get(uploadDir, fileName);
	                 Files.write(filePath, file.getBytes());

	                 // Save full path to entity
	                 cab.setCabPic(filePath.toAbsolutePath().toString());
	                 cabRespository.save(cab);
	             }
	         }
	     } catch (IOException e) {
	         throw new RuntimeException("Failed to update cab images", e);
	     }
	 }

	 @Override
	 @Transactional
	 public void deleteCabImages(Long cabId) {
	     Cab cab = cabRespository.findById(cabId)
	             .orElseThrow(() -> new EntityNotFoundException("Cab not found with id: " + cabId));

	     if (cab.getCabPic() != null) {
	         try {
	             Path filePath = Paths.get(cab.getCabPic());
	             Files.deleteIfExists(filePath); // delete from disk
	         } catch (IOException e) {
	             throw new RuntimeException("Failed to delete cab image", e);
	         }
	         cab.setCabPic(null); // remove reference from entity
	         cabRespository.save(cab);
	     }
	 }


}
