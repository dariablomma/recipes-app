package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.CategoryCreateRequest;
import com.daria.recipe.app.dto.CategoryResponse;
import com.daria.recipe.app.dto.CategoryUpdateRequest;
import com.daria.recipe.app.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CategoryCreateRequest request) {
        return categoryService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse update(
            @PathVariable("id") Long categoryId,
            @Valid @RequestBody CategoryUpdateRequest request
    ) {
        return categoryService.update(categoryId, request);
    }

    @GetMapping("/{id}")
    public CategoryResponse getOne(@PathVariable("id") Long categoryId) {
        return categoryService.getOne(categoryId);
    }
}
