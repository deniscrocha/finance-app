package com.denis.api.modules.transaction.factory;

import com.denis.api.modules.transaction.controller.request.CreateTransactionRequest;
import com.denis.api.modules.transaction.controller.response.CreateTransactionResponse;
import com.denis.api.modules.transaction.domain.ExpenseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransactionFactory {
    public static CreateTransactionResponse getCreateTransactionResponse(){
        return CreateTransactionResponse.builder()
                .id(1L)
                .type(ExpenseType.EXPENSE)
                .description("test")
                .date(LocalDate.of(2025, 1, 1))
                .category("test")
                .amount(BigDecimal.valueOf(10.00))
                .build();
    }
    public static CreateTransactionRequest getCreateTransactionRequest(){
        return CreateTransactionRequest.builder()
                .amount(BigDecimal.valueOf(10.00))
                .category("test")
                .date(LocalDate.of(2025, 1, 1))
                .description("test")
                .type(ExpenseType.EXPENSE)
                .build();
    }
}