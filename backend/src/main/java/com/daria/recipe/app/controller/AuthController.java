package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.AuthResponse;
import com.daria.recipe.app.dto.SignUpRequest;
import com.daria.recipe.app.dto.UserResponse;
import com.daria.recipe.app.security.JwtService;
import com.daria.recipe.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest request) {
        UserResponse userResponse = userService.signUp(request);
        String jwtToken = jwtService.generateToken(
                userResponse.getUserName(),
                userResponse.getId(),
                userResponse.getEmail()
        );

        return AuthResponse
                .builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpiresInSeconds())
                .userName(userResponse.getUserName())
                .userId(userResponse.getId())
                .build();
    }
}
