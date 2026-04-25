package com.daria.recipe.app.service;

import com.daria.recipe.app.dto.auth.SignUpRequest;
import com.daria.recipe.app.dto.user.UserResponse;
import com.daria.recipe.app.dto.user.UserResponseWithPassword;
import com.daria.recipe.app.entity.User;
import com.daria.recipe.app.exception.ConflictException;
import com.daria.recipe.app.exception.ResourceNotFoundException;
import com.daria.recipe.app.mapper.UserMapper;
import com.daria.recipe.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse create(SignUpRequest request) {
        if (userRepository.existsByUserNameAndDeletedAtIsNull(request.getUserName())) {
            throw new ConflictException("User with such name already exists");
        }

        User userForSaving = userMapper.toEntityWithoutPassword(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userForSaving.setPassword(encodedPassword);

        User savedUser = userRepository.save(userForSaving);
        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getMe(Long userId) {
        User user = userRepository.findActiveById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist or deleted with such id " + userId)
        );
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponseWithPassword getMeWithPassword(String userName) {
        User user  = userRepository
                .findActiveByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist or deleted with username: " + userName));
        return userMapper.toResponseWithPassword(user);
    }
}
