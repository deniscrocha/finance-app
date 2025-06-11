package com.denis.api.modules.transaction.repository;

import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
