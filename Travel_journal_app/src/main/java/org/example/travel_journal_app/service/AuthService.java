package org.example.travel_journal_app.service;

import org.example.travel_journal_app.entities.User;
import org.example.travel_journal_app.exception.DuplicateResourceException;
import org.example.travel_journal_app.generated.model.LoginRequest;
import org.example.travel_journal_app.generated.model.LoginResponse;
import org.example.travel_journal_app.generated.model.RegisterRequest;
import org.example.travel_journal_app.generated.model.UserResponse;
import org.example.travel_journal_app.repository.UserRepository;
import org.example.travel_journal_app.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email is already in use");
        }

        OffsetDateTime now = OffsetDateTime.now();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        User saved = userRepository.save(user);
        return toUserResponse(saved);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        boolean ok = passwordEncoder.matches(request.getPassword(), user.getPasswordHash());
        if (!ok) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String accessToken = jwtService.createAccessToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse()
                .user(toUserResponse(user))
                .accessToken(accessToken)
                .message("Login Successful");
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt());
    }
}
