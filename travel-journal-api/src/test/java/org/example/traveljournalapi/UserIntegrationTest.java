package org.example.traveljournalapi;


import org.example.traveljournalapi.entities.User;
import org.example.traveljournalapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.deleteAll();
    }

    @Test
    void getUser_returns200_whenUserExists() throws Exception {

        User user = new User();
        user.setName("Test");
        user.setSurname("User");
        user.setEmail("test@example.com");
        user.setPassword("password");
        User savedUser = userRepository.save(user);

        Long id = user.getId();

        mockMvc.perform(get("/travel-journal/user/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.surname").value("User"));
    }

    @Test
    void getUser_returns404_whenUserDoesNotExist() throws Exception {
        Long nonExistingId = 999L;

        mockMvc.perform(get("/travel-journal/user/" + nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_createUserInDatabase() throws Exception {
        String userJson = """
                                    {
                                        "name": "Necula",
                                        "surname": "Vali",
                                        "email": "neculavalentin@gmail.com",
                                        "password": "12345"
                        }
                """;
        mockMvc.perform(post("/travel-journal/user")
                .contentType("application/json")
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Necula"))
                .andExpect(jsonPath("$.surname").value("Vali"))
                .andExpect(jsonPath("$.email").value("neculavalentin@gmail.com"));
    }

    @Test
    void deleteUser_deleteUsersAndReturns202() throws Exception {
        User user = new User();
        user.setEmail("vali.rulz@yahoo.com");
        user.setName("Valentin");
        user.setSurname("Necula");
        user.setPassword("parola123");
        User savedUser = userRepository.save(user);

        Long id = savedUser.getId();

        assertThat(userRepository.findById(id).isPresent());

        mockMvc.perform(delete("/travel-journal/user/" + id))
                .andExpect(status().isOk());

        assertThat(userRepository.findById(id).isPresent()).isFalse();
    }

    @Test
    void deleteUser_returns404_whenUserDoesNotExist() throws Exception {
        Long nonExistingId = 888L;

        mockMvc.perform(delete("/travel-journal/user/" + nonExistingId))
                .andExpect(status().isNotFound());
    }
}
