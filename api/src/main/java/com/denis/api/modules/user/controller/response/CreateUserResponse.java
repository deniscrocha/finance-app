package com.denis.api.modules.user.controller.response;

import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserResponse {
    private Long id;
    private String name;
    private String email;
}
