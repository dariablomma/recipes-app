package com.daria.recipe.app.jsonNullableExample;

import com.daria.recipe.app.dto.RecipeResponse;
import com.daria.recipe.app.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface RecipeMapperExample {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    RecipeResponse toResponse(Recipe recipe);

    @Mapping(target = "category", ignore = true)
    void update(RecipeUpdateRequestExample updateRequest, @MappingTarget Recipe recipe);
}
