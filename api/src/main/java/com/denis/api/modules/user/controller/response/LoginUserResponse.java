package com.denis.api.modules.user.controller.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginUserResponse {
    private String token;
    private Long expiresIn;
}
