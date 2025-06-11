package com.denis.api.modules.transaction.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionErrorMessage {
    TRANSACTION_DOESNT_EXISTS("Transaction doesn't exists"),
    USER_ARE_NOT_THE_OWNER("User doesn't own this transaction");
    private final String message;
}
