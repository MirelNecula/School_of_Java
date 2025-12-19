package org.example.traveljournalapi;

import org.example.traveljournalapi.dto.CreateUserDTO;
import org.example.traveljournalapi.dto.UserCredentialsDTO;
import org.example.traveljournalapi.dto.UserDetailsDTO;
import org.example.traveljournalapi.entities.User;
import org.example.traveljournalapi.repositories.UserRepository;
import org.example.traveljournalapi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void createUser_shouldSaveUserAndReturnDetailsDTO() {
        CreateUserDTO dto = new CreateUserDTO();
        dto.setName("Necula");
        dto.setSurname("Valentin");
        dto.setEmail("test@example.com");
        dto.setPassword("12345");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserDetailsDTO result = userServiceImpl.createUser(dto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("Necula", savedUser.getName());
        assertEquals("Valentin", savedUser.getSurname());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("12345", savedUser.getPassword());

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Necula", result.getName());
        assertEquals("Valentin", result.getSurname());
        assertEquals("test@example.com", result.getEmail());
    }


    @Test
    void getUser_shouldReturnUserDetailsDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Necula");
        user.setSurname("Valentin");
        user.setEmail("test@example.com");
        user.setPassword("12345");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userServiceImpl.getUser(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Necula", result.getName());
        assertEquals("Valentin", result.getSurname());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void getUser_shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userServiceImpl.getUser(1L));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void singIn_shouldReturnLoginSuccessful_whenCredentialAreValid() {

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("12345");

        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(user));

        UserCredentialsDTO userCredentials = new UserCredentialsDTO();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("12345");

        boolean result = userServiceImpl.singIn(userCredentials);
        assertTrue(result);
    }

    @Test
    void signIn_shouldReturnLoginFailed_whenCredentialAreInvalid() {

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        UserCredentialsDTO userCredentials = new UserCredentialsDTO();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("12345");
        boolean result = userServiceImpl.singIn(userCredentials);
        assertFalse(result);
    }
    @Test
    void deleteUser_shouldDeleteExistingUser() {
        User user =new User();
        user.setId(1L);
        user.setName("Necula");
        user.setSurname("Valentin");
        user.setEmail("text@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userServiceImpl.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }


}