package com.choosenfly.hotelbookingsystem.registration.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.registration.employee.enitities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
