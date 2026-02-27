package com.daria.recipe.app.dto;

import lombok.Data;

@Data
public class RecipeCreateResponse {
    private Long id;
    private String name;
    private Long categoryId;
    private Long userId;
    private String externalLink;
    private String description;
}
