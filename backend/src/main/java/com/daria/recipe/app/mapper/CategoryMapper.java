package com.daria.recipe.app.mapper;

import com.daria.recipe.app.dto.CategoryRequest;
import com.daria.recipe.app.dto.CategoryResponse;
import com.daria.recipe.app.entity.Category;
import com.daria.recipe.app.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);

    @Mapping(source = "recipes", target = "recipeIds")
    CategoryResponse toResponse(Category category);

    default List<Long> mapRecipesToIds(Collection<Recipe> recipes) {
        if (recipes == null) return List.of();
        return recipes.stream().map(Recipe::getId).toList();
    }
}
