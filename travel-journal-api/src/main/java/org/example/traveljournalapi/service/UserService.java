package org.example.traveljournalapi.service;

import org.example.traveljournalapi.dto.CreateUserDTO;
import org.example.traveljournalapi.dto.UpdateUserDTO;
import org.example.traveljournalapi.dto.UserCredentialsDTO;
import org.example.traveljournalapi.dto.UserDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDetailsDTO createUser(CreateUserDTO user);

    UserDetailsDTO getUser(Long id);

    List<UserDetailsDTO> getAllUsers();

    UserDetailsDTO updateUser(Long id, UpdateUserDTO user);

    void deleteUser(Long id);

    Boolean singIn(UserCredentialsDTO userCredentialsDTO);
}
