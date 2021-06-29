package htwb.ai.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.config.WebSecurityConfig;
import htwb.ai.controller.SongListsController;
import htwb.ai.model.Song;
import htwb.ai.model.SongList;
import htwb.ai.model.Users;
import htwb.ai.repository.SongListRepo;
import htwb.ai.repository.SongRepo;
import htwb.ai.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.*;
import java.util.List;
public class Initer {

    public Initer(){
        try {
            startHelper();
        } catch(Exception e){
            System.out.println("#############################");
            System.out.println("#############################");
            System.out.println("#############################");
            System.out.println("#############################");
            System.out.println("#############################");
            System.out.println("#############################");
            System.out.println("#############################");
        }
    }
    @Autowired(required = true)
    private UserRepo userRepository;
    @Autowired
    private SongRepo songRepository;
    @Autowired
    private SongListsController sc;
    @Autowired
    private SongListRepo songlistRepository;
    //	@Autowired(required = true)
//	private PasswordEncoder bcryptEncoder;
    @Autowired
    WebSecurityConfig web;

    public void startHelper() throws Exception {

        Users user1 = new Users("mmuster", "pass1234", "Max", "Muster");
        user1.setPassword(web.passwordEncoder().encode(user1.getPassword()));
        Users user2 = new Users("eschuler", "pass1234", "Elena", "Schuler");
        user2.setPassword(web.passwordEncoder().encode(user2.getPassword()));
//
        List<Song> songs = readJSONToSongs("src/main/resources/songs.json");

        List<SongList> songlistjson = readJSONToSongList("src/main/resources/songList.json");

        int i = 1;
        for (SongList st : songlistjson) {
            if(i==1 || i==2) st.setOwner(user1);
            else st.setOwner(user2);
            i++;
        }
        songlistjson.get(0).setIsPrivate(true);
        songlistjson.get(1).setIsPrivate(false);
        songlistjson.get(2).setIsPrivate(true);
        songlistjson.get(3).setIsPrivate(false);


        // 1

        //user.songlist1.setPassword(bcrypt);

//		songRepository.saveAll(songs);
        //this.userRepository.save(user1);
        //this.userRepository.save(user2);

        songlistRepository.saveAll(songlistjson);

        SongList sl1 = songlistRepository.getById(1);

        SongList songlist1 = new SongList("Forro music", false, user1);
        SongList songlist2 = new SongList("Workout music", true, user1);

        SongList songlist3 = new SongList("Rock and roll", true, user2);
        SongList songlist4 = new SongList("Salsa", false, user2);

        user1.getSongLists().add(songlist1);
        user1.getSongLists().add(songlist2);

        user2.getSongLists().add(songlist3);
        user2.getSongLists().add(songlist4);

        this.userRepository.save(user1);
        this.userRepository.save(user2);

    }

    @SuppressWarnings("unchecked")
    public static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>() {
            });
        }
    }

    @SuppressWarnings("unchecked")
    public static List<SongList> readJSONToSongList(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<SongList>) objectMapper.readValue(is, new TypeReference<List<SongList>>() {
            });
        }
    }
}
