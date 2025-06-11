package com.denis.api.modules.transaction.service;

import com.denis.api.modules.category.domain.Category;
import com.denis.api.modules.category.service.CreateCategoryService;
import com.denis.api.modules.transaction.controller.request.UpdateTransactionRequest;
import com.denis.api.modules.transaction.controller.response.UpdateTransactionResponse;
import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.transaction.util.CheckIfUserIsOwnerUtil;
import com.denis.api.modules.user.domain.User;
import com.denis.api.modules.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.denis.api.modules.transaction.mapper.UpdateTransactionMapper.toResponse;

@Service
@RequiredArgsConstructor
public class UpdateTransactionService {
    private final TransactionRepository transactionRepository;
    private final SearchUserService searchUserService;
    private final GetTransactionService getTransactionService;
    private final CreateCategoryService createCategoryService;

    public UpdateTransactionResponse update(Long id, UpdateTransactionRequest request) {
        User user = searchUserService.searchUserByToken();
        Transaction transaction = getTransactionService.getById(id);
        CheckIfUserIsOwnerUtil.check(user, transaction);
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        Category category = createCategoryService.getOrCreateCategoryByName(request.getCategory());
        transaction.setCategory(category);
        transaction.setDescription(request.getDescription());
        transaction.setType(request.getType());
        return toResponse(transactionRepository.save(transaction));
    }
}
