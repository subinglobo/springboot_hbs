package com.choosenfly.hotelbookingsystem.registration.cab.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabImageUploadRequest;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabProviderDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabRateDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.service.CabImageUploadService;
import com.choosenfly.hotelbookingsystem.registration.cab.service.CabProviderService;

import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cabImage")
public class CabImageUploadController {

	@Autowired
	private CabImageUploadService cabImageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCabImages(@ModelAttribute CabImageUploadRequest request) throws IOException {
    	
    	System.out.println("request::"+request);
    	cabImageUploadService.saveCabImages(request);
        return ResponseEntity.ok("Cab images uploaded successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCabImages(@PathVariable("id") Long id,@ModelAttribute CabImageUploadRequest request) throws IOException {
    	
    	System.out.println("request::"+request);
    	cabImageUploadService.updateCabImages(request);
        return ResponseEntity.ok("Cab images updated successfully!");
    }
   	
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCabImages(@PathVariable("id") Long id) {
        cabImageUploadService.deleteCabImages(id);
        return ResponseEntity.ok("Cab image deleted successfully!");
    }
 
       
}
