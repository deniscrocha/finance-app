package com.denis.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter

public class ErrorResponse {
    private String message;
    private int statusCode;
    private List<ErrorDetails> errors;
}
