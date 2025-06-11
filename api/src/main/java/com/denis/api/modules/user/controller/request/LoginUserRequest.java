package com.denis.api.modules.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginUserRequest {
    @Email(message = "Email must be valid")
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
}