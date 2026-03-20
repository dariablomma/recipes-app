package com.daria.recipe.app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
public class RecipeUpdateRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Recipe name must be between 2 and 200 characters")
    private String name;

    @NotNull(message = "Category id is required")
    private Long categoryId;

    @Valid
    @URL(message = "Invalid URL format")
    @Size(max = 500)
    private String externalLink;

    @Valid
    @Size(max = 2000, message = "Recipe description name must be between 2 and 2000 characters")
    private String description;
}
