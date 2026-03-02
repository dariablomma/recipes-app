package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.AuthResponse;
import com.daria.recipe.app.dto.LoginRequest;
import com.daria.recipe.app.dto.SignUpRequest;
import com.daria.recipe.app.dto.UserResponse;
import com.daria.recipe.app.entity.User;
import com.daria.recipe.app.security.JwtService;
import com.daria.recipe.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest request) {
        UserResponse userResponse = userService.signUp(request);
        return buildAuthResponse(userResponse);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        UserResponse userResponse = userService.login(request);
        return buildAuthResponse(userResponse);
    }

    private AuthResponse buildAuthResponse(UserResponse user) {
        String token = jwtService.generateToken(
                user.getUserName(),
                user.getId(),
                user.getEmail()
        );

        return AuthResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpiresInSeconds())
                .userName(user.getUserName())
                .userId(user.getId())
                .build();
    }
}
