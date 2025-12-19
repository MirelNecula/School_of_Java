package org.example.traveljournalapi.service;

import org.example.traveljournalapi.dto.CreateUserDTO;
import org.example.traveljournalapi.dto.UpdateUserDTO;
import org.example.traveljournalapi.dto.UserCredentialsDTO;
import org.example.traveljournalapi.dto.UserDetailsDTO;
import org.example.traveljournalapi.entities.User;
import org.example.traveljournalapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.example.traveljournalapi.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDTO createUser(CreateUserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saveUser = userRepository.save(user);
        UserDetailsDTO result = new UserDetailsDTO();
        result.setId(saveUser.getId());
        result.setName(saveUser.getName());
        result.setSurname(saveUser.getSurname());
        result.setEmail(saveUser.getEmail());
        return result;
    }

    @Override
    public UserDetailsDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public List<UserDetailsDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserDetailsDTO dto = new UserDetailsDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setEmail(user.getEmail());
            return dto;
        }).toList();
    }

    @Override
    public UserDetailsDTO updateUser(Long id, UpdateUserDTO dto) {
        User userupdate = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userupdate.setName(dto.getName());
        userupdate.setSurname(dto.getSurname());

        User updateUser = userRepository.save(userupdate);

        UserDetailsDTO result = new UserDetailsDTO();
        result.setId(updateUser.getId());
        result.setName(updateUser.getName());
        result.setSurname(updateUser.getSurname());
        result.setEmail(updateUser.getEmail());
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public Boolean singIn(UserCredentialsDTO credentials) {
        var optionalUser = userRepository.findByEmail(credentials.getEmail());

        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();

        if (user.getPassword().equals(credentials.getPassword())) {
            return true;
        }

        return false;
    }
}
