package com.choosenfly.hotelbookingsystem.registration.employee.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplymentsDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.dto.EmployeeResponseDTO;
import com.choosenfly.hotelbookingsystem.registration.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
    @PostMapping("/register")
    public ResponseEntity<EmployeeResponseDTO> registerEmployee(@Valid @RequestBody EmployeeDTO request) {
    	
    	System.out.println("request:"+request);
        
    	EmployeeResponseDTO employee = employeeService.registerEmployee(request);
        
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
	private EmployeeDTO getEmployeeRegistrationDetailsById(@PathVariable("id") Long id) {
		
		return employeeService.getEmployeeRegistrationDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private EmployeeDTO editEmployeeRegistrationDetails(@PathVariable("id") Long id , @Valid @RequestBody EmployeeDTO reqDTO) {
		return employeeService.editEmployeeRegistrationDetails(id , reqDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteEmployeeRegistrationDetails(@PathVariable("id") Long id) {
		return employeeService.deleteEmployeeRegistrationDetails(id);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<EmployeeDTO> Page = employeeService.getAllEmployees(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<EmployeeDTO> employeesList = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(employeesList, HttpStatus.OK);
	}
    
    
}
