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
    private Gson gson;
    private Users user1 = new Users();

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

        songlist1 = new SongList("songlist no1", false, user1);
        try {
            slRepo.save(songlist1);
            token = jwtTokenUtil.createToken(user1.getUsername());
            System.out.println("### token -->"+ token);

        } catch (Exception e) {
            System.out.println("### Save Exception -->" + e.getCause());
        }

    }

    @Test
    public void postSongListMethodNotFound() throws Exception {
        String payload = gson.toJson(user1);
        MvcResult result = mockSongController.perform(get("/songsWS-sakvis/rest/songs/1").header("Content-Type", "application/json").
                header("Authorization", "Bearer "+token).
                content(payload)).andReturn();
        System.out.println("### result --> " + result.getResponse().getContentAsString());
    }

//    @Test
//    public void postSongListNotAuthorized() throws Exception {
//
//        User user = new User(user1.getUsername(),user1.getPassword(), AuthorityUtils.createAuthorityList("USER"));
////    	songListRepo.findById(1);
//        String payload = gson.toJson(songlist1);
//        //Principal securityUser = "mmuster";
//
//        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
//
//
//        mockMvc2.perform(post("/songsWS-sakvis/rest/songLists/").content(payload)
//        		.principal(testingAuthenticationToken)
//        		.contentType(MediaType.APPLICATION_JSON))
//        		.andExpect(status().isForbidden());
//
////        mockMvc2.perform(post("/partner/notifications/activate")
////                .content(payload)
////                .principal(securityUser)
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//    }

    @Test
    public void postSongListBadRequest() throws Exception {
       /* String payload = gson.toJson(songlist1);
        User user = new User(user1.getUsername(), user1.getPassword(), AuthorityUtils.createAuthorityList("USER"));

        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user, null);


        mockMvc.perform(post("/songsWS-sakvis/rest/songLists").content(payload)
                .principal(testingAuthenticationToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
                */
//        mockMvc2.perform(post("/partner/notifications/activate")
//                .content(payload)
//                .principal(securityUser)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }

    @Test
    @Order(1)
    public void postUserShouldSaveUserAndReturnNewId2() throws Exception {
    /*
        System.out.println("$$$$$$$$$$$$$ "+ user1);
        String payload = gson.toJson(user1);
        System.out.println(payload);
        MvcResult result = mockMvc4.perform(MockMvcRequestBuilders.post("/songsWS-sakvis/rest/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)).andReturn();

        System.out.println("######################################### "+ result.getResponse().getContentAsString());
        System.out.println("######################################### "+ result.getResponse().getStatus());
        user1.setPassword("pass1234");
        System.out.println("User -> "+user1.toString());
        String payload2 = gson.toJson(user1);
        System.out.println(payload2);
        MvcResult result2 = mockMvc4.perform(MockMvcRequestBuilders.post("/songsWS-sakvis/rest/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload2)).andReturn();

        System.out.println("######################################### "+ result2.getResponse().getContentAsString());
        System.out.println("######################################### "+ result2.getResponse().getStatus());


        MvcResult result3 = mockMvc.perform(MockMvcRequestBuilders.post("/songsWS-sakvis/rest/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)).andReturn();

        System.out.println("######################################### "+ result3.getResponse().getContentAsString());
        System.out.println("######################################### "+ result3.getResponse().getStatus());*/
    }

}
