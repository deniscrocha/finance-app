package com.denis.api.modules.category.service;

import com.denis.api.modules.category.controller.response.GetAllCategoriesResponse;
import com.denis.api.modules.category.repository.CategoryRepository;
import com.denis.api.modules.category.mapper.GetAllCategoriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCategoriesService {
    private final CategoryRepository categoryRepository;

    public List<GetAllCategoriesResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(GetAllCategoriesMapper::toResponse)
                .toList();
    }
}
