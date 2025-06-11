package com.denis.api.modules.transaction.service;

import com.denis.api.modules.transaction.domain.Transaction;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.transaction.util.CheckIfUserIsOwnerUtil;
import com.denis.api.modules.user.domain.User;
import com.denis.api.modules.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTransactionService {
    private final TransactionRepository transactionRepository;
    private final SearchUserService searchUserService;
    private final GetTransactionService getTransactionService;

    public void delete(Long id) {
        User user = searchUserService.searchUserByToken();
        Transaction transaction = getTransactionService.getById(id);
        CheckIfUserIsOwnerUtil.check(user, transaction);

        transactionRepository.delete(transaction);
    }
}
