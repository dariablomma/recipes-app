package com.daria.recipe.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeCreateRequest {
    @NotBlank(message = "Recipe name is required")
    @Size(min = 2, max = 200, message = "Recipe name must be between 2 and 200 characters")
    private String name;

    @NotNull(message =  "Category Id is required")
    private Long categoryId;

    private String externalLink; // YouTube or other platforms links, where recipe is uploaded

    private String description;
}
