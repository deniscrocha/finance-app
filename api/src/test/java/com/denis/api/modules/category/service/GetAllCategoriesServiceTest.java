package com.denis.api.modules.category.service;

import com.denis.api.modules.category.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.denis.api.modules.category.factory.CategoryFactory.getCategory;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllCategoriesServiceTest {
    @InjectMocks
    private GetAllCategoriesService getAllCategoriesService;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("must return an empty list when does not have a single category created")
    void mustReturnAnEmptyListWhenDoesNotHaveASingleCategoryCreated(){
        when(categoryRepository.findAll()).thenReturn(List.of());

        var response = getAllCategoriesService.getAll();

        assertInstanceOf(List.class, response);
        assertTrue(response.isEmpty());
    }
    @Test
    @DisplayName("must return a list with categories")
    void mustReturnAListWithCategories(){
        var category = getCategory();
        int arraySize = 1;
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        var response = getAllCategoriesService.getAll();

        assertInstanceOf(List.class, response);
        assertEquals(arraySize, response.size());
        assertEquals(category.getId(), response.getFirst().getId());
        assertEquals(category.getName(), response.getFirst().getName());
    }
}