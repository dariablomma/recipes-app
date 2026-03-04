package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeResponse;
import com.daria.recipe.app.dto.RecipeUpdateRequest;
import com.daria.recipe.app.security.CustomUserDetails;
import com.daria.recipe.app.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponse create(
            @Valid @RequestBody RecipeCreateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return recipeService.createRecipe(userDetails.getId(), request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse update(
            @PathVariable Long recipeId,
            @Valid @RequestBody RecipeUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return recipeService.updateRecipe(userDetails.getId(), recipeId, request);
    }
}
