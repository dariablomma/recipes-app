package com.daria.recipe.app.jsonNullableExample;

import com.daria.recipe.app.dto.recipe.RecipeResponse;
import com.daria.recipe.app.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeControllerExample {
    private final RecipeServiceExample recipeService;

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse update(
            @PathVariable("id") Long recipeId,
            @Valid @RequestBody RecipeUpdateRequestExample request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return recipeService.updateRecipe(userDetails.getId(), recipeId, request);
    }
}
