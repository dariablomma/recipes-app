package com.daria.recipe.app.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateRequest {
    @NotBlank(message =  "Category name is required")
    private String name;
}
