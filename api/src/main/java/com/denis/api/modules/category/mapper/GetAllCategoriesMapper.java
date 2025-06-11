package com.denis.api.modules.category.mapper;

import com.denis.api.modules.category.controller.response.GetAllCategoriesResponse;
import com.denis.api.modules.category.domain.Category;

public class GetAllCategoriesMapper {
    public static GetAllCategoriesResponse toResponse(Category entity) {
        return GetAllCategoriesResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
