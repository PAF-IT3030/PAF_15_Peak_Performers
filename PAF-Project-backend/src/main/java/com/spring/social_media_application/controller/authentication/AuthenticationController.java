package com.spring.social_media_application.controller.authentication;

import com.spring.social_media_application.dto.authentication.request.LoginRequest;
import com.spring.social_media_application.dto.authentication.request.SignupRequest;
import com.spring.social_media_application.dto.authentication.response.JwtResponse;
import com.spring.social_media_application.dto.authentication.response.MessageResponse;
import com.spring.social_media_application.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return authenticationService.authenticateUserDetails(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authenticationService.registerUserDetails(signUpRequest);
	}
}
