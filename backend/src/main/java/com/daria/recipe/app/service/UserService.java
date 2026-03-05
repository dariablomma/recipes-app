package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.LoginRequest;
import com.daria.recipe.app.dto.SignUpRequest;
import com.daria.recipe.app.dto.UserResponse;
import com.daria.recipe.app.entity.User;
import com.daria.recipe.app.exception.ConflictException;
import com.daria.recipe.app.exception.UnauthorizedException;
import com.daria.recipe.app.mapper.UserMapper;
import com.daria.recipe.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse signUp(SignUpRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new ConflictException("User with such name already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("User with such email already exists");
        }

        User userForSaving = userMapper.toEntityWithoutPassword(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userForSaving.setPassword(encodedPassword);

        User savedUser = userRepository.save(userForSaving);
        return userMapper.toResponse(savedUser);
    }

    public UserResponse login(LoginRequest request) {
        String error = "Invalid username or password";
        User user  = userRepository
                .findByUserNameWithRecipes(request.getUserName())
                .orElseThrow(() -> new UnauthorizedException(error));

        if (!passwordEncoder.matches(user.getPassword(), request.getPassword())) {
            throw new UnauthorizedException(error);
        }

        return userMapper.toResponse(user);
    }
}
