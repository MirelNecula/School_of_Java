package org.example.travel_journal_app.api.controller;

import org.example.travel_journal_app.generated.api.AuthApi;
import org.example.travel_journal_app.generated.model.LoginRequest;
import org.example.travel_journal_app.generated.model.LoginResponse;
import org.example.travel_journal_app.generated.model.RegisterRequest;
import org.example.travel_journal_app.generated.model.UserResponse;
import org.example.travel_journal_app.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<UserResponse> registerUser(RegisterRequest registerRequest) {
        UserResponse created = authService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
