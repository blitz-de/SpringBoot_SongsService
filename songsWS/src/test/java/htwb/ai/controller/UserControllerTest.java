package htwb.ai.controller;

import htwb.ai.dao.DBUserDAO;
import htwb.ai.dao.TestUserDAO;
import htwb.ai.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import javax.servlet.ServletContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private MockMvc mockMvc;
    private TestUserDAO userDAOMock;
    private Gson gson;
    User user1;
    User user2;

    @BeforeEach
    public void setup() {
        this.userDAOMock = new TestUserDAO();
        this.gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(
                new UserController(userDAOMock)).build();

        user1 = User.builder().withUserId("saki")
                .withFirstName("sakhr").withLastName("al")
                .withPassword("1234").build();

        user2 = User.builder().withUserId("dave")
                .withFirstName("Davis").withLastName("--")
                .withPassword("1234").build();
    }

    @Test
        // GET /auth/finkin
    void getUserShouldReturnOKAndUserForExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/finkin")).andExpect(status().isOk());

    }
    @Test
        // GET /auth/finkin
    void getNotExistingUser400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/abc")).andExpect(status().isNotFound());
    }



    // Einloggen -> PUT (useriD, pass) im body

    // Mit falschen Pass -> bad request

    // first und lasname -> was kommt zurueck

    // sessionKey -> lange nicht null

    // falsche Object schicken

    // MediaType

    // Header

}
