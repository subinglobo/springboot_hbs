package com.choosenfly.hotelbookingsystem.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.user.UserDTO;
import com.choosenfly.hotelbookingsystem.service.user.UserAccountServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserAccountServiceInterface userAccountService;

	@Autowired
	public UserController(UserAccountServiceInterface userAccountService) {
		this.userAccountService = userAccountService;
	}

	
	
	@PostMapping("/register")
	public UserDTO registerUser(@Valid @RequestBody UserDTO user) {
		return userAccountService.registerUser(user);
	}
}
