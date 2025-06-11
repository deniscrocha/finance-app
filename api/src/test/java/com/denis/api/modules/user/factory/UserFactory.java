package com.denis.api.modules.user.factory;

import com.denis.api.modules.user.controller.request.CreateUserRequest;
import com.denis.api.modules.user.controller.response.CreateUserResponse;
import com.denis.api.modules.user.domain.User;

public class UserFactory {
    public static User getUser(){
        return User.builder()
                .id(1L)
                .name("test")
                .password("test")
                .email("test@test.com")
                .build();
    }
    public static CreateUserResponse getCreateUserResponse(){
        return CreateUserResponse.builder()
                .id(1L)
                .name("test")
                .email("test@test.com")
                .build();
    }
    public static CreateUserRequest getCreateUserRequest(){
        return CreateUserRequest.builder()
                .name("test")
                .password("test")
                .email("test@test.com")
                .build();
    }
}