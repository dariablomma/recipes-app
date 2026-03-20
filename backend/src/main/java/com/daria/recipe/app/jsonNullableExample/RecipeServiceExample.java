package com.daria.recipe.app.jsonNullableExample;

import com.daria.recipe.app.dto.RecipeResponse;
import com.daria.recipe.app.entity.Category;
import com.daria.recipe.app.entity.Recipe;
import com.daria.recipe.app.exception.ResourceNotFoundException;
import com.daria.recipe.app.repository.CategoryRepository;
import com.daria.recipe.app.repository.RecipeRepository;
import com.daria.recipe.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceExample {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeMapperExample recipeMapperExample;

    public RecipeResponse updateRecipe(Long userId, Long recipeId, RecipeUpdateRequestExample request) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(
                () -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));

        recipeMapperExample.update(request, recipe);

        if (request.getCategoryId().isPresent()) {
            Long categoryId = request.getCategoryId().get();
            Category category = categoryRepository
                    .findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id:" + categoryId
                    ));
            recipe.setCategory(category);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapperExample.toResponse(savedRecipe);
    }
}
