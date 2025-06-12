package com.denis.api.modules.user.controller;

import com.denis.api.modules.user.controller.request.CreateUserRequest;
import com.denis.api.modules.user.controller.request.LoginUserRequest;
import com.denis.api.modules.user.controller.response.CreateUserResponse;
import com.denis.api.modules.user.controller.response.LoginUserResponse;
import com.denis.api.modules.user.service.CreateUserService;
import com.denis.api.modules.user.service.LoginUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserService createUserService;
    private final LoginUserService loginUserService;

    @PostMapping("/login")
    public LoginUserResponse login(@Valid @RequestBody LoginUserRequest request){
        return loginUserService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return createUserService.createUser(createUserRequest);
    }

    @GetMapping("/validate")
    @ResponseStatus(ACCEPTED)
    public void validateToken(){
    }
}
