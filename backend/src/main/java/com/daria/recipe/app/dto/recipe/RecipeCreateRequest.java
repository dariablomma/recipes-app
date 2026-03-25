package com.daria.recipe.app.dto.recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
public class RecipeCreateRequest {
    @NotBlank(message = "Recipe name is required")
    @Size(min = 2, max = 200, message = "Recipe name must be between 2 and 200 characters")
    private String name;

    @NotNull(message =  "Category Id is required")
    private Long categoryId;

    @URL(message = "Invalid URL format")
    @Size(max = 500)
    private String externalLink;

    @Size(max = 2000, message = "Recipe description name must be between 2 and 2000 characters")
    private String description;
}
