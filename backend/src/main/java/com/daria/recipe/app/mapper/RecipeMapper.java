package com.daria.recipe.app.mapper;

import com.daria.recipe.app.dto.RecipeCreateRequest;
import com.daria.recipe.app.dto.RecipeResponse;
import com.daria.recipe.app.dto.RecipeUpdateRequest;
import com.daria.recipe.app.entity.Recipe;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    @Mapping(target = "category", ignore = true)
    Recipe toEntity(RecipeCreateRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    RecipeResponse toResponse(Recipe recipe);

    @Mapping(target = "category", ignore = true)
    void update(RecipeUpdateRequest request, @MappingTarget Recipe recipe);
}
