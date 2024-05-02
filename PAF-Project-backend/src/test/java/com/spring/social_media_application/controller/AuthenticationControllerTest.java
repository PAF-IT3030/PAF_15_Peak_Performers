package com.spring.social_media_application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.social_media_application.controller.authentication.AuthenticationController;
import com.spring.social_media_application.dto.authentication.request.LoginRequest;
import com.spring.social_media_application.service.authentication.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    private static final String END_POINT_PATH = "/api/auth";
    @InjectMocks
    private AuthenticationController authenticationController;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AuthenticationService authenticationService;
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    /**
     *
     * @param obj - required data for testing
     * @return JasonObject this is return json
     * @throws Exception exception
     */
    private static String asJsonString(Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }

    /**
     * This method test when invalid DTO pass to save user details then return bad request
     *
     */
    @Test
    void testSaveAuthenticationService_WhenDtoIsInValid_ReturnBadRequest() throws Exception {
        // Given
        LoginRequest loginRequest = new LoginRequest(
                "aathif", ""
        );
        // Act
        ResultActions result = mockMvc.perform(post(END_POINT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginRequest)));
        // Assert
        result.andExpect(status().isNotFound());
    }
    /**
     * This method test when null value pass to save user details then return bad request
     *
     */
    @Test
    void testSaveAuthenticationService_WhenDtoIsNull_ReturnBadRequest() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(post(END_POINT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)));
        // Assert
        result.andExpect(status().isNotFound());
    }
}
