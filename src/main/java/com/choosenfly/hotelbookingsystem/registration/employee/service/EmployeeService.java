package com.choosenfly.hotelbookingsystem.registration.employee.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeResponseDTO;

import jakarta.validation.Valid;

public interface EmployeeService {

	EmployeeResponseDTO registerEmployee(@Valid EmployeeDTO request);

	EmployeeDTO getEmployeeRegistrationDetailsById(Long id);

	EmployeeDTO editEmployeeRegistrationDetails(Long id, @Valid EmployeeDTO reqDTO);

	ResponseEntity<String> deleteEmployeeRegistrationDetails(Long id);

	Page<EmployeeDTO> getAllEmployees(Pageable pageable, String search);

}
