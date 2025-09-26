package com.choosenfly.hotelbookingsystem.auth.service.user;

import com.choosenfly.hotelbookingsystem.auth.dto.user.UserAccountsDTO;
import com.choosenfly.hotelbookingsystem.auth.dto.user.UserDTO;

import jakarta.validation.Valid;

public interface UserAccountServiceInterface {

	
	public UserDTO registerUser(UserDTO user);

	public UserAccountsDTO checkRegisteredUserExist(@Valid Long userId);

	
}
