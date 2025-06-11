package com.denis.api.modules.user.controller;

import com.denis.api.modules.user.controller.request.CreateUserRequest;
import com.denis.api.modules.user.controller.request.LoginUserRequest;
import com.denis.api.modules.user.controller.response.CreateUserResponse;
import com.denis.api.modules.user.controller.response.LoginUserResponse;
import com.denis.api.modules.user.service.CreateUserService;
import com.denis.api.modules.user.service.LoginUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        return createUserService.createUser(createUserRequest);
    }
}
