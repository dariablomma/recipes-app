package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeCreateResponse;
import com.daria.recipe.app.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public ResponseEntity<RecipeCreateResponse> createRecipe(@Valid @RequestBody RecipeCreateRequest request) {
        RecipeCreateResponse createdRecipe = recipeService.createRecipe(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
    }
}
