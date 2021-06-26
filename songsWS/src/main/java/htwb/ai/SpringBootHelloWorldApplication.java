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

import htwb.ai.dao.SongListRepo;
import htwb.ai.dao.SongRepo;
import htwb.ai.dao.UserRepo;
import htwb.ai.model.DAOUser;
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
	private SongListRepo songlistRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public void run(String... args) throws Exception {
		
//		DAOUser user1 = new DAOUser("mmuster", "pass1234", "Max", "Muster");
		DAOUser user1 = DAOUser.builder().withUsername("mmuster").withPassword("pass1234").withFirstname("Max").withLastname("Muster").build();
		user1.setPassword(bcryptEncoder.encode(user1.getPassword()));
		
		DAOUser user2 = new DAOUser("eschuler", "pass1235", "Elena", "Schuler");
		user2.setPassword(bcryptEncoder.encode(user2.getPassword()));
//		
		List<Song> songs = readJSONToSongs("src/main/resources/songs.json");
		
	
		songRepository.saveAll(songs);
		this.userRepository.save(user1);
		this.userRepository.save(user2);
//		for (Song song: songs) {
//			System.out.println();
////			Integer jsonId = song.getSongId();
//			
//			
//			songRepository.save(song);
//			
//			songRepository.saveAll(songs);
//			System.out.println("########################" +song.getSongId());
//			
//		}
//		// Init 10 songs from songs.json
			SongList songlist1 = new SongList("Forro music", true);
			SongList songlist2 = new SongList("Workout music", false);
//////		SongList songlist3 = new SongList("Concentration music", false);
//////		SongList songlist4 = new SongList("Pary music", false);
//////		u
//			songlistRepository.save(songlist1);
//			songlistRepository.save(songlist2);
//			user1.setSongLists(songlist1);
			user1.getSongLists().add(songlist1);
			user1.getSongLists().add(songlist2);
			List<SongList> list = user1.getSongLists();
//			songlistRepository.save(list);
			
//			user1.getSongLists().add(songlist2);
////		user.getSongLists().add(songlist3);
////		user.getSongLists().add(songlist4);
		

	}
	
    @SuppressWarnings("unchecked")
    public static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>() {
            });
        }
    }

}