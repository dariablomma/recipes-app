package com.daria.recipe.app.controller;

import com.daria.recipe.app.dto.auth.AuthResponse;
import com.daria.recipe.app.dto.auth.LoginRequest;
import com.daria.recipe.app.dto.auth.RefreshTokenRequest;
import com.daria.recipe.app.dto.auth.SignUpRequest;
import com.daria.recipe.app.security.CustomUserDetails;
import com.daria.recipe.app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return authService.refresh(userDetails.getId(), request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        authService.logout(userDetails.getId());
    }
}
