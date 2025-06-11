package com.denis.api.modules.transaction.service;

import com.denis.api.modules.category.factory.CategoryFactory;
import com.denis.api.modules.transaction.controller.response.GetTransactionResponse;
import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.transaction.factory.TransactionFactory;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.user.factory.UserFactory;
import com.denis.api.modules.user.service.SearchUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.denis.api.modules.category.factory.CategoryFactory.getCategory;
import static com.denis.api.modules.user.factory.UserFactory.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllTransactionsServiceTest {
    @InjectMocks
    private GetAllTransactionsService getAllTransactionsService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private SearchUserService searchUserService;

    @Test
    @DisplayName("must return all users transaction")
    void mustReturnAllUsersTransaction(){
        var user = getUser();
        var category = getCategory();
        var transactionsList = List.of(TransactionFactory.getTransaction(user, getCategory()));
        when(searchUserService.searchUserByToken()).thenReturn(user);
        when(transactionRepository.findByUser(any())).thenReturn(transactionsList);

        var response = getAllTransactionsService.getAllByUser();

        assertEquals(transactionsList.size(), response.size());
        assertEquals(transactionsList.getFirst().getId(), response.getFirst().getId());
        assertEquals(transactionsList.getFirst().getAmount(), response.getFirst().getAmount());
        assertEquals(transactionsList.getFirst().getType(), response.getFirst().getType());
        assertEquals(transactionsList.getFirst().getCategory().getName(), response.getFirst().getCategory());
        assertEquals(transactionsList.getFirst().getDescription(), response.getFirst().getDescription());
        assertEquals(transactionsList.getFirst().getDate(), response.getFirst().getDate());
        Assertions.assertInstanceOf(GetTransactionResponse.class, response.getFirst());
    }
    @Test
    @DisplayName("Must return an empty list when user does not have transactions")
    void mustReturnAnEmptyListWhenUserDoesNotHaveTransactions(){
        var user = getUser();
        List<Transaction> transactionsList = List.of();
        when(searchUserService.searchUserByToken()).thenReturn(user);
        when(transactionRepository.findByUser(any())).thenReturn(transactionsList);

        var response = getAllTransactionsService.getAllByUser();

        assertEquals(0, response.size());
    }
}
