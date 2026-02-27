package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeCreateResponse;
import com.daria.recipe.app.entity.Recipe;
import com.daria.recipe.app.mapper.RecipeMapper;
import com.daria.recipe.app.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public RecipeCreateResponse createRecipe(RecipeCreateRequest createRequest) {
       // todo: add validation of userId, categoryId
       Recipe recipe = recipeMapper.toEntity(createRequest);
       Recipe savedRecipe = recipeRepository.save(recipe);
       return recipeMapper.toResponse(savedRecipe);
    }
}
