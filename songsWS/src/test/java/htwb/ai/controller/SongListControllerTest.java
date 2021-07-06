package htwb.ai.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import htwb.ai.config.JwtTokenUtil;
import htwb.ai.helper.AppConstants;
import htwb.ai.model.Song;
import org.aspectj.lang.annotation.Before;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import htwb.ai.model.SongList;
import htwb.ai.model.Users;
import htwb.ai.repository.SongListRepo;
import htwb.ai.repository.SongRepo;
import htwb.ai.repository.UserRepo;
import htwb.ai.service.JwtUserDetailsService;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
//@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SongListControllerTest {
    private MockMvc mockUserController;
    private MockMvc mockSlController;
    private MockMvc mockSongController;
    private MockMvc mockAuthController;
//
//	@Autowired
//	WebSecurityConfig web;

    @Autowired
    private UserRepo uRepo;
    @Autowired
    private SongListRepo slRepo;

    @Autowired
    private UserRepo userRepo;
//    @Autowired
//    private SongRepo sRepo;

    @Autowired
    private JwtUserDetailsService jwtUser;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    SongList songlist1;
    SongList songlist2;
    SongList songlist3;
    SongList songlist4;

    SongList songlist;
    private Gson gson;
    private Users user1 = new Users();
    private Users user2 = new Users();

    Principal securityUser;
    String token;
    Principal principal = new PrincipalImpl();

    @BeforeEach
    public void setupMockMvc() throws Exception {
        this.gson = new Gson();
//        securityUser = getPrincipal();
//        loadData();
        mockUserController = MockMvcBuilders.standaloneSetup(new UserController(uRepo)).build();
        mockSlController = MockMvcBuilders.standaloneSetup(new SongListsController(slRepo, uRepo))
                .build();
        // mockMvc3 = MockMvcBuilders.standaloneSetup(new
        // SongController(sRepo)).build();
        mockAuthController = MockMvcBuilders
                .standaloneSetup(new JwtAuthenticationController(jwtUser, authenticationManager, jwtTokenUtil)).build();

//        user1 = new UserDTO("mmuster","Bobby","Smith","pass1234");
        user1.setUsername("mmuster");
        user1.setFirstname("Maxime");
        user1.setLastname("Muster");
        user1.setPassword("pass1234");
        user2.setUsername(AppConstants.TEST_USER_2);
        user2.setFirstname("Bobby");
        user2.setLastname("Smith");
        user2.setPassword(AppConstants.DEFAULT_PASSWORD);

        songlist = new SongList("songlist default", false, user1);
        Song song = new Song();
        song.setTitle("new song");
        song.setArtist("Davis D. Sky");
        song.setAlbum("Intellij");
        songlist.getSongList().add(song);
        songlist1 = new SongList("songlist no1", false, user1);
        songlist2 = new SongList("songlist no2", true, user1);

        songlist3 = new SongList("songlist no3", false, user2);
        songlist4 = new SongList("songlist no4", true, user2);

        // ==========================================
//		uRepo.save(user1);

        // authenticate user 1
        String userToAuth = "{\"username\":\"mmuster\"," + "\"firstname\":\"Maxime\", " + "\"lastname\":"
                + "\"Muster\"," + "\"password\":\"pass1234\"}";
        Users savedUsers = uRepo.findByUsername("mmuster");
        MvcResult result = mockAuthController.perform(MockMvcRequestBuilders.post("/songsWS-sakvis/rest/auth")
                .contentType(MediaType.APPLICATION_JSON).content(userToAuth)).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        JSONObject tokenObject = new JSONObject(result.getResponse().getContentAsString());
        this.token = tokenObject.getString("token");
        System.out.println(this.token);

    }

    @Test
    @Order(1)
    public void sys() {
        System.out.println("######################### "+ this.token);
    }
    @Test
    @Order(2)
    public void getAPublicForeignSongLists() throws Exception {
        String payload = gson.toJson(user1);
        MvcResult result = mockSlController
                .perform(get("/songsWS-sakvis/rest/songLists?username=" + AppConstants.USER_2)
                        .header("Content-Type", "application/json").header("Authorization", "Bearer " + this.token)
                        .content(payload))
                .andExpect(status().isOk()).andReturn();
        Type listType = new TypeToken<ArrayList<SongList>>() {
        }.getType();
        List<SongList> list = gson.fromJson(result.getResponse().getContentAsString(), listType);
        assertEquals(16, list.size());

    }

    @Test
    @Order(3)
    public void getAOwnSongLists() throws Exception {
        String payload = gson.toJson(user1);
        MvcResult result = mockSlController
                .perform(get("/songsWS-sakvis/rest/songLists?username=" + AppConstants.USER_1)
                        .header("Content-Type", "application/json").header("Authorization", "Bearer " + 																								this.token)
                        .content(payload))
                .andExpect(status().isOk()).andReturn();
        Type listType = new TypeToken<ArrayList<SongList>>() {
        }.getType();
        List<SongList> list = gson.fromJson(result.getResponse().getContentAsString(), listType);
        assertEquals(list.size(), 1);

    }

    @Test
    @Order(4)
    public void postUserShouldSaveUser() throws Exception {
        String userToSave = "{\"username\":\"mmuster\"," + "\"firstname\":\"Maxime\", " + "\"lastname\":\"Muster\","
                + "\"password\":\"pass1234\"}";

        mockAuthController.perform(MockMvcRequestBuilders.post("/songsWS-sakvis/rest/register")
                .contentType(MediaType.APPLICATION_JSON).content(userToSave)).andExpect(status().isOk());
//                .andExpect(header().string("location", containsString("/api/users")));
        System.out.println("$$$$$$$$$$$$$$$$$$$4444444444444$$$");
        Users savedUsers = uRepo.findByUsername("mmuster");
//       assertTrue(savedUsers.) == 1);
        Users u = savedUsers;
        System.out.println("####################################################3 " + u.getFirstname());
        assertTrue(u.getUsername().equals("mmuster"));
        assertTrue(u.getFirstname().equals("Maxime"));
        assertTrue(u.getLastname().equals("Muster"));
//		String hashed = web.passwordEncoder().encode(u.getPassword());
//		System.out.println("################################## " + hashed);
//       assertTrue (u.getPassword().equals("pass1234"));
    }

    @Test
    @Order(5)
    void getAllSongLists() throws Exception {
        System.out.println("1~~~~~~~~~~~~~~~~~~~~~~~~~~ " + this.token);
        MvcResult result = mockSlController.perform(MockMvcRequestBuilders.get("/songsWS-sakvis/rest/songLists/all")
                .header("Authorization", "Bearer" + " " + this.token))
                .andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

//================================

    @Test
    public void getForbiddenSongList() throws Exception {
        String payload = gson.toJson(user1);
        // get song:
        MvcResult result = mockSlController
                .perform(get("/songsWS-sakvis/rest/songLists/1").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isForbidden()).andReturn();
        System.out.println("### SongLists result --> " + result.getResponse().getContentAsString());

        // ToDo: check status forbidden
    }

    @Test
    public void getOwnSongList() throws Exception {
        String payload = gson.toJson(user1);
        MvcResult result = mockSlController
                .perform(get("/songsWS-sakvis/rest/songLists/1").header("Content-Type", "application/json") //was 37
                        .header("Authorization", "Bearer " + this.token)
                        .principal(principal)
                        .content(payload)
                )//.sessionAttr(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, this.token)
                .andExpect(status().isOk()).andReturn();
        System.out.println("### Own SL result --> " + result.getResponse().getContentAsString());
        // ToDo: check status forbidden
    }

    @Test
    public void getForeignPublicSongList() throws Exception {
        String payload = gson.toJson(user1);
        // get song:
        MvcResult result = mockSlController
                .perform(get("/songsWS-sakvis/rest/songLists/2").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isOk()).andReturn();
        // ToDo: check status forbidden

    }

    @Test
    public void getSongListWithNonNummericId() throws Exception {
        String payload = gson.toJson(user1);
        // get song:
        MvcResult result = mockSlController
                .perform(get("/songsWS-sakvis/rest/songLists/abc").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void getSongListsWrongUsername() throws Exception {
        String payload = gson.toJson(user1);
        MvcResult result = mockSlController.perform(get("/songsWS-sakvis/rest/songLists?username=wrongUsername")
                .header("Content-Type", "application/json").header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void postCorrectSongList() throws Exception {
        //		MvcResult result = mockSlController.perform(MockMvcRequestBuilders.get("/songsWS-sakvis/rest/songLists/all")
//		.header("Authorization", "Bearer" + " " + this.token)).andReturn();
        String payload = gson.toJson(songlist); //songlist
        MvcResult result = mockSlController
                .perform(post("/songsWS-sakvis/rest/songLists/")
                        .contentType("application/json")
                        .header("Authorization", "Bearer" + " " + this.token)
                        .principal(principal)
                        .content(payload))

                .andExpect(status().isAccepted()).andReturn();
    }

    @Test
    public void postWithoutNameForSongList() throws Exception {
        songlist.setName(null);
        String payload = gson.toJson(songlist);
        MvcResult result = mockSlController
                .perform(post("/songsWS-sakvis/rest/songLists/").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void postEmptyNameForSongList() throws Exception {
        songlist.setName("");
        String payload = gson.toJson(songlist);
        MvcResult result = mockSlController
                .perform(post("/songsWS-sakvis/rest/songLists/").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void postWithoutTitleInSongOfSongList() throws Exception {
        Song s = new Song();
        s.setTitle(null);
        songlist.getSongList().add(s);
        String payload = gson.toJson(songlist);
        MvcResult result = mockSlController
                .perform(post("/songsWS-sakvis/rest/songLists/").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void postEmptyTitleInSongOfSongList() throws Exception {
        Song s = new Song();
        s.setTitle("");
        songlist.getSongList().add(s);
        String payload = gson.toJson(songlist);
        MvcResult result = mockSlController
                .perform(post("/songsWS-sakvis/rest/songLists/").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void postEmptyWhiteSpaceTitleInSongOfSongList() throws Exception {
        Song s = new Song();
        s.setTitle("  ");
        songlist.getSongList().add(s);
        String payload = gson.toJson(songlist);
        MvcResult result = mockSlController
                .perform(post("/songsWS-sakvis/rest/songLists/").header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + this.token).content(payload))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void deleteAOwnSongLists() throws Exception {

        String payload = gson.toJson(songlist);
        MvcResult result = mockSlController.perform(delete("/songsWS-sakvis/rest/songLists/1")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .principal(principal)
                .content(payload)).andExpect(status().isNoContent()).andReturn();
        Type listType = new TypeToken<ArrayList<SongList>>() {
        }.getType();
        // List<SongList> list = gson.fromJson(result.getResponse().getContentAsString(), listType);
        // 4 weil before each 2x ausgeführt wird
        //assertEquals(list.size(), 3);
    }


}