package com.denis.api.modules.transaction.service;

import com.denis.api.modules.category.domain.Category;
import com.denis.api.modules.category.service.CreateCategoryService;
import com.denis.api.modules.transaction.controller.request.CreateTransactionRequest;
import com.denis.api.modules.transaction.controller.response.CreateTransactionResponse;
import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.user.domain.User;
import com.denis.api.modules.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.denis.api.modules.transaction.mapper.CreateTransactionMapper.toEntity;
import static com.denis.api.modules.transaction.mapper.CreateTransactionMapper.toResponse;

@Service
@RequiredArgsConstructor
public class CreateTransactionService {
    private final TransactionRepository transactionRepository;
    private final SearchUserService searchUserService;
    private final CreateCategoryService createCategoryService;

    public CreateTransactionResponse create(CreateTransactionRequest request) {
        User user = searchUserService.searchUserByToken();
        Transaction transaction = toEntity(request);
        Category category = createCategoryService.getOrCreateCategoryByName(request.getCategory());
        transaction.setCategory(category);
        transaction.setUser(user);
        return toResponse(transactionRepository.save(transaction));
    }
}
