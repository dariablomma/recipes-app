package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.CategoryRequest;
import com.daria.recipe.app.dto.CategoryResponse;
import com.daria.recipe.app.entity.Category;
import com.daria.recipe.app.mapper.CategoryMapper;
import com.daria.recipe.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest request) {
        // todo: add validaion of name uniqueness
        Category category = categoryRepository.save(categoryMapper.toEntity(request));
        return categoryMapper.toResponse(category);
    }
}
