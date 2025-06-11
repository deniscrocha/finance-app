package com.denis.api.modules.transaction.service;

import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.denis.api.modules.transaction.error.TransactionErrorMessage.TRANSACTION_DOESNT_EXISTS;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GetTransactionService {
    private final TransactionRepository transactionRepository;

    public Transaction getById(Long id){
       return transactionRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, TRANSACTION_DOESNT_EXISTS.getMessage()));
    }
}
