package com.denis.api.modules.user.service;

import com.denis.api.modules.user.controller.request.LoginUserRequest;
import com.denis.api.modules.user.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginUserServiceTest {
    @InjectMocks
    private LoginUserService loginUserService;
    @Mock
    private SearchUserService searchUserService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtEncoder jwtEncoder;

    @Test
    @DisplayName("Must throw an error when email does not exists")
    void mustThrowAnErrorWhenEmailDoesNotExists(){
        when(searchUserService.searchUserByEmail(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> loginUserService.login(getRequest()));
    }
    @Test
    @DisplayName("Must throw an error when password is wrong")
    void mustThrowAnErrorWhenPasswordIsWrong(){
        when(searchUserService.searchUserByEmail(any())).thenReturn(Optional.of(UserFactory.getUser()));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> loginUserService.login(getRequest()));
    }
    @Test
    @DisplayName("Must return token an expiration time when everything is right")
    void mustReturnTokenAnExpirationTimeWhenEverythingIsRight(){
        var request = getRequest();
        String token = "token";
        Jwt mockJwt = mock(Jwt.class);
        when(mockJwt.getTokenValue()).thenReturn(token);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(searchUserService.searchUserByEmail(any())).thenReturn(Optional.of(UserFactory.getUser()));
        when(jwtEncoder.encode(any())).thenReturn(mockJwt);

        var response = loginUserService.login(request);

        assertEquals(token, response.getToken());
        assertEquals(600L, response.getExpiresIn());
    }

    private LoginUserRequest getRequest(){
        return LoginUserRequest.builder()
                .email("test@email.com")
                .password("test")
                .build();
    }
}
