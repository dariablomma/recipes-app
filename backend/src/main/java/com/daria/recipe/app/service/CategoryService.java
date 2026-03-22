package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.CategoryCreateRequest;
import com.daria.recipe.app.dto.CategoryResponse;
import com.daria.recipe.app.dto.CategoryUpdateRequest;
import com.daria.recipe.app.entity.Category;
import com.daria.recipe.app.exception.ConflictException;
import com.daria.recipe.app.exception.ResourceNotFoundException;
import com.daria.recipe.app.mapper.CategoryMapper;
import com.daria.recipe.app.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryResponse create(CategoryCreateRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new ConflictException("Category with such name already exists" + request.getName());
        }
        Category category = categoryRepository.save(categoryMapper.toEntity(request));
        return categoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryResponse update(Long categoryId, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category with such id not found: " + categoryId));
        if (categoryRepository.existsByName(request.getName())) {
            throw new ConflictException("Category with such name already exists: " + request.getName());
        }

        categoryMapper.update(request, category);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.toResponse(categorySaved);
    }
}
