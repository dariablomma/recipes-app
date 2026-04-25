package com.daria.recipe.app.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryPageResponse {
    private Long id;
    private String name;
}
