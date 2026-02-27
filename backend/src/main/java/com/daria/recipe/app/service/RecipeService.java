package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeCreateResponse;
import com.daria.recipe.app.entity.Recipe;
import com.daria.recipe.app.exception.ResourceNotFoundException;
import com.daria.recipe.app.mapper.RecipeMapper;
import com.daria.recipe.app.repository.CategoryRepository;
import com.daria.recipe.app.repository.RecipeRepository;
import com.daria.recipe.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeMapper recipeMapper;

    public RecipeCreateResponse createRecipe(RecipeCreateRequest createRequest) {
      if (!userRepository.existsById(createRequest.getUserId()))  {
          throw new ResourceNotFoundException( "User not found with id: " + createRequest.getUserId());
      }
      if (!categoryRepository.existsById(createRequest.getCategoryId())) {
          throw new ResourceNotFoundException("Category not found with id:" + createRequest.getCategoryId());
      }
       Recipe recipe = recipeMapper.toEntity(createRequest);
       Recipe savedRecipe = recipeRepository.save(recipe);
       return recipeMapper.toResponse(savedRecipe);
    }
}
