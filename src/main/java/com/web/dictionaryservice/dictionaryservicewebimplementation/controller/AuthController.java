package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.LoginRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.service.AuthService;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
		return authService.authUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
		return authService.registerUser(signupRequest);
	}
}
