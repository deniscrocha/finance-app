package com.denis.api.modules.transaction.controller.request;

import com.denis.api.modules.transaction.domain.ExpenseType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateTransactionRequest {
    @NotNull(message = "Amount cannot be null")
    @Digits(fraction = 2, integer = 12, message = "Amount must have only 2 decimals houses, and max 12 integers houses")
    private BigDecimal amount;
    @NotBlank(message = "Category cannot be null")
    private String category;
    private String description;
    @Past(message = "Date must be on past")
    @NotNull(message = "Date cannot be null")
    private LocalDate date;
    @NotNull(message = "Type cannot be null")
    private ExpenseType type;
}