package com.denis.api.modules.user.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorMessage {
    EMAIL_ALREADY_EXISTS("Email already in use"),
    EMAIL_OR_PASSWORD_WRONG("Email or password are wrong!"),
    USER_DOESNT_EXISTS("User doesn't exists");

    private final String message;
}