package htwb.ai.controller;

import htwb.ai.dao.DBUserDAO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import javax.servlet.ServletContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup (
                new UserController(new DBUserDAO("PU-Songs"))).build();
    }

    @Test
        // GET /auth/1
    void getUserShouldReturnOKAndUserForExistingId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/auth/finkin")).andExpect(status().isOk());
//      Validate the response code, content type, and payload:
//                  { "id":1,
//                    "userId":"bsmith",
//                    "firstname":"bob",
//                    "lastname":"smith",
//                    "password":"secret" }

    }


}
