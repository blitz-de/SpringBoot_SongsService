package htwb.ai.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import htwb.ai.dao.TestSongDAO;
import htwb.ai.model.Song;
import com.google.gson.*;
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@WebAppConfiguration
class SongControllerTest {
	
    private MockMvc mockMvc;
    private TestSongDAO songDAOMock;
    @Autowired
    private SongController songControllerMock;
    private Gson gson;
    Song song1;
    Song song2;

//    private ISongDAO iDAO;
    @BeforeEach
    public void setup() {
        this.gson = new Gson();
        this.songDAOMock = new TestSongDAO();
        mockMvc = MockMvcBuilders.standaloneSetup(
                new SongController(songDAOMock)).build();
        song1 = Song.builder().withId(1).withTitle("to the moon")
        		.withArtist("Frank Senatra")
        		.withAlbum("Dancing")
        		.withReleased(2021).build();
        //        Song song2 = new Song(null,"new song","aaa", "bbb", 1999);
        song2 = Song.builder().withId(2).withTitle("new song").withArtist("aaa").withAlbum("bbb")
        		.withReleased(1999).build();
    }

    @Test
    // GET /auth/1
	void getUserShouldReturnOKAndUserForExistingId() throws Exception {
	
	//  Validate the response code, content type, and payload:
	//              { "id":1,
	//                "userId":"bsmith",
	//                "firstname":"bob",
	//                "lastname":"smith",
	//                "password":"secret" }
   // 	song1 = songDAOMock.getById(1);
        System.out.println(song1);
//        String payload = gson.toJson(song1);
        mockMvc.perform(get("/songs/{id}", 1).header("Content-Type","application/json"))
        	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.id").value(song1.getId()))
                .andExpect(jsonPath("$.title").value(song1.getTitle()))
                .andExpect(jsonPath("$.artist").value(song1.getArtist()))
                .andExpect(jsonPath("$.album").value(song1.getAlbum()))
                .andExpect(jsonPath("$.released").value(song1.getReleased()));
        System.out.println("###### TEST");
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
    void getSongReturn404WhenSongNotFound() throws Exception {
//    	when 
        mockMvc.perform(get("/songs/{id}", 3).header("Content-Type","application/json"))
            	.andExpect(status().isNotFound());     
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
        //Song song2 = new Song(null,"new song","aaa", "bbb", 1999);
        String payload = gson.toJson(song2);
        mockMvc.perform(post("/songs/").header("Content-Type","application/json").content(payload))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        System.out.println("###### TEST " + payload);
    }
    @Test
    void deleteSongShouldReturn201() throws Exception {

        mockMvc.perform(delete("/songs/1").header("Content-Type","application/json"))
                .andExpect(status().isNoContent());
        System.out.println("###### TEST");
    }
    
    @Test
    void postSongReturn400() throws Exception {
    	mockMvc.perform(post("/songs/{id}", 1).header("Content-Type", "application/json"))
//    		.andExpect(matcher)
    		.andExpect(status().is4xxClientError());
    }
    /*
     * POST schickt Statuscode 201 und URI (/rest/songs/) zur neuen Ressource im „Location“-Header zurück

     * PUT /songs/1 Statuscode 204 zurückschicken, ansonsten 400 bzw. 404

     * DELETE songs/1: Statuscode 204 zurückschicken, ansonsten 400 bzw. 404
     */
    
    
    
    
    
    
    
    
    /*@Test
    void deleteNoSongShouldReturn400() throws Exception {

        mockMvc.perform(delete("/songs/2").header("Content-Type","application/json"))
                .andExpect(status().isNoContent());
        System.out.println("###### TEST");
    }*/
    // Weiter noch status von put checken und testen


    @Test
    void test() {
    }

}
