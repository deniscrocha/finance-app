package com.denis.api.modules.transaction.mapper;

import com.denis.api.modules.transaction.controller.response.GetTransactionResponse;
import com.denis.api.modules.transaction.domain.Transaction;

public class GetTransactionMapper {
    public static GetTransactionResponse toResponse(Transaction transaction) {
        return GetTransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .category(transaction.getCategory().getName())
                .date(transaction.getDate())
                .description(transaction.getDescription())
                .type(transaction.getType())
                .build();
    }
}
