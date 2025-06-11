package com.denis.api.modules.transaction.domain;

import com.denis.api.modules.category.domain.Category;
import com.denis.api.modules.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String description;
    private LocalDate date;
    @Enumerated(STRING)
    private ExpenseType type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
