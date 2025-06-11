package com.denis.api.modules.transaction.factory;

import com.denis.api.modules.transaction.controller.request.UpdateTransactionRequest;
import com.denis.api.modules.transaction.controller.response.UpdateTransactionResponse;
import com.denis.api.modules.transaction.domain.ExpenseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateTransactionFactory {
    public static UpdateTransactionRequest getUpdateTransactionRequest(){
        return UpdateTransactionRequest.builder()
                .type(ExpenseType.EXPENSE)
                .date(LocalDate.of(2025, 1, 1))
                .description("test")
                .category("test")
                .amount(BigDecimal.valueOf(10.00))
                .build();
    }
    public static UpdateTransactionResponse getUpdateTransactionResponse(){
        return UpdateTransactionResponse.builder()
                .id(1L)
                .type(ExpenseType.EXPENSE)
                .date(LocalDate.of(2025, 1, 1))
                .description("test")
                .category("test")
                .amount(BigDecimal.valueOf(10.00))
                .build();
    }
}
