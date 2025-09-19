package com.choosenfly.hotelbookingsystem.registration.employee.service;

import java.io.File;
import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.configuration.FileStorageProperties;
import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeResponseDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.enitities.Employee;
import com.choosenfly.hotelbookingsystem.registration.employee.enitities.EmployeeContactDetails;
import com.choosenfly.hotelbookingsystem.registration.employee.exceptions.EmployeetRegistrationException;
import com.choosenfly.hotelbookingsystem.registration.employee.exceptions.handler.EmployeeNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.employee.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	 private final String uploadDir;

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,FileStorageProperties fileStorageProperties) {
        this.employeeRepository = employeeRepository;
        this.uploadDir = fileStorageProperties.getDirectory();
        
        File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()) {
			uploadDirFile.mkdir();
		}
    }

    @Override
    public EmployeeResponseDTO registerEmployee(@Valid EmployeeDTO request) {
    	
    	if(request==null) {
    		throw new EmployeetRegistrationException("Employe request cannot be null");
    	}
        
    	Employee employee = new Employee();

        // --- Map EmployeeDTO -> Employee entity ---
        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setDesignation(request.getDesignation());
        employee.setDob(request.getDob());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setIsActive(request.getActive());
        employee.setMarkupType(request.getMarkupType());
        
        //saving multi part file
        MultipartFile Img =request.getEmployeeProfile();

        if (Img != null && !Img.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + Img.getOriginalFilename();
                File saveFile = new File(uploadDir + fileName);
                
                // Ensure parent directories exist
                saveFile.getParentFile().mkdirs();
                
                // Save the file
                Img.transferTo(saveFile);
                employee.setEmployeeImg(saveFile.getAbsolutePath());
                
            } catch (IOException e) {
                // Continue without image path
            	employee.setEmployeeImg(null);
            } catch (Exception e) {
            	System.out.println("Unexpected error while saving itinerary image"+ e);
                // Continue without image path
            	employee.setEmployeeImg(null);
            }
        }

        // --- Contact Details ---
        if (request.getContactDetails() != null) {
            EmployeeContactDetailsDTO cdto = request.getContactDetails();
            EmployeeContactDetails contactDetails = new EmployeeContactDetails();
            contactDetails.setEmail(cdto.getEmail());
            contactDetails.setMobileNumber(cdto.getMobileNumber());
            contactDetails.setFaxNumber(cdto.getFaxNumber());
            contactDetails.setTelexNumber(cdto.getTelexNumber());
            contactDetails.setAddress(cdto.getAddress());
            contactDetails.setZipcode(cdto.getZipcode());

            // set bi-directional mapping
            contactDetails.setEmployee(employee);
            employee.setContactDetails(contactDetails);
        }

        // Save employee (will cascade contact details because of CascadeType.ALL)
        Employee savedEmployee = employeeRepository.save(employee);

        // --- Prepare Response DTO ---
        EmployeeResponseDTO response = new EmployeeResponseDTO();
        if(savedEmployee.getEmployeeId()>0l && savedEmployee.getEmployeeId()!=null) {
        	response.setId(savedEmployee.getEmployeeId());
        	response.setSuccess(true);
        	response.setMessage("Registration successful");
        }
        else
        {
        	response.setSuccess(false);
        	response.setMessage("Registration not successful");
        }
       
        return response;
    }

    @Override
    public EmployeeDTO getEmployeeRegistrationDetailsById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Employee ID must be a positive number");
        }
        
        // Fetch employee from repository
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        
        // Convert Employee entity to EmployeeDTO
        EmployeeDTO employeeDTO = new EmployeeDTO();
        
        // Map basic employee details
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeCode(employee.getEmployeeCode());
        employeeDTO.setDesignation(employee.getDesignation());
        employeeDTO.setDob(employee.getDob());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setActive(employee.getIsActive());
        employeeDTO.setMarkupType(employee.getMarkupType());
        
        // Handle employee profile image - convert to Base64 or file path as needed
        if (employee.getEmployeeImg() != null) {
            // Option 1: Return file path
            employeeDTO.setImagePath(employee.getEmployeeImg());
            
            // Option 2: If you want to return Base64 encoded image
            /*
            try {
                Path imagePath = Paths.get(employee.getEmployeeImg());
                byte[] imageBytes = Files.readAllBytes(imagePath);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                employeeDTO.setEmployeeProfile(base64Image);
            } catch (IOException e) {
                // Log error but continue without image
                System.out.println("Error reading employee image: " + e.getMessage());
            }
            */
        }
        
        // Map contact details if they exist
        if (employee.getContactDetails() != null) {
            EmployeeContactDetails contactDetails = employee.getContactDetails();
            EmployeeContactDetailsDTO contactDetailsDTO = new EmployeeContactDetailsDTO();
            
            contactDetailsDTO.setContactId(contactDetails.getEmployeeContactId());
            contactDetailsDTO.setEmail(contactDetails.getEmail());
            contactDetailsDTO.setMobileNumber(contactDetails.getMobileNumber());
            contactDetailsDTO.setFaxNumber(contactDetails.getFaxNumber());
            contactDetailsDTO.setTelexNumber(contactDetails.getTelexNumber());
            contactDetailsDTO.setAddress(contactDetails.getAddress());
            contactDetailsDTO.setZipcode(contactDetails.getZipcode());
            
            employeeDTO.setContactDetails(contactDetailsDTO);
        }
        
        return employeeDTO;
    }

    @Override
    public EmployeeDTO editEmployeeRegistrationDetails(Long id, @Valid EmployeeDTO reqDTO) {
        if (reqDTO == null) {
            throw new EmployeetRegistrationException("Employee request cannot be null");
        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeetRegistrationException("Employee not found with id: " + id));

        // --- Update Employee entity fields ---
        employee.setEmployeeCode(reqDTO.getEmployeeCode());
        employee.setDesignation(reqDTO.getDesignation());
        employee.setDob(reqDTO.getDob());
        employee.setFirstName(reqDTO.getFirstName());
        employee.setLastName(reqDTO.getLastName());
        employee.setIsActive(reqDTO.getActive());
        employee.setMarkupType(reqDTO.getMarkupType());

        // --- Update profile image ---
        MultipartFile img = reqDTO.getEmployeeProfile();
        if (img != null && !img.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + img.getOriginalFilename();
                File saveFile = new File(uploadDir + fileName);

                // Ensure directories exist
                saveFile.getParentFile().mkdirs();

                // Save new file
                img.transferTo(saveFile);

                // Update entity with new image path
                employee.setEmployeeImg(saveFile.getAbsolutePath());

            } catch (IOException e) {
                System.out.println("Error updating employee profile image: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error while updating employee profile image: " + e);
            }
        }

        // --- Update Contact Details ---
        if (reqDTO.getContactDetails() != null) {
            EmployeeContactDetailsDTO cdto = reqDTO.getContactDetails();

            EmployeeContactDetails contactDetails = employee.getContactDetails();
            if (contactDetails == null) {
                contactDetails = new EmployeeContactDetails();
                contactDetails.setEmployee(employee); // maintain relationship
            }

            contactDetails.setEmail(cdto.getEmail());
            contactDetails.setMobileNumber(cdto.getMobileNumber());
            contactDetails.setFaxNumber(cdto.getFaxNumber());
            contactDetails.setTelexNumber(cdto.getTelexNumber());
            contactDetails.setAddress(cdto.getAddress());
            contactDetails.setZipcode(cdto.getZipcode());

            employee.setContactDetails(contactDetails);
        }

        // --- Save updated employee ---
        Employee updatedEmployee = employeeRepository.save(employee);

        // --- Map back to DTO ---
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(updatedEmployee.getEmployeeId());
        dto.setEmployeeCode(updatedEmployee.getEmployeeCode());
        dto.setDesignation(updatedEmployee.getDesignation());
        dto.setDob(updatedEmployee.getDob());
        dto.setFirstName(updatedEmployee.getFirstName());
        dto.setLastName(updatedEmployee.getLastName());
        dto.setActive(updatedEmployee.getIsActive());
        dto.setMarkupType(updatedEmployee.getMarkupType());
        dto.setEmployeeProfile(null); // Don’t return file itself, only path

        if (updatedEmployee.getContactDetails() != null) {
            EmployeeContactDetailsDTO cdto = new EmployeeContactDetailsDTO();
            cdto.setEmail(updatedEmployee.getContactDetails().getEmail());
            cdto.setMobileNumber(updatedEmployee.getContactDetails().getMobileNumber());
            cdto.setFaxNumber(updatedEmployee.getContactDetails().getFaxNumber());
            cdto.setTelexNumber(updatedEmployee.getContactDetails().getTelexNumber());
            cdto.setAddress(updatedEmployee.getContactDetails().getAddress());
            cdto.setZipcode(updatedEmployee.getContactDetails().getZipcode());
            dto.setContactDetails(cdto);
        }

        return dto;
    }

    @Override
    public ResponseEntity<String> deleteEmployeeRegistrationDetails(Long id) {
        // Find employee by id
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeetRegistrationException("Employee not found with id: " + id));

        // Delete uploaded image file if exists
        if (employee.getEmployeeImg() != null) {
            File file = new File(employee.getEmployeeImg());
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    System.out.println("Deleted employee image file: " + file.getAbsolutePath());
                } else {
                    System.out.println("Could not delete employee image file: " + file.getAbsolutePath());
                }
            }
        }

        // Delete employee record (cascade will handle contact details)
        employeeRepository.delete(employee);

        // Return response
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
    }

    @Override
    public Page<EmployeeDTO> getAllEmployees(Pageable pageable, String search) {


        // If search is provided, filter by employeeCode, firstName, or lastName

         Page<Employee>   employees = employeeRepository.findAll(pageable);


        // Map entities to DTOs
        return employees.map(employee -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setEmployeeId(employee.getEmployeeId());
            dto.setEmployeeCode(employee.getEmployeeCode());
            dto.setDesignation(employee.getDesignation());
            dto.setDob(employee.getDob());
            dto.setFirstName(employee.getFirstName());
            dto.setLastName(employee.getLastName());
            dto.setActive(employee.getIsActive());
            dto.setMarkupType(employee.getMarkupType());
            dto.setEmployeeProfile(null); // don't return file, you may return path instead

            if (employee.getContactDetails() != null) {
                EmployeeContactDetailsDTO cdto = new EmployeeContactDetailsDTO();
                cdto.setContactId(employee.getContactDetails().getEmployeeContactId());
                cdto.setEmail(employee.getContactDetails().getEmail());
                cdto.setMobileNumber(employee.getContactDetails().getMobileNumber());
                cdto.setFaxNumber(employee.getContactDetails().getFaxNumber());
                cdto.setTelexNumber(employee.getContactDetails().getTelexNumber());
                cdto.setAddress(employee.getContactDetails().getAddress());
                cdto.setZipcode(employee.getContactDetails().getZipcode());
                dto.setContactDetails(cdto);
            }

            return dto;
        });
    }


}
