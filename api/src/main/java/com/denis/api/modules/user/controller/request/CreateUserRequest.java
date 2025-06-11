package com.denis.api.modules.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserRequest {
    @NotBlank(message = "Name cannot be null")
    @Size(min = 5, max = 100, message = "Name must have betwwen 5 and 100 chars")
    private String name;
    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email cannot have more than 255 chars")
    @NotNull(message = "Email cannot be null")
    private String email;
    @Size(min = 4, max = 100, message = "Password must have between 4 and 100 chars")
    @NotBlank(message = "Password cannot be null")
    private String password;
}