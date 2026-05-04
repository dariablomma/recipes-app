package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.auth.AuthResponse;
import com.daria.recipe.app.dto.auth.LoginRequest;
import com.daria.recipe.app.dto.auth.RefreshTokenRequest;
import com.daria.recipe.app.dto.auth.SignUpRequest;
import com.daria.recipe.app.dto.user.UserResponse;
import com.daria.recipe.app.dto.user.UserResponseWithPassword;
import com.daria.recipe.app.entity.User;
import com.daria.recipe.app.exception.ResourceNotFoundException;
import com.daria.recipe.app.exception.UnauthorizedException;
import com.daria.recipe.app.mapper.UserMapper;
import com.daria.recipe.app.repository.UserRepository;
import com.daria.recipe.app.security.AccessTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AccessTokenService accessTokenService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    @Transactional
    public AuthResponse signUp(SignUpRequest request) {
        UserResponse userResponse = userService.create(request);
        UUID refreshToken = refreshTokenService.createRefreshToken(userResponse.getId());
        return buildAuthResponse(userResponse, refreshToken);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        UserResponseWithPassword user = userService.getMeWithPassword(request.getUserName());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        UUID refreshToken = refreshTokenService.rotateRefreshToken(user.getId());
        return buildAuthResponse(userMapper.fromWithPasswordToResponse(user), refreshToken);
    }

    @Transactional
    public AuthResponse refresh(Long userId, RefreshTokenRequest request) {
        User user = userRepository.findActiveById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found or deleted: " + userId));
        refreshTokenService.verifyRefreshToken(request.refreshToken());
        UUID refresh = refreshTokenService.rotateRefreshToken(userId);
        return buildAuthResponse(userMapper.toResponse(user), refresh);
    }

    @Transactional
    public void logout(Long userId) {
        refreshTokenService.revokeRefreshToken(userId);
    }

    private AuthResponse buildAuthResponse(UserResponse user, UUID refreshToken) {
        String token = accessTokenService.generateToken(
                user.getUserName(),
                user.getId(),
                user.getEmail()
        );

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .expiresAt(accessTokenService.getExpiresAt())
                .userName(user.getUserName())
                .userId(user.getId())
                .build();
    }
}