package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeResponse;
import com.daria.recipe.app.dto.RecipeUpdateRequest;
import com.daria.recipe.app.entity.Category;
import com.daria.recipe.app.entity.Recipe;
import com.daria.recipe.app.entity.User;
import com.daria.recipe.app.exception.ResourceNotFoundException;
import com.daria.recipe.app.mapper.RecipeMapper;
import com.daria.recipe.app.repository.CategoryRepository;
import com.daria.recipe.app.repository.RecipeRepository;
import com.daria.recipe.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeMapper recipeMapper;

    public RecipeResponse createRecipe(Long userId, RecipeCreateRequest createRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException( "User not found with id: " + userId));
        Category category = categoryRepository
                .findById(createRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id:" + createRequest.getCategoryId()
                ));

       Recipe recipe = recipeMapper.toEntity(createRequest);
       recipe.setCategory(category);
       recipe.setUser(user);

       Recipe savedRecipe = recipeRepository.save(recipe);
       return recipeMapper.toResponse(savedRecipe);
    }

    public RecipeResponse putRecipe (Long userId, Long recipeId, RecipeUpdateRequest request) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
        if (!recipe.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You can edit only your own recipes");
        }

        recipeMapper.update(request, recipe);

        long requestCategoryId = request.getCategoryId();
        if (requestCategoryId != recipe.getCategory().getId()) {
            Category category = categoryRepository
                    .findById(requestCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id:" + requestCategoryId
                    ));
            recipe.setCategory(category);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toResponse(savedRecipe);
    }
}
