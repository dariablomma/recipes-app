package com.daria.recipe.app.mapper;

import com.daria.recipe.app.dto.SignUpRequest;
import com.daria.recipe.app.dto.UserResponse;
import com.daria.recipe.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User toEntityWithoutPassword(SignUpRequest request);

    UserResponse toResponse(User user);
}
