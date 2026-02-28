package com.daria.recipe.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "User name is required")
    @Size(min = 2, max = 200, message = "User name must be between 2 and 200 characters")
    private String userName;

    @NotBlank(message = "User name is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "User name is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
