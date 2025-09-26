package com.choosenfly.hotelbookingsystem.registration.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.registration.employee.enitities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Page<Employee> findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCaseOrEmployeeCodeStartingWithIgnoreCase(
			String search, String search2, String search3, Pageable pageable);

}
