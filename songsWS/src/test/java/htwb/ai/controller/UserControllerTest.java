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

        user1 = User.builder().withUserId("finkin")
                .withFirstName("davis").withLastName("sky")
                .withPassword("secret").build();

        user2 = User.builder().withUserId("saki")
                .withFirstName("sakhr").withLastName("al")
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

    @Test
    void postAuthShouldReturn201() throws Exception {
        //Song song2 = new Song(null,"new song","aaa", "bbb", 1999);
//    	song2.setTitle("");
        //User u = new User(user1.getUserId(),user1.getPassword());
        String payload = gson.toJson(user1);
        mockMvc.perform(post("/auth/").header("Content-Type", "application/json").content(payload))
                .andExpect(status().isOk());
    }

    @Test
    void postNotAuthWrongUserShouldReturn404() throws Exception {
        //Song song2 = new Song(null,"new song","aaa", "bbb", 1999);
//    	song2.setTitle("");
        //User u = new User(user1.getUserId(),user1.getPassword());

        user1.setUserId("fari");
        String payload = gson.toJson(user1);
        mockMvc.perform(post("/auth/").header("Content-Type", "application/json").content(payload))
                .andExpect(status().isNotFound());
    }

    @Test
    void postNotAuthShouldReturn401() throws Exception {
        user1.setPassword("pass");
        String payload = gson.toJson(user1);
        mockMvc.perform(post("/auth/").header("Content-Type", "application/json").content(payload))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void postNullObjektShouldReturn400() throws Exception {
        user1.setUserId(null);
        user1.setPassword(null);
        String payload = gson.toJson(user1);
        mockMvc.perform(post("/auth/").header("Content-Type", "application/json").content(payload))
                .andExpect(status().isBadRequest());
    }
    @Test
    void postAllNullInternalServerError500() throws Exception{
        try {
            user1.setFirstName(null);
            user1.setLastName(null);
            user1.setPassword(null);
            user1.setUserId(null);
        } catch (NullPointerException ex) {
            String payload = gson.toJson(user1);
            mockMvc.perform(post("/auth/").header("Content-Type","application/json").content(payload))
                    .andExpect(status().isInternalServerError());
        }
    }
    // Einloggen -> PUT (useriD, pass) im body

    // Mit falschen Pass -> bad request

    // first und lasname -> was kommt zurueck

    // sessionKey -> lange nicht null

    // falsche Object schicken

    // MediaType

    // Header

}
