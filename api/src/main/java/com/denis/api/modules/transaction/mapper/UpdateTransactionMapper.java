package com.denis.api.modules.transaction.mapper;

import com.denis.api.modules.transaction.controller.response.UpdateTransactionResponse;
import com.denis.api.modules.transaction.domain.Transaction;

public class UpdateTransactionMapper {
    public static UpdateTransactionResponse toResponse(Transaction entity) {
        return UpdateTransactionResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .category(entity.getCategory().getName())
                .date(entity.getDate())
                .description(entity.getDescription())
                .type(entity.getType())
                .build();
    }
}
