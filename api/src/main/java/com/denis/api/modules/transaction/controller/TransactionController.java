package com.denis.api.modules.transaction.controller;

import com.denis.api.modules.transaction.controller.request.CreateTransactionRequest;
import com.denis.api.modules.transaction.controller.request.UpdateTransactionRequest;
import com.denis.api.modules.transaction.controller.response.CreateTransactionResponse;
import com.denis.api.modules.transaction.controller.response.GetTransactionResponse;
import com.denis.api.modules.transaction.controller.response.UpdateTransactionResponse;
import com.denis.api.modules.transaction.service.CreateTransactionService;
import com.denis.api.modules.transaction.service.DeleteTransactionService;
import com.denis.api.modules.transaction.service.GetAllTransactionsService;
import com.denis.api.modules.transaction.service.UpdateTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final GetAllTransactionsService getAllTransactionsService;
    private final CreateTransactionService createTransactionService;
    private final UpdateTransactionService updateTransactionService;
    private final DeleteTransactionService deleteTransactionService;

    @GetMapping
    public List<GetTransactionResponse> getAllUserTransaction(){
        return getAllTransactionsService.getAllByUser();

    }
    @PostMapping
    @ResponseStatus(CREATED)
    public CreateTransactionResponse createTransaction(@Valid @RequestBody CreateTransactionRequest request){
        return createTransactionService.create(request);
    }
    @PutMapping("/{id}")
    public UpdateTransactionResponse updateTransaction(@Valid @RequestBody UpdateTransactionRequest request, @PathVariable Long id){
        return updateTransactionService.update(id, request);

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id){
        deleteTransactionService.delete(id);
    }
}
