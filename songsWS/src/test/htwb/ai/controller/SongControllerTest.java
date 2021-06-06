package htwb.ai.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import com.google.gson.*;
class SongControllerTest {

    private MockMvc mockMvc;
    private TestSongDAO songDAOMock;
    private Gson gson;

    @BeforeEach
    public void setup() {
        this.gson = new Gson();
        this.songDAOMock = new TestSongDAO();
//		songDAOMock = Mockito.mock(ISongDAO.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
                new SongController(this.songDAOMock)).build();
    }


    @Test
        // GET /auth/1
    void getSongShouldReturnOK() throws Exception {

        mockMvc.perform(get("/songs/1").header("Content-Type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        System.out.println("###### TEST");
    }
    @Test
        // GET /auth/1
    void getAllUsersShouldReturnOK() throws Exception {

        mockMvc.perform(get("/songs/").header("Content-Type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void addSongShouldReturn201() throws Exception {
        Song song = new Song(null,"new song","aaa", "bbb", 1999);
        String payload = gson.toJson(song);
        mockMvc.perform(post("/songs/").header("Content-Type","application/json").content(payload))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        System.out.println("###### TEST");
    }
    @Test
    void deleteSongShouldReturn201() throws Exception {

        mockMvc.perform(delete("/songs/1").header("Content-Type","application/json"))
                .andExpect(status().isNoContent());
        System.out.println("###### TEST");
    }
    /*@Test
    void deleteNoSongShouldReturn400() throws Exception {

        mockMvc.perform(delete("/songs/2").header("Content-Type","application/json"))
                .andExpect(status().isNoContent());
        System.out.println("###### TEST");
    }*/
    // Weiter noch status von put checken und testen


    @Test
    void test() {
        fail("Not yet implemented");
    }

}
