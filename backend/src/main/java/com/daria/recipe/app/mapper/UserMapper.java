package com.daria.recipe.app.mapper;

import com.daria.recipe.app.dto.SignUpRequest;
import com.daria.recipe.app.dto.UserResponse;
import com.daria.recipe.app.entity.Recipe;
import com.daria.recipe.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User toEntityWithoutPassword(SignUpRequest request);

    @Mapping(source = "recipes", target = "recipeIds")
    UserResponse toResponse(User user);

    default List<Long> mapRecipesToIds(Collection<Recipe> recipes) {
        if (recipes == null) {
            return List.of();
        }
        return recipes.stream().map(Recipe::getId).toList();
    }
}
