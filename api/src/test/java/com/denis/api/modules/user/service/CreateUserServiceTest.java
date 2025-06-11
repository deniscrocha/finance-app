package com.denis.api.modules.user.service;

import com.denis.api.modules.user.factory.UserFactory;
import com.denis.api.modules.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static com.denis.api.modules.user.factory.UserFactory.getCreateUserRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {
    @InjectMocks
    private CreateUserService createUserService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Must throw an error when email already exists")
    void mustThrowAnErrorWhenEmailAlreadyExists(){
        var request = getCreateUserRequest();
        when(passwordEncoder.encode(any())).thenReturn("encoded");
        when(userRepository.existsByEmail(any())).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> createUserService.createUser(request));
    }

    @Test
    @DisplayName("Must create an user when email is not used")
    void mustCreateAnUserWhenEmailIsNotUsed(){
        var request = getCreateUserRequest();
        when(passwordEncoder.encode(any())).thenReturn("encoded");
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(UserFactory.getUser());

        var response = createUserService.createUser(request);

        assertEquals(request.getName(), response.getName());
        assertEquals(request.getEmail(), response.getEmail());
    }
}
