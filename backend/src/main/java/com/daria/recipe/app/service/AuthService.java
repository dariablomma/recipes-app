package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.auth.AuthResponse;
import com.daria.recipe.app.dto.auth.LoginRequest;
import com.daria.recipe.app.dto.auth.SignUpRequest;
import com.daria.recipe.app.dto.user.UserResponse;
import com.daria.recipe.app.dto.user.UserResponseWithPassword;
import com.daria.recipe.app.exception.UnauthorizedException;
import com.daria.recipe.app.mapper.UserMapper;
import com.daria.recipe.app.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse signUp(SignUpRequest request) {
        UserResponse userResponse = userService.create(request);
        return buildAuthResponse(userResponse);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        UserResponseWithPassword user = userService.getMeWithPassword(request.getUserName());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        return buildAuthResponse(userMapper.fromWithPasswordToResponse(user));
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