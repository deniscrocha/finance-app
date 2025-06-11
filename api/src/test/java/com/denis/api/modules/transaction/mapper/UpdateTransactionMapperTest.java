package com.denis.api.modules.transaction.mapper;

import com.denis.api.modules.category.factory.CategoryFactory;
import com.denis.api.modules.transaction.factory.TransactionFactory;
import com.denis.api.modules.user.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdateTransactionMapperTest {
    @Test
    @DisplayName("Must convert entity to update transaction response")
    void mustConvertEntityToUpdateTransactionResponse(){
        var user = UserFactory.getUser();
        var category = CategoryFactory.getCategory();
        var entity = TransactionFactory.getTransaction(user, category);

        var response = UpdateTransactionMapper.toResponse(entity);

        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getDate(), response.getDate());
        Assertions.assertEquals(entity.getCategory().getName(), response.getCategory());
        Assertions.assertEquals(entity.getDescription(), response.getDescription());
        Assertions.assertEquals(entity.getType(), response.getType());
        Assertions.assertEquals(entity.getAmount(), response.getAmount());
    }
}
