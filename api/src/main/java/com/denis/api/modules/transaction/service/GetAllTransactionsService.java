package com.denis.api.modules.transaction.service;


import com.denis.api.modules.transaction.controller.response.GetTransactionResponse;
import com.denis.api.modules.transaction.mapper.GetTransactionMapper;
import com.denis.api.modules.transaction.repository.TransactionRepository;
import com.denis.api.modules.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTransactionsService {

    private final TransactionRepository transactionRepository;
    private final SearchUserService searchUserService;

    public List<GetTransactionResponse> getAllByUser() {
        var user = searchUserService.searchUserByToken();
        return transactionRepository.findByUser(user)
                .stream()
                .map(GetTransactionMapper::toResponse)
                .toList();
    }
}
