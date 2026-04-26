package com.daria.recipe.app.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private UUID refreshToken;
    @Builder.Default
    private String type = "Bearer";
    private Long expiresIn;
    private String userName;
    private Long userId;
}
