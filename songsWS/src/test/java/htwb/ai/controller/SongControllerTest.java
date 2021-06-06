package htwb.ai.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import htwb.ai.dao.ISongDAO;
import htwb.ai.dao.TestSongDAO;
import htwb.ai.model.Song;

class SongControllerTest {

	private MockMvc mockMvc;
	private ISongDAO songDAOMock;

	@BeforeEach
	public void setup() {
//		songDAOMock = Mockito.mock(ISongDAO.class);
		mockMvc = MockMvcBuilders.standaloneSetup(
				new SongController(new TestSongDAO())).build();
	}
	

    @Test
        // GET /auth/1
    void getUserShouldReturnOKAndUserForExistingId() throws Exception {

        mockMvc.perform(get("/songs/1"))
//      Validate the response code, content type, and payload:
//                  { "id":1,
//                    "userId":"bsmith",
//                    "firstname":"bob",
//                    "lastname":"smith",
//                    "password":"secret" }
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("bob"))
                .andExpect(jsonPath("$.lastname").value("smith"))
                .andExpect(jsonPath("$.userId").value("bsmith"));
    }
    
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
	