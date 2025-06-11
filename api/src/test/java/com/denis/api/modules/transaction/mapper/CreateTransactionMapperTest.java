package com.denis.api.modules.transaction.mapper;

import com.denis.api.modules.category.domain.Category;
import com.denis.api.modules.category.factory.CategoryFactory;
import com.denis.api.modules.transaction.factory.CreateTransactionFactory;
import com.denis.api.modules.transaction.factory.TransactionFactory;
import com.denis.api.modules.user.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTransactionMapperTest {
    @Test
    @DisplayName("Must convert create transaction request to entity")
    void mustConvertCreateTransactionRequestToEntity(){
        var request = CreateTransactionFactory.getCreateTransactionRequest();

        var entity = CreateTransactionMapper.toEntity(request);

        Assertions.assertEquals(request.getAmount(), entity.getAmount());
        Assertions.assertEquals(request.getType(), entity.getType());
        Assertions.assertEquals(request.getDate(), entity.getDate());
        Assertions.assertEquals(request.getDescription(), entity.getDescription());
    }
    @Test
    @DisplayName("Must convert entity to create transaction response")
    void mustConvertEntityToCreateTransactionResponse(){
        var user = UserFactory.getUser();
        var category = CategoryFactory.getCategory();
        var entity = TransactionFactory.getTransaction(user, category);

        var response = CreateTransactionMapper.toResponse(entity);


        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getAmount(), response.getAmount());
        Assertions.assertEquals(entity.getType(), response.getType());
        Assertions.assertEquals(entity.getDate(), response.getDate());
        Assertions.assertEquals(entity.getDescription(), response.getDescription());
        Assertions.assertEquals(entity.getCategory().getName(), response.getCategory());
    }
}