package com.denis.api.modules.user.service;

import com.denis.api.modules.user.controller.request.CreateUserRequest;
import com.denis.api.modules.user.controller.response.CreateUserResponse;
import com.denis.api.modules.user.domain.User;
import com.denis.api.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.denis.api.modules.user.error.UserErrorMessage.EMAIL_ALREADY_EXISTS;
import static com.denis.api.modules.user.mapper.CreateUserMapper.toEntity;
import static com.denis.api.modules.user.mapper.CreateUserMapper.toResponse;

@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        String password = passwordEncoder.encode(createUserRequest.getPassword());
        if(userRepository.existsByEmail(createUserRequest.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMAIL_ALREADY_EXISTS.getMessage());
        User user = toEntity(createUserRequest, password);
        return toResponse(userRepository.save(user));
    }
}
