package com.denis.api.modules.category.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.denis.api.modules.category.factory.CategoryFactory.getCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllCategoriesMapperTest {
    @Test
    @DisplayName("Must convert entity to response without a problem")
    void mustConvertEntityToResponseWithoutAProblem(){
        var entity = getCategory();

        var response = GetAllCategoriesMapper.toResponse(entity);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
    }
}
