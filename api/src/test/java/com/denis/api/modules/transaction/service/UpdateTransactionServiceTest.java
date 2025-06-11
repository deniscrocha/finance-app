package com.denis.api.modules.transaction.service;

import com.denis.api.modules.category.service.CreateCategoryService;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.user.service.SearchUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.denis.api.modules.category.factory.CategoryFactory.getCategory;
import static com.denis.api.modules.transaction.factory.TransactionFactory.getTransaction;
import static com.denis.api.modules.transaction.factory.UpdateTransactionFactory.getUpdateTransactionRequest;
import static com.denis.api.modules.user.factory.UserFactory.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTransactionServiceTest {
    @InjectMocks
    private UpdateTransactionService updateTransactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private SearchUserService searchUserService;
    @Mock
    private GetTransactionService getTransactionService;
    @Mock
    private CreateCategoryService createCategoryService;

    @Test
    @DisplayName("Must update when everything is correctly")
    void mustUpdateWhenEverythingIsCorrectly(){
        var user = getUser();
        var category = getCategory();
        var transaction = getTransaction(user, category);
        var request = getUpdateTransactionRequest();
        Long id = 1L;

        when(searchUserService.searchUserByToken()).thenReturn(user);
        when(getTransactionService.getById(id)).thenReturn(transaction);
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(createCategoryService.getOrCreateCategoryByName(any())).thenReturn(category);

        var response = updateTransactionService.update(id, request);

        assertEquals(request.getAmount(), response.getAmount());
        assertEquals(request.getCategory(), response.getCategory());
        assertEquals(request.getType(), response.getType());
        assertEquals(request.getDate(), response.getDate());
        assertEquals(request.getDescription(), response.getDescription());
    }
}
