//package htwb.ai.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContext;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import htwb.ai.dao.TestSongDAO;
//import htwb.ai.model.BadSong;
//import htwb.ai.model.Song;
//import com.google.gson.*;
////@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
////@WebAppConfiguration
//class SongControllerTest {
//	
//    private MockMvc mockMvc;
//    private TestSongDAO songDAOMock;
//    @Autowired
////    private SongController songControllerMock;
//    private Gson gson;
//    Song song1;
//    Song song2;
//    Song songNotFound;
//
////    private ISongDAO iDAO;
//    @BeforeEach
//    public void setup() {
//        this.gson = new Gson();
//        this.songDAOMock = new TestSongDAO();
//        mockMvc = MockMvcBuilders.standaloneSetup(
//                new SongController(songDAOMock)).build();
//        song1 = Song.builder().withId(1).withTitle("to the moon")
//        		.withArtist("Frank Senatra")
//        		.withAlbum("Dancing")
//        		.withReleased(2021).build();
//        //        Song song2 = new Song(null,"new song","aaa", "bbb", 1999);
//        song2 = Song.builder().withId(2).withTitle("new song").withArtist("aaa").withAlbum("bbb")
//        		.withReleased(1999).build();
//        songNotFound = Song.builder().withId(3).withTitle("update song").withArtist("the update").withAlbum("updateProcess")
//        		.withReleased(2000).build();
//        
//        
//    }
//
//    @Test
//    // GET /auth/1
//	void getUserShouldReturnOKAndUserForExistingId() throws Exception {
//	
//	//  Validate the response code, content type, and payload:
//	//              { "id":1,
//	//                "userId":"bsmith",
//	//                "firstname":"bob",
//	//                "lastname":"smith",
//	//                "password":"secret" }
//   // 	song1 = songDAOMock.getById(1);
//        System.out.println(song1);
////        String payload = gson.toJson(song1);
//        mockMvc.perform(get("/songs/{id}", 1).header("Content-Type","application/json"))
//        	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                
//                .andExpect(jsonPath("$.id").value(song1.getId()))
//                .andExpect(jsonPath("$.title").value(song1.getTitle()))
//                .andExpect(jsonPath("$.artist").value(song1.getArtist()))
//                .andExpect(jsonPath("$.album").value(song1.getAlbum()))
//                .andExpect(jsonPath("$.released").value(song1.getReleased()));
//        System.out.println("###### TEST");
//	}
//    @Test
//        // GET /auth/1
//    void getSongShouldReturnOK() throws Exception {
//        mockMvc.perform(get("/songs/1").header("Content-Type","application/json"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//        System.out.println("###### TEST");
//    }
//    @Test
//    void getSongReturn404WhenSongNotFound() throws Exception {
////    	when 
//        mockMvc.perform(get("/songs/{id}", 3).header("Content-Type","application/json"))
//            	.andExpect(status().isNotFound());     
//        System.out.println("###### TEST");
//    }
//    @Test
//        // GET /auth/1
// 
//   void getAllUsersShouldReturnOK() throws Exception {
//
//        mockMvc.perform(get("/songs/").header("Content-Type","application/json"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    void postSongShouldReturn201() throws Exception {
//        //Song song2 = new Song(null,"new song","aaa", "bbb", 1999);
////    	song2.setTitle("");
//        String payload = gson.toJson(song2);
//        mockMvc.perform(post("/songs/").header("Content-Type","application/json").content(payload))
//                .andExpect(status().isCreated());
//    }
//    
//    @Test
//    void deleteSongShouldReturn201() throws Exception {
//
//        mockMvc.perform(delete("/songs/1").header("Content-Type","application/json"))
//                .andExpect(status().isNoContent());
//        System.out.println("###### TEST");
//    }
//    
//    // recheck
//    @Test
//    void postSongReturn409() throws Exception { // Conflict
//    	song2.setTitle("");
//        String payload = gson.toJson(song2);
//        mockMvc.perform(post("/songs/").header("Content-Type","application/json").content(payload))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(song2.getId()))
//                .andExpect(jsonPath("$.title").value(song2.getTitle()))
//                .andExpect(jsonPath("$.artist").value(song2.getArtist()))
//                .andExpect(jsonPath("$.album").value(song2.getAlbum()))
//                .andExpect(jsonPath("$.released").value(song2.getReleased()))
//                .andExpect(status().isConflict());
//        System.out.println("###### TEST " + song2);
////    	mockMvc.perform(post("/songs/{id}", 1).header("Content-Type", "application/json"))
////    		.andExpect(matcher)
//    		//.andExpect(status().is4xxClientError());
//    }
//    
//    //bad request if i use song2
//    @Test
//    void putSongReturn204() throws Exception {
//        String payload = gson.toJson(song1);
//        System.out.println(song1);
//        mockMvc.perform(put("/songs/1").header("Content-Type","application/json").content(payload)
//        		.contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//        System.out.println("###### TEST " + payload);
//    }
//
//    @Test
//    void putSongReturn400() throws Exception {
//    	BadSong badSong = BadSong.builder().withId("one").withTitle("title").withArtist("artist")
//    			.withAlbum("album").withReleased("thousand nine-hundred").build();
//    	
//        String payload = gson.toJson(badSong);
//        mockMvc.perform(put("/songs/1").contentType(MediaType.APPLICATION_JSON)
//        		.header("Content-Type","application/json").content(payload))
//                .andExpect(status().isBadRequest());
//        System.out.println("###### TEST " + payload);
//    }
//    
//    @Test
//    void putSongReturn404() throws Exception {
//     
//        String payload = gson.toJson(songNotFound);
//        System.out.println(songNotFound);
//        mockMvc.perform(put("/songs/3").header("Content-Type","application/json").content(payload)
//        		.contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//        System.out.println("###### TEST " + payload);
//    }
//    
//    @Test
//    void deleteSongReturn204() throws Exception {
//        String payload = gson.toJson(song1);
//        System.out.println(song1);
//        mockMvc.perform(delete("/songs/{id}", 1).header("Content-Type","application/json").content(payload)
//        		.contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//        System.out.println("###### TEST " + payload);
//    }
//    
//    @Test
//    void deleteSongReturn404() throws Exception {
//    	
//        String payload = gson.toJson(song2);
//        mockMvc.perform(delete("/songs/3").contentType(MediaType.APPLICATION_JSON)
//        		.header("Content-Type","application/json").content(payload))
//                .andExpect(status().isNotFound());
//        System.out.println("###### TEST " + payload);
//    }
//    
//    @Test
//    void deleteSongReturn400() throws Exception {
//        String payload = gson.toJson(song2);
//        mockMvc.perform(delete("/songs/abc").contentType(MediaType.APPLICATION_JSON)
//        		.header("Content-Type","application/json").content(payload))
//                .andExpect(status().isBadRequest());
//        System.out.println("###### TEST " + payload);
//    }
//   
//    /*
//     * POST schickt Statuscode 201 und URI (/rest/songs/) zur neuen Ressource im „Location“-Header zurück
//
//     * PUT /songs/1 Statuscode 204 zurückschicken, ansonsten 400 bzw. 404
//
//     * DELETE songs/1: Statuscode 204 zurückschicken, ansonsten 400 bzw. 404
//     */
//    
//    
//    
//    
//    
//    
//    
//    
//    /*@Test
//    void deleteNoSongShouldReturn400() throws Exception {
//
//        mockMvc.perform(delete("/songs/2").header("Content-Type","application/json"))
//                .andExpect(status().isNoContent());
//        System.out.println("###### TEST");
//    }*/
//    // Weiter noch status von put checken und testen
//
//
//    @Test
//    void test() {
//    }
//
//}
