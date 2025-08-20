package com.choosenfly.hotelbookingsystem.auth.service.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.auth.dto.user.UserDTO;
import com.choosenfly.hotelbookingsystem.auth.enitities.role.Role;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserType;
import com.choosenfly.hotelbookingsystem.auth.exceptions.InvalidRoleException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingCredentialsException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingEmailException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.UserRegistrationException;
import com.choosenfly.hotelbookingsystem.auth.repository.role.RoleRepository;
import com.choosenfly.hotelbookingsystem.auth.repository.user.UserRepository;
import com.choosenfly.hotelbookingsystem.auth.repository.user.UserTypeRepository;
import com.choosenfly.hotelbookingsystem.common.email.dto.EmailDTO;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidUserTypeException;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelContactDetails;
import com.choosenfly.hotelbookingsystem.inventory.entities.LinkedHotelContactDetailsMailType;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.util.rabbitmq.EmailProducer;

import jakarta.transaction.Transactional;

@Service
public class UserAccountService implements UserAccountServiceInterface {

	public final UserRepository userRepository;

	public final HotelRepository hotelRepository;

	private final BCryptPasswordEncoder passwordEncoder;
	
	private final EmailProducer emailProducer;
	
	private final UserTypeRepository userTypeRepository;
	
	private final RoleRepository rolerepository;

	@Autowired
	public UserAccountService(UserRepository userRepository, HotelRepository hotelRepository,
			BCryptPasswordEncoder passwordEncoder,
			EmailProducer emailProducer,
			UserTypeRepository userTypeRepository,
			RoleRepository rolerepository) {
		this.userRepository = userRepository;
		this.hotelRepository = hotelRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailProducer = emailProducer;
		this.userTypeRepository = userTypeRepository;
		this.rolerepository = rolerepository;
	}

	@Override
	@Transactional
	public UserDTO registerUser(UserDTO user) {
	    if (user == null) {
	        throw new UserRegistrationException("UserDTO cannot be null.");
	    }

	    if (user.getUserTypeId() == null) {
	        throw new InvalidUserTypeException("UserTypeId is required.");
	    }

	    Optional<UserType> userTypeEntityOpt = userTypeRepository.findById(user.getUserTypeId());
	    if (userTypeEntityOpt.isEmpty()) {
	        throw new InvalidUserTypeException("Invalid userTypeId: " + user.getUserTypeId());
	    }

	    UserType userTypeEntity = userTypeEntityOpt.get();
	    String userTypeName = userTypeEntity.getTypeName();

	    if (userTypeName == null || userTypeName.isBlank()) {
	        throw new InvalidUserTypeException("UserType name is missing for userTypeId: " + user.getUserTypeId());
	    }

	    userTypeName = userTypeName.toUpperCase();

	    UserDTO registeredUser;
	    switch (userTypeName) {
	        case "EXTRANET":
	            registeredUser = createHotelUser(user);
	            break;
	        case "ADMIN":
	            registeredUser = createAdminUser(user);
	            break;
	        case "AGENT":
	            registeredUser = createAgentUser(user);
	            break;
	        case "EMPLOYEE":
	            registeredUser = createEmployeeUser(user);
	            break;
	        case "SUPER_ADMIN":
	            registeredUser = createSuperAdminUser(user);
	            break;
	        default:
	            throw new InvalidUserTypeException("Invalid user type name: " + userTypeName);
	    }

	    if (registeredUser == null) {
	        throw new UserRegistrationException("Failed to register user for type: " + userTypeName);
	    }

	    if (registeredUser.getUserMailIds() == null || registeredUser.getUserMailIds().length == 0) {
	        throw new MissingEmailException("No email ID found to send credentials.");
	    }

	    if (registeredUser.getUserName() == null || registeredUser.getPassword() == null) {
	        throw new MissingCredentialsException("Username or password missing for sending email.");
	    }

	    EmailDTO emailDTO = new EmailDTO();
	    emailDTO.setToEmail(registeredUser.getUserMailIds());
	    emailDTO.setUsername(registeredUser.getUserName());
	    emailDTO.setPassword(registeredUser.getPassword());

	    emailProducer.sendMessage(emailDTO);

	    return registeredUser;
	}



	private UserDTO createSuperAdminUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDTO createEmployeeUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDTO createAgentUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDTO createAdminUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDTO createHotelUser(UserDTO user) {
		// TODO Auto-generated method stub
		
		Hotel hotel = hotelRepository.findById(user.getUserId())
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found for ID: " + user.getUserId()));

List<HotelContactDetails> contactDetails = hotel.getContactDetails();
	    
	    List<String> mailIds = contactDetails.stream()
	    	    .flatMap(hotelContact -> hotelContact.getMailType().stream())
	    	    .filter(mailType -> mailType.getMasterMailType().getId().equals(1l))
	    	    .map(LinkedHotelContactDetailsMailType::getHotelContactDetails)
	    	    .map(HotelContactDetails::getPersonalEmail)
	    	    .collect(Collectors.toList());

	    if (mailIds == null || mailIds.size() == 0) {
	        throw new MissingEmailException("No email ID found to send credentials.");
	    }
	    
	  
	    String[] mailIdArray = mailIds.toArray(new String[0]);
		
	    UserAccount userAccount = new UserAccount();
	    userAccount.setActive(true);
	    userAccount.setUserId(user.getUserId());
	    
	    String encryptedPassword = passwordEncoder.encode(user.getPassword());
	    userAccount.setPassword(encryptedPassword);
	    
	   
	   
	   // userAccount.setUserRoles(roles);
	    
	    Optional<UserAccount> existing = userRepository.findByUsername(user.getUserName());
	    if (existing.isPresent()) {
	        throw new UserRegistrationException("Username already exists.");
	    }
	    
	    userAccount.setUsername(user.getUserName());
	 //   userAccount.setUserType("HOTEL");
	    
	    Optional<UserType> userTypeEntityOpt = userTypeRepository.findById(user.getUserTypeId());
		if (!userTypeEntityOpt.isPresent()) {
			throw new InvalidUserTypeException("Invalid userTypeId: " + user.getUserTypeId());
		}
		UserType userTypeEntity = userTypeEntityOpt.get();
	    userAccount.setUserType(userTypeEntity);
	    
	    
	    List<Long> inputRoleIds = user.getUserRoleIds();

	 // If no roles provided, assign default role based on userTypeId
	 if (inputRoleIds == null || inputRoleIds.isEmpty()) {
	     Long defaultRoleId = user.getUserTypeId();
	     Role defaultRole = rolerepository.findById(defaultRoleId)
	             .orElseThrow(() -> new InvalidRoleException("No role found for userTypeId: " + defaultRoleId));

	     userAccount.setUserRoles(Set.of(defaultRole));
	 } else {
	     // Validate provided roles
	     List<Role> foundRoles = rolerepository.findAllById(inputRoleIds);

	     if (foundRoles.size() != inputRoleIds.size()) {
	         // Find missing role IDs
	         Set<Long> foundIds = foundRoles.stream().map(Role::getId).collect(Collectors.toSet());
	         List<Long> missingIds = inputRoleIds.stream()
	                                             .filter(id -> !foundIds.contains(id))
	                                             .collect(Collectors.toList());
	         throw new InvalidRoleException("Invalid role IDs: " + missingIds);
	     }

	     Set<Role> roles = new HashSet<>(foundRoles);
	     userAccount.setUserRoles(roles);
	 }

	    // Save user
	
	    UserAccount savedUser = userRepository.save(userAccount);
	    
	    
	    
	    System.err.println("Mail Ids :: "+Arrays.toString(mailIdArray));
	    user.setUserMailIds(mailIdArray);
	    user.setUserId(savedUser.getUserId());
	    
	    return user;
	}

}
