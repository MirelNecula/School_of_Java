package org.example.traveljournalapi.controller;

import org.example.traveljournalapi.dto.CreateUserDTO;
import org.example.traveljournalapi.dto.UpdateUserDTO;
import org.example.traveljournalapi.dto.UserCredentialsDTO;
import org.example.traveljournalapi.dto.UserDetailsDTO;
import org.example.traveljournalapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-journal")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public UserDetailsDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<UserDetailsDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public UserDetailsDTO createUser(@RequestBody CreateUserDTO dto){
        return userService.createUser(dto);
    }

    @PutMapping("/user/{id}")
    public UserDetailsDTO updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO dto){
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id); }

    @PostMapping("/login")
    public String singIn(@RequestBody UserCredentialsDTO userCredentialsDTO){
        Boolean isAuthenticated = userService.singIn( userCredentialsDTO);
        return isAuthenticated ? "Login successful" : "Invalid credentials";

    }
}
