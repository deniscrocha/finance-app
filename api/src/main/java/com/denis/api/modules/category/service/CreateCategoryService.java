package com.denis.api.modules.category.service;

import com.denis.api.modules.category.domain.Category;
import com.denis.api.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    public Category getOrCreateCategoryByName(String name){
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if(categoryOptional.isPresent())
            return categoryOptional.get();
        Category newCategory = Category.builder().name(name.toUpperCase()).build();
        return categoryRepository.save(newCategory);
    }
}
