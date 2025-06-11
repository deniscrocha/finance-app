package com.denis.api.modules.user.service;

import com.denis.api.modules.user.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;

import static com.denis.api.modules.user.factory.UserFactory.getUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchUserServiceTest {
    @InjectMocks
    private SearchUserService searchUserService;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Must return an optional with value when find an user by email")
    void mustReturnAnOptionalWithValueWhenFindAnUserByEmail(){
        var expected = getUser();
        String email = expected.getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(expected));

        var user = searchUserService.searchUserByEmail(email).get();

        assertEquals(expected.getId(), user.getId());
        assertEquals(expected.getName(), user.getName());
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getPassword(), user.getPassword());
    }

    @Test
    @DisplayName("Must return an optional without value when not find an user by email")
    void mustReturnAnOptionalWithoutValueWhenNotFindAnUserByEmail(){
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        var user = searchUserService.searchUserByEmail("");

        assertFalse(user.isPresent());
    }

    @Test
    @DisplayName("Must return an user when find user by authentication")
    void mustReturnAnUserWhenFindUserByAuthentication(){
        var user = getUser();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        HashMap<String, Object> claims = new HashMap<>();
        HashMap<String, Object> headers = new HashMap<>();
        claims.put("jti", user.getId());
        headers.put("alg", "HS256");
        Jwt jwt = new Jwt("test", Instant.now(), Instant.now().plusSeconds(600L), headers, claims);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(authentication.getCredentials()).thenReturn(jwt);
        SecurityContextHolder.setContext(securityContext);

        var response = searchUserService.searchUserByToken();

        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getPassword(), response.getPassword());

    }

    @Test
    @DisplayName("Must throw an error when not found an user by authentication")
    void mustThrowAnErrorWhenNotFoundAnUserByAuthentication(){
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        HashMap<String, Object> claims = new HashMap<>();
        HashMap<String, Object> headers = new HashMap<>();
        claims.put("jti", 5L);
        headers.put("alg", "HS256");
        Jwt jwt = new Jwt("test", Instant.now(), Instant.now().plusSeconds(600L), headers, claims);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(authentication.getCredentials()).thenReturn(jwt);
        SecurityContextHolder.setContext(securityContext);

        assertThrows(ResponseStatusException.class, () -> searchUserService.searchUserByToken());
    }

}
