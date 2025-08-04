package com.choosenfly.hotelbookingsystem.auth.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private RoleRepository roleRepo;
//
//    @Autowired
//    private UserRoleRepository userRoleRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public void registerUser(RegisterRequest req) {
//        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//
//        User user = new User();
//        user.setUsername(req.getUsername());
//        user.setPassword(passwordEncoder.encode(req.getPassword()));
//        userRepo.save(user);
//
//        Role role = roleRepo.findByName(req.getRole())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//
//        UserRole userRole = new UserRole(user, role);
//        userRoleRepo.save(userRole);
//    }
//
//    public LoginResponse authenticateUser(LoginRequest req) {
//        User user = userRepo.findByUsername(req.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//            throw new BadCredentialsException("Invalid credentials");
//        }
//
//        List<String> roles = user.getUserRoles().stream()
//                .map(userRole -> userRole.getRole().getName().name())
//                .collect(Collectors.toList());
//
//        String token = jwtUtil.generateToken(user.getUsername(), roles);
//
//        return new LoginResponse(token, user.getUsername(), roles);
//    }
}
