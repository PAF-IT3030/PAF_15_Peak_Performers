package com.spring.social_media_application.service.authentication;

import com.spring.social_media_application.dto.authentication.request.LoginRequest;
import com.spring.social_media_application.dto.authentication.request.SignupRequest;
import com.spring.social_media_application.dto.authentication.response.JwtResponse;
import com.spring.social_media_application.dto.authentication.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<JwtResponse> authenticateUserDetails(LoginRequest loginRequest);

    ResponseEntity<MessageResponse> registerUserDetails(SignupRequest signUpRequest);
}
