package com.choosenfly.hotelbookingsystem.roles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.auth.dto.user.UserDTO;
import com.choosenfly.hotelbookingsystem.roles.dto.UserRolesDTO;
import com.choosenfly.hotelbookingsystem.roles.service.UserRolesServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/userRoles")
public class UserRolesController {
	
	private UserRolesServiceInterface userRolesServiceInterface;
	
	@Autowired
	public UserRolesController(UserRolesServiceInterface userRolesServiceInterface) {
		this.userRolesServiceInterface = userRolesServiceInterface;
	}
	
	   @GetMapping
	    public List<UserRolesDTO> getUserRoles() {
	        return userRolesServiceInterface.getUserRoles();
	    }

}
