package com.denis.api.modules.transaction.controller.response;

import com.denis.api.modules.transaction.domain.ExpenseType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateTransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate date;
    private ExpenseType type;
}
