package com.daria.recipe.app.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "User name is required")
    @Size(min = 2, max = 200, message = "User Name must be between 2 and 200 characters")
    private String userName;

    @NotBlank(message = "User password is required")
    private String password;
}
