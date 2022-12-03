package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import com.web.dictionaryservice.dictionaryservicewebimplementation.configs.jwt.JwtUtils;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.ERole;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Role;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.JwtResponse;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.LoginRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.MessageResponse;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.SignupRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.RoleRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        //Username checking
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        //Username validation
        if (signupRequest.getUsername() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is null"));
        }
        if (signupRequest.getUsername().length() > 16) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is too long"));
        }
        if (signupRequest.getUsername().length() < 1) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is empty"));
        }

        //Email checking
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        //Email Validation
        if (signupRequest.getEmail() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is null"));
        }
        if (signupRequest.getEmail().length() > 20) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is too long"));
        }
        if (signupRequest.getEmail().length() < 1) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is empty"));
        }

        //Password validation
        if (signupRequest.getPassword() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Password is null"));
        }
        if (signupRequest.getPassword().length() < 1) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Password is empty"));
        }

        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository
                                .findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
