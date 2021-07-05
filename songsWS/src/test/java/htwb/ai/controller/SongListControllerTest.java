package htwb.ai.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.security.Principal;

import htwb.ai.config.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SongListControllerTest {

    private MockMvc mockUserController;

    private MockMvc mockSlController;
    private MockMvc mockSongController;
    private MockMvc mockAuthController;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepo uRepo;
    @Autowired
    private SongListRepo slRepo;
    @Autowired
    private SongRepo sRepo;

    @Autowired
    private JwtUserDetailsService jwtUser;

    SongList songlist1;
    SongList songlist2;
    SongList songlist3;
    SongList songlist4;
    private Gson gson;
    private Users user1 = new Users();
    private Users user2= new Users();

    Principal securityUser;

    String token = "";

    @BeforeEach
    public void setupMockMvc() {
        this.gson = new Gson();
//        securityUser = getPrincipal();

        mockUserController = MockMvcBuilders.standaloneSetup(new UserController(uRepo)).build();
        mockSlController = MockMvcBuilders.standaloneSetup(new SongListsController(slRepo)).build();
        mockSongController = MockMvcBuilders.standaloneSetup(new SongController(sRepo)).build();
        mockAuthController = MockMvcBuilders.standaloneSetup(new JwtAuthenticationController(jwtUser)).build();


//        user1 = new UserDTO("mmuster","Bobby","Smith","pass1234");
        user1.setUsername("helloWorld");
        user1.setFirstname("Bobby");
        user1.setLastname("Smith");
        user1.setPassword("pass1234");


        user2.setUsername("worldHello");
        user2.setFirstname("Bobby");
        user2.setLastname("Smith");
        user2.setPassword("pass1234");


        songlist1 = new SongList("songlist no1", false, user1);
        songlist2 = new SongList("songlist no2", true, user1);

        //songlist3 = new SongList("songlist no3", false, user2);
        //songlist4 = new SongList("songlist no4", true, user2);

        try {
            //slRepo.save(songlist1);
            //slRepo.save(songlist2);

            Users u1 = uRepo.save(user1);
            uRepo.flush();
            u1.getSongLists().add(songlist1);
            u1.getSongLists().add(songlist2);
            uRepo.save(u1);
            uRepo.flush();
            token = jwtTokenUtil.createToken(user1.getUsername());
            System.out.println("### token -->"+ token);

        } catch (Exception e) {
            System.out.println("### Save Exception -->" + e.getCause());
        }

    }

    @Test
    public void getForbiddenSongList() throws Exception {
        String payload = gson.toJson(user1);
        // get songlist:
        MvcResult result = mockSlController.perform(get("/songsWS-sakvis/rest/songLists/1").header("Content-Type", "application/json").
                header("Authorization", "Bearer "+token).
                content(payload)).andReturn();

        // get song:
        /*MvcResult result = mockSongController.perform(get("/songsWS-sakvis/rest/songs/1").header("Content-Type", "application/json").
                header("Authorization", "Bearer "+token).
                content(payload)).andReturn();*/
        System.out.println("### result --> " + result.getResponse().getContentAsString());
        // ToDo: check status forbidden
    }




}
