package htwb.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import htwb.ai.controller.SongListsController;
import htwb.ai.dao.SongListRepo;
import htwb.ai.dao.SongRepo;
import htwb.ai.dao.UserRepo;
import htwb.ai.model.Users;
import htwb.ai.model.Song;
import htwb.ai.model.SongList;

@SpringBootApplication
public class SpringBootHelloWorldApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
	
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private SongRepo songRepository;
	
	@Autowired
	private SongListsController sc;
	
	@Autowired
	private SongListRepo songlistRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public void run(String... args) throws Exception {
		
//		DAOUser user1 = new DAOUser("mmuster", "pass1234", "Max", "Muster");
		Users user1 = Users.builder().withUsername("mmuster").withPassword("pass1234").withFirstname("Max").withLastname("Muster").build();
		user1.setPassword(bcryptEncoder.encode(user1.getPassword()));
		
		Users user2 = new Users("eschuler", "pass1235", "Elena", "Schuler");
		user2.setPassword(bcryptEncoder.encode(user2.getPassword()));
//		
		List<Song> songs = readJSONToSongs("src/main/resources/songs.json");
		
//		List<SongList> songlistjson = readJSONToSongList("src/main/resources/songList.json");
		
		
		songRepository.saveAll(songs);
		this.userRepository.save(user1);
		this.userRepository.save(user2);
		
//		songlistRepository.saveAll(songlistjson);
		
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