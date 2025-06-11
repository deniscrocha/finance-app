package com.denis.api.modules.user.service;

import com.denis.api.modules.user.controller.request.LoginUserRequest;
import com.denis.api.modules.user.controller.response.LoginUserResponse;
import com.denis.api.modules.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

import static com.denis.api.modules.user.error.UserErrorMessage.EMAIL_OR_PASSWORD_WRONG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final SearchUserService searchUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginUserResponse login(LoginUserRequest loginUserRequest){
        Optional<User> optUser = searchUserService.searchUserByEmail(loginUserRequest.getEmail());

        if (optUser.isEmpty() || !isLoginValid(loginUserRequest.getPassword(), optUser.get().getPassword())) {
            throw new ResponseStatusException(BAD_REQUEST, EMAIL_OR_PASSWORD_WRONG.getMessage());
        }

        User user = optUser.get();
        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("api")
                .subject(user.getName())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .id(user.getId().toString())
                .claim("email", user.getEmail())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return new LoginUserResponse(token, expiresIn);
    }

    private boolean isLoginValid(String password, String savedPassowrd) {
        return passwordEncoder.matches(password, savedPassowrd);
    }
}