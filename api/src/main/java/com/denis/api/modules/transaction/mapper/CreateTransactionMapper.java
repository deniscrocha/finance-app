package com.denis.api.modules.transaction.mapper;

import com.denis.api.modules.transaction.controller.request.CreateTransactionRequest;
import com.denis.api.modules.transaction.controller.response.CreateTransactionResponse;
import com.denis.api.modules.transaction.domain.Transaction;

public class CreateTransactionMapper {
    public static Transaction toEntity(CreateTransactionRequest request) {
        return Transaction.builder()
                .amount(request.getAmount())
                .date(request.getDate())
                .description(request.getDescription())
                .type(request.getType())
                .build();
    }

    public static CreateTransactionResponse toResponse(Transaction entity) {
        return CreateTransactionResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .category(entity.getCategory().getName())
                .date(entity.getDate())
                .description(entity.getDescription())
                .type(entity.getType())
                .build();
    }
}
