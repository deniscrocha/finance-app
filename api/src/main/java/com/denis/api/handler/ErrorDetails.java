package com.denis.api.handler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDetails {

    private String field;
    private String message;
}