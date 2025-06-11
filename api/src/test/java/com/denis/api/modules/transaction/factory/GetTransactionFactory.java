package com.denis.api.modules.transaction.factory;

import com.denis.api.modules.transaction.controller.response.GetTransactionResponse;
import com.denis.api.modules.transaction.domain.ExpenseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GetTransactionFactory {
    public static GetTransactionResponse getGetTransactionResponse(){
        return GetTransactionResponse.builder()
                .id(1L)
                .type(ExpenseType.EXPENSE)
                .description("test")
                .date(LocalDate.of(2025, 1, 1))
                .category("test")
                .amount(BigDecimal.valueOf(10.00))
                .build();
    }
}
