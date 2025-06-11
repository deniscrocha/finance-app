package com.denis.api.modules.transaction.service;

import com.denis.api.modules.category.factory.CategoryFactory;
import com.denis.api.modules.transaction.error.TransactionErrorMessage;
import com.denis.api.modules.transaction.factory.TransactionFactory;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.denis.api.modules.category.factory.CategoryFactory.getCategory;
import static com.denis.api.modules.transaction.error.TransactionErrorMessage.TRANSACTION_DOESNT_EXISTS;
import static com.denis.api.modules.transaction.factory.TransactionFactory.getTransaction;
import static com.denis.api.modules.user.factory.UserFactory.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTransactionServiceTest {
    @InjectMocks
    private GetTransactionService getTransactionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Must return a transaction when id is correctly")
    void mustReturnATransactionWhenIdIsCorrectly(){
        var user = getUser();
        var category = getCategory();
        var transaction = getTransaction(user, category);

        when(transactionRepository.findById(any())).thenReturn(Optional.of(transaction));

        var entity = getTransactionService.getById(transaction.getId());

        assertEquals(transaction.getId(), entity.getId());
        assertEquals(transaction.getDate(), entity.getDate());
        assertEquals(transaction.getDescription(), entity.getDescription());
        assertEquals(transaction.getType(), entity.getType());
        assertEquals(transaction.getUser().getId(), entity.getUser().getId());
        assertEquals(transaction.getCategory().getName(), entity.getCategory().getName());
    }
    @Test
    @DisplayName("Must throw an error when id does not exists")
    void mustThrowAnErrorWhenIdDoesNotExists(){
        Long id = 1L;
        when(transactionRepository.findById(any())).thenReturn(Optional.empty());

        var error = assertThrows(ResponseStatusException.class, () -> getTransactionService.getById(id));
        assertEquals(TRANSACTION_DOESNT_EXISTS.getMessage(), error.getReason());
    }
}