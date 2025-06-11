package com.denis.api.modules.transaction.factory;

import com.denis.api.modules.category.domain.Category;
import com.denis.api.modules.transaction.domain.ExpenseType;
import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.user.domain.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionFactory {
    public static Transaction getTransaction(User user, Category category){
        return Transaction.builder()
                .id(1L)
                .type(ExpenseType.EXPENSE)
                .date(LocalDate.of(2025, 1, 1))
                .user(user)
                .description("test")
                .category(category)
                .amount(BigDecimal.valueOf(10.00))
                .build();
    }
}
