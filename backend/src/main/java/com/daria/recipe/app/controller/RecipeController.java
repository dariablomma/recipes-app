package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeCreateResponse;
import com.daria.recipe.app.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeCreateResponse createRecipe(@Valid @RequestBody RecipeCreateRequest request) {
        RecipeCreateResponse createdRecipe = recipeService.createRecipe(request);
        return createdRecipe;
    }
}
