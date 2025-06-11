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
import static com.denis.api.modules.transaction.factory.CreateTransactionFactory.getCreateTransactionRequest;
import static com.denis.api.modules.transaction.factory.TransactionFactory.getTransaction;
import static com.denis.api.modules.user.factory.UserFactory.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTransactionServiceTest {
    @InjectMocks
    private CreateTransactionService createTransactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private SearchUserService searchUserService;
    @Mock
    private CreateCategoryService createCategoryService;

    @Test
    @DisplayName("Must create transaction without a problem")
    void mustCreateTransactionWithoutAProblem(){
        var user = getUser();
        var request = getCreateTransactionRequest();
        var category = getCategory();
        var entity = getTransaction(user, category);
        when(searchUserService.searchUserByToken()).thenReturn(user);
        when(transactionRepository.save(any())).thenReturn(entity);
        when(createCategoryService.getOrCreateCategoryByName(any())).thenReturn(category);

        var response = createTransactionService.create(request);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getType(), response.getType());
        assertEquals(entity.getDate(), response.getDate());
        assertEquals(entity.getDescription(), response.getDescription());
        assertEquals(entity.getAmount(), response.getAmount());
        assertEquals(entity.getCategory().getName(), response.getCategory());
    }
}
