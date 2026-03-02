package com.daria.recipe.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "User password is required")
    private String password;
}
