package com.denis.api.modules.category.service;

import com.denis.api.modules.category.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.denis.api.modules.category.factory.CategoryFactory.getCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryServiceTest {
    @InjectMocks
    private CreateCategoryService createCategoryService;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Must create a category if does not exists")
    void mustCreateACategoryIfDoesNotExists(){
        var category = getCategory();
        when(categoryRepository.findByName(any())).thenReturn(Optional.empty());
        when(categoryRepository.save(any())).thenReturn(category);
        String categoryName = "test";

        var createdCategory = createCategoryService.getOrCreateCategoryByName(categoryName);

        assertEquals(categoryName, createdCategory.getName());
    }

    @Test
    @DisplayName("Must return an existent category if exists")
    void mustReturnAnExistentCategoryIfExists(){
        var category = getCategory();
        when(categoryRepository.findByName(any())).thenReturn(Optional.of(category));

        var findedCategory = createCategoryService.getOrCreateCategoryByName(category.getName());


        assertEquals(category.getName(), findedCategory.getName());
        assertEquals(category.getId(), findedCategory.getId());
    }
}
