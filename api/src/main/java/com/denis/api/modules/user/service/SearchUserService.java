package com.denis.api.modules.user.service;

import com.denis.api.modules.user.domain.User;
import com.denis.api.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.denis.api.modules.user.error.UserErrorMessage.USER_DOESNT_EXISTS;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class SearchUserService {

    private final UserRepository userRepository;

    public Optional<User> searchUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User searchUserByToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return userRepository.findById(Long.valueOf(jwt.getId()))
                .orElseThrow(()-> new ResponseStatusException(FORBIDDEN, USER_DOESNT_EXISTS.getMessage()));
    }
}
