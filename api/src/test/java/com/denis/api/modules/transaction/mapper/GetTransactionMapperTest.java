package com.denis.api.modules.transaction.mapper;

import com.denis.api.modules.category.factory.CategoryFactory;
import com.denis.api.modules.transaction.factory.TransactionFactory;
import com.denis.api.modules.user.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetTransactionMapperTest {
    @Test
    @DisplayName("Must convert entity to get transaction response")
    void mustConvertEntityToGetTransactionResponse(){
        var user = UserFactory.getUser();
        var category = CategoryFactory.getCategory();
        var entity = TransactionFactory.getTransaction(user, category);

        var response = GetTransactionMapper.toResponse(entity);

        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getAmount(), response.getAmount());
        Assertions.assertEquals(entity.getCategory().getName(), response.getCategory());
        Assertions.assertEquals(entity.getDescription(), response.getDescription());
        Assertions.assertEquals(entity.getDate(), response.getDate());
        Assertions.assertEquals(entity.getType(), response.getType());
    }
}
