package org.example.travel_journal_app.api.controller;

import org.example.travel_journal_app.generated.api.UsersApi;
import org.example.travel_journal_app.generated.model.UserPatchRequest;
import org.example.travel_journal_app.generated.model.UserPutRequest;
import org.example.travel_journal_app.generated.model.UserResponse;
import org.example.travel_journal_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UsersApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Override
    public ResponseEntity<UserResponse> getUserByEmail(String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @Override
    public ResponseEntity<UserResponse> replaceUser(Long userId, UserPutRequest userPutRequest) {
        return ResponseEntity.ok(userService.replaceUser(userId, userPutRequest));
    }

    @Override
    public ResponseEntity<UserResponse> patchUser(Long userId, UserPatchRequest request) {
        return ResponseEntity.ok(userService.patchUser(userId, request));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
