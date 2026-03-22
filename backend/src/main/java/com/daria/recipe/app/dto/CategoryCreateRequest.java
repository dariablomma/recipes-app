package com.daria.recipe.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryCreateRequest {
    @NotBlank(message =  "Category name is required")
    private String name;
}
