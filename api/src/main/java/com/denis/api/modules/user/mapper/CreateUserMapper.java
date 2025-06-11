package com.denis.api.modules.user.mapper;

import com.denis.api.modules.user.controller.request.CreateUserRequest;
import com.denis.api.modules.user.controller.response.CreateUserResponse;
import com.denis.api.modules.user.domain.User;

public class CreateUserMapper {
    public static User toEntity(CreateUserRequest createUserRequest, String password) {
        return User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .password(password)
                .build();
    }

    public static CreateUserResponse toResponse(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
