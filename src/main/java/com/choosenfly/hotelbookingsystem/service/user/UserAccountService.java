package com.choosenfly.hotelbookingsystem.service.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.user.UserDTO;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelContactDetails;
import com.choosenfly.hotelbookingsystem.entities.hotel.linked.LinkedHotelContactDetailsMailType;
import com.choosenfly.hotelbookingsystem.entities.user.UserAccount;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidUserTypeException;
import com.choosenfly.hotelbookingsystem.repository.hotel.HotelRepository;
import com.choosenfly.hotelbookingsystem.repository.user.UserRepository;
import com.choosenfly.hotelbookingsystem.service.email.EmailServiceInterface;

import jakarta.transaction.Transactional;

@Service
public class UserAccountService implements UserAccountServiceInterface {

	public final UserRepository userRepository;

	public final HotelRepository hotelRepository;

	private final BCryptPasswordEncoder passwordEncoder;
	
	private final EmailServiceInterface emailService;

	@Autowired
	public UserAccountService(UserRepository userRepository, HotelRepository hotelRepository,
			BCryptPasswordEncoder passwordEncoder,EmailServiceInterface emailService) {
		this.userRepository = userRepository;
		this.hotelRepository = hotelRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	@Override
	@Transactional
	public UserDTO registerUser(UserDTO user) {
		// TODO Auto-generated method stub

		
		switch (user.getUserType()) {
		case HOTEL:
			user = createHotelUser(user);
			break;
		case ADMIN:
			user = createAdminUser(user);
			break;
		case AGENT:
			user = createAgentUser(user);
			break;
		case EMPLOYEE:
			user = createEmployeeUser(user);
			break;
		case SUPER_ADMIN:
			user = createSuperAdminUser(user);
			break;
		default:
			throw new InvalidUserTypeException("Invalid user type: " + user.getUserType());
		}
	
		
		
		
		
		emailService.sendLoginCredentials(user.getUserMailIds(), user.getUserName(), user.getPassword());
		
		return user;
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
		
		Hotel hotel = hotelRepository.findById(user.getUserTyeId())
	            .orElseThrow(() -> new HotelNotFoundException("Hotel not found for ID: " + user.getUserTyeId()));

	    UserAccount userAccount = new UserAccount();
	    userAccount.setActive(true);
	    userAccount.setFirstName(hotel.getHotelName());
	    userAccount.setLastName("-");
	    
	    String encryptedPassword = passwordEncoder.encode(user.getPassword());
	    userAccount.setPassword(encryptedPassword);
	    
	    userAccount.setRole(user.getUserRole());
	    userAccount.setUsername(user.getUserName());
	    userAccount.setUserType("HOTEL");
	    userAccount.setUserTypeId(user.getUserTyeId());
	    UserAccount savedUser = userRepository.save(userAccount);
	    
	    List<HotelContactDetails> contactDetails = hotel.getContactDetails();
	    
	    List<String> mailIds = contactDetails.stream()
	    	    .flatMap(hotelContact -> hotelContact.getMailType().stream())
	    	    .filter(mailType -> mailType.getMasterMailType().getId().equals(1l))
	    	    .map(LinkedHotelContactDetailsMailType::getHotelContactDetails)
	    	    .map(HotelContactDetails::getPersonalEmail)
	    	    .collect(Collectors.toList());

	    	System.out.println("Final mailIds: " + mailIds);
	  
	    String[] mailIdArray = mailIds.toArray(new String[0]);
	    
	    System.err.println("Mail Ids :: "+Arrays.toString(mailIdArray));
	    user.setUserMailIds(mailIdArray);
	    user.setUserId(savedUser.getUserId());
	    
	    return user;
	}

}
