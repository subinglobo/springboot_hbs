package com.choosenfly.hotelbookingsystem.roles.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.auth.enitities.role.Role;
import com.choosenfly.hotelbookingsystem.roles.dto.UserRolesDTO;
import com.choosenfly.hotelbookingsystem.roles.repository.UserRolesRepository;

@Service
public class UserRolesService implements UserRolesServiceInterface {
	
	private UserRolesRepository userRolesRepository;
	
	@Autowired
	public UserRolesService(UserRolesRepository userRolesRepository) {
		this.userRolesRepository = userRolesRepository;
	}

	   @Override
	    @Transactional(readOnly = true)
	    public List<UserRolesDTO> getUserRoles() {
	        // Fetch all entities from DB
	        List<Role> roles = userRolesRepository.findAll();

	        // Map entities to DTOs
	        return roles.stream().map(role -> {
	            UserRolesDTO dto = new UserRolesDTO();
	            dto.setId(role.getId());
	            dto.setRoleName(role.getRoleName());
	            dto.setDescription(role.getDescription());
	            return dto;
	        }).collect(Collectors.toList());
	    }

}
