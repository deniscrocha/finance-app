package com.denis.api.modules.category.factory;

import com.denis.api.modules.category.domain.Category;

public class CategoryFactory {
    public static Category getCategory(){
        return Category.builder().id(1L).name("test").build();
    }
}
