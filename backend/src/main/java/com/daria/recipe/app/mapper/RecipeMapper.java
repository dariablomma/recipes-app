package com.daria.recipe.app.mapper;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeCreateResponse;
import com.daria.recipe.app.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toEntity(RecipeCreateRequest request);

    RecipeCreateResponse toResponse(Recipe recipe);
}
