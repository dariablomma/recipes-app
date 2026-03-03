package com.daria.recipe.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private List<Long> recipeIds;
}
