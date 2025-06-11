package com.denis.api.modules.category.controller;

import com.denis.api.modules.category.controller.response.GetAllCategoriesResponse;
import com.denis.api.modules.category.service.GetAllCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final GetAllCategoriesService getAllCategoriesService;

    @GetMapping
    public List<GetAllCategoriesResponse> getAllCategories(){
        return getAllCategoriesService.getAll();
    }
}