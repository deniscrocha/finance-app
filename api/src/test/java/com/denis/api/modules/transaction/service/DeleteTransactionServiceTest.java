package com.denis.api.modules.transaction.service;

import com.denis.api.modules.category.factory.CategoryFactory;
import com.denis.api.modules.transaction.factory.TransactionFactory;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.user.factory.UserFactory;
import com.denis.api.modules.user.service.SearchUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteTransactionServiceTest {
    @InjectMocks
    private DeleteTransactionService deleteTransactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private SearchUserService searchUserService;
    @Mock
    private GetTransactionService getTransactionService;

    @Test
    @DisplayName("Must delete a transaction without a problem")
    void mustDeleteATransactionWithoutAProblem(){
        var user = UserFactory.getUser();
        var category = CategoryFactory.getCategory();
        var transaction = TransactionFactory.getTransaction(user, category);
        Long id = 1L;
        when(searchUserService.searchUserByToken()).thenReturn(user);
        when(getTransactionService.getById(any())).thenReturn(transaction);

        deleteTransactionService.delete(id);

        verify(transactionRepository, times(1)).delete(any());
    }
}
