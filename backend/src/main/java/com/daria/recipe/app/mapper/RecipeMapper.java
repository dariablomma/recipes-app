package com.daria.recipe.app.mapper;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeCreateResponse;
import com.daria.recipe.app.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "user", ignore = true)
    Recipe toEntity(RecipeCreateRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "catgory.id", target = "categoryId")
    RecipeCreateResponse toResponse(Recipe recipe);
}
