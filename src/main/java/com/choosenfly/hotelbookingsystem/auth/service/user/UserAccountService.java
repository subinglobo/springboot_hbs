package com.choosenfly.hotelbookingsystem.auth.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.agent.entity.Agent;
import com.choosenfly.hotelbookingsystem.agent.exception.AgentRegistrationException;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentRepository;
import com.choosenfly.hotelbookingsystem.auth.dto.user.UserAccountsDTO;
import com.choosenfly.hotelbookingsystem.auth.dto.user.UserDTO;
import com.choosenfly.hotelbookingsystem.auth.enitities.role.Role;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserType;
import com.choosenfly.hotelbookingsystem.auth.exceptions.InvalidRoleException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingCredentialsException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingEmailException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.RegisteredUserNotFoundException;
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
import com.choosenfly.hotelbookingsystem.registration.employee.enitities.Employee;
import com.choosenfly.hotelbookingsystem.registration.employee.enitities.EmployeeContactDetails;
import com.choosenfly.hotelbookingsystem.registration.employee.exceptions.EmployeetRegistrationException;
import com.choosenfly.hotelbookingsystem.registration.employee.repository.EmployeeRepository;
import com.choosenfly.hotelbookingsystem.util.rabbitmq.EmailProducer;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserAccountService implements UserAccountServiceInterface {

	public final UserRepository userRepository;

	public final HotelRepository hotelRepository;

	private final BCryptPasswordEncoder passwordEncoder;

	private final EmailProducer emailProducer;

	private final UserTypeRepository userTypeRepository;

	private final RoleRepository rolerepository;

	private final AgentRepository agentRepository;

	private final EmployeeRepository employeeRepository;

	@Autowired
	public UserAccountService(UserRepository userRepository, HotelRepository hotelRepository,
			BCryptPasswordEncoder passwordEncoder, EmailProducer emailProducer, UserTypeRepository userTypeRepository,
			RoleRepository rolerepository, AgentRepository agentRepository, EmployeeRepository employeeRepository) {
		this.userRepository = userRepository;
		this.hotelRepository = hotelRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailProducer = emailProducer;
		this.userTypeRepository = userTypeRepository;
		this.rolerepository = rolerepository;
		this.agentRepository = agentRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public UserDTO registerUser(UserDTO user) {

		System.err.println("inside register click");
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
		System.err.println("userTypeName::" + userTypeName);

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
		case "STAFF":
			registeredUser = createEmployeeUser(user);
			break;
		case "SUPER_ADMIN":
			registeredUser = createSuperAdminUser(user);
			break;
		default:
			throw new InvalidUserTypeException("Invalid user type name: " + userTypeName);
		}

		System.err.println("registeredUser::" + registeredUser);
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
		
		System.err.println("inside created employee"); 

		Employee employee = employeeRepository.findById(user.getUserId()).orElseThrow(
				() -> new EmployeetRegistrationException("Employee not found with ID : " + user.getUserId()));

		EmployeeContactDetails contactDetails = employee.getContactDetails();
		String employeeEmail = contactDetails.getEmail();
		if (employeeEmail == null || employeeEmail.isEmpty()) {
			throw new MissingEmailException("No email ID found to send credentials.");
		}

		// 🔑 Check if username already exists
		Optional<UserAccount> existingUserOpt = userRepository.findByUsername(user.getUserName());
		if (existingUserOpt.isPresent()) {
			throw new UserRegistrationException("Username already exists.");
		}

		// ✅ Create new UserAccount without setting ID (let Hibernate generate it)
		UserAccount userAccount = new UserAccount();
		userAccount.setActive(true);
		userAccount.setUsername(user.getUserName());
		userAccount.setUserId(user.getUserId());

		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		userAccount.setPassword(encryptedPassword);

		// Validate userType
		UserType userTypeEntity = userTypeRepository.findById(user.getUserTypeId())
				.orElseThrow(() -> new InvalidUserTypeException("Invalid userTypeId: " + user.getUserTypeId()));
		userAccount.setUserType(userTypeEntity);

		// Handle roles
		List<Long> inputRoleIds = user.getUserRoleIds();
		if (inputRoleIds == null || inputRoleIds.isEmpty()) {
			// Assign default role = userTypeId
			Long defaultRoleId = user.getUserTypeId();
			Role defaultRole = rolerepository.findById(defaultRoleId)
					.orElseThrow(() -> new InvalidRoleException("No role found for userTypeId: " + defaultRoleId));

			userAccount.setUserRoles(Set.of(defaultRole));
		} else {
			List<Role> foundRoles = rolerepository.findAllById(inputRoleIds);

			if (foundRoles.size() != inputRoleIds.size()) {
				Set<Long> foundIds = foundRoles.stream().map(Role::getId).collect(Collectors.toSet());
				List<Long> missingIds = inputRoleIds.stream().filter(id -> !foundIds.contains(id))
						.collect(Collectors.toList());
				throw new InvalidRoleException("Invalid role IDs: " + missingIds);
			}

			userAccount.setUserRoles(new HashSet<>(foundRoles));
		}

		System.err.println("userAccount before save:: " + userAccount);

		// ✅ Save user
		UserAccount savedUser = userRepository.save(userAccount);
		System.err.println("savedUser for employee :: " + savedUser);

		// Prepare email list
		user.setUserMailIds(new String[] { employeeEmail });
		user.setUserId(savedUser.getUserId());

		System.err.println("Final UserDTO for employee :: " + user); 

		return user;

	}

	private UserDTO createAgentUser(UserDTO user) {

		Agent agent = agentRepository.findById(user.getUserId())
				.orElseThrow(() -> new AgentRegistrationException("Agent not found with ID: " + user.getUserId()));
		String personalEmail = agent.getPersonalEmail();
		if (personalEmail == null || personalEmail.isEmpty()) {
			throw new MissingEmailException("No email ID found to send credentials.");
		}

		// 🔑 Check if username already exists
		Optional<UserAccount> existingUserOpt = userRepository.findByUsername(user.getUserName());
		if (existingUserOpt.isPresent()) {
			throw new UserRegistrationException("Username already exists.");
		}

		// ✅ Create new UserAccount without setting ID (let Hibernate generate it)
		UserAccount userAccount = new UserAccount();
		userAccount.setActive(true);
		userAccount.setUsername(user.getUserName());
		userAccount.setUserId(user.getUserId());

		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		userAccount.setPassword(encryptedPassword);

		// Validate userType
		UserType userTypeEntity = userTypeRepository.findById(user.getUserTypeId())
				.orElseThrow(() -> new InvalidUserTypeException("Invalid userTypeId: " + user.getUserTypeId()));
		userAccount.setUserType(userTypeEntity);

		// Handle roles
		List<Long> inputRoleIds = user.getUserRoleIds();
		if (inputRoleIds == null || inputRoleIds.isEmpty()) {
			// Assign default role = userTypeId
			Long defaultRoleId = user.getUserTypeId();
			Role defaultRole = rolerepository.findById(defaultRoleId)
					.orElseThrow(() -> new InvalidRoleException("No role found for userTypeId: " + defaultRoleId));

			userAccount.setUserRoles(Set.of(defaultRole));
		} else {
			List<Role> foundRoles = rolerepository.findAllById(inputRoleIds);

			if (foundRoles.size() != inputRoleIds.size()) {
				Set<Long> foundIds = foundRoles.stream().map(Role::getId).collect(Collectors.toSet());
				List<Long> missingIds = inputRoleIds.stream().filter(id -> !foundIds.contains(id))
						.collect(Collectors.toList());
				throw new InvalidRoleException("Invalid role IDs: " + missingIds);
			}

			userAccount.setUserRoles(new HashSet<>(foundRoles));
		}

		// ✅ Save user
		UserAccount savedUser = userRepository.save(userAccount);
		// Prepare email list
		user.setUserMailIds(new String[] { personalEmail });
		user.setUserId(savedUser.getUserId());
		return user;
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

		List<String> mailIds = contactDetails.stream().flatMap(hotelContact -> hotelContact.getMailType().stream())
				.filter(mailType -> mailType.getMasterMailType().getId().equals(1l))
				.map(LinkedHotelContactDetailsMailType::getHotelContactDetails)
				.map(HotelContactDetails::getPersonalEmail).collect(Collectors.toList());

		if (mailIds == null || mailIds.size() == 0) {
			throw new MissingEmailException("No email ID found to send credentials.");
		}

		String[] mailIdArray = mailIds.toArray(new String[0]);

		UserAccount userAccount = new UserAccount();
		userAccount.setActive(true);
		userAccount.setId(user.getUserId());

		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		userAccount.setPassword(encryptedPassword);

		// userAccount.setUserRoles(roles);

		Optional<UserAccount> existing = userRepository.findByUsername(user.getUserName());
		if (existing.isPresent()) {
			throw new UserRegistrationException("Username already exists.");
		}

		userAccount.setUsername(user.getUserName());
		// userAccount.setUserType("HOTEL");

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
				List<Long> missingIds = inputRoleIds.stream().filter(id -> !foundIds.contains(id))
						.collect(Collectors.toList());
				throw new InvalidRoleException("Invalid role IDs: " + missingIds);
			}

			Set<Role> roles = new HashSet<>(foundRoles);
			userAccount.setUserRoles(roles);
		}

		// Save user

		UserAccount savedUser = userRepository.save(userAccount);

		System.err.println("Mail Ids :: " + Arrays.toString(mailIdArray));
		user.setUserMailIds(mailIdArray);
		user.setUserId(savedUser.getUserId());

		return user;
	}

	@Override
	public UserAccountsDTO checkRegisteredUserExist(@Valid Long userId) {
		// TODO Auto-generated method stub

		System.err.println("eneter checkRegisteredUserExist:::userId is ::" + userId);

		Long userAccountId = userRepository.fetchUserAccountId(userId);
		
		if (userAccountId == null || userAccountId == 0) {
			throw new UserRegistrationException("User is not Registered for id : " + userId);
		}

		UserAccount userAccount = userRepository.findById(userAccountId)
				.orElseThrow(() -> new RegisteredUserNotFoundException("Invalid User Account id :" + userAccountId));

		System.err.println("useracc:::" + userAccount);

		UserAccountsDTO userAccountsDTO = new UserAccountsDTO();
		userAccountsDTO.setUserId(userAccount.getUserId());
		userAccountsDTO.setUserName(userAccount.getUsername());
		userAccountsDTO.setUserRoles(null);

		return userAccountsDTO;
	}

}
