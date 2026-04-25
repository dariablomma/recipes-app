package com.daria.recipe.app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseWithPassword {
    private Long id;
    private String userName; // login name
    private String email;
    private String firstName;
    private String lastName;
    private List<Long> recipeIds;
    private String password;
    private Instant deletedAt;
}
