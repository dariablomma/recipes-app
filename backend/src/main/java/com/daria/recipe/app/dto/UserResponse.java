package com.daria.recipe.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userName; // login name
    private String email;
    private String firstName;
    private String lastName;
    private List<Long> recipeIds;
}
