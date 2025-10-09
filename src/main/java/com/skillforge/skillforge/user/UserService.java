package com.skillforge.skillforge.user;

import com.skillforge.skillforge.dto.CreateUserRequest;
import com.skillforge.skillforge.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse create(CreateUserRequest req) {
        if (userRepository.existsByEmail(req.email().toLowerCase().trim())) {
            throw new IllegalArgumentException("An account with this email address already exists.");
        }

        User user = User.builder()
                .email(req.email().toLowerCase().trim())
                .passwordHash(passwordEncoder.encode(req.password()))
                .build();

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("An account with this email address already exists.");
        }

        return new UserResponse(user.getId(), user.getEmail(), user.getCreatedAt());
    }

    @Transactional
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        return new UserResponse(user.getId(), user.getEmail(), user.getCreatedAt());
    }
}
