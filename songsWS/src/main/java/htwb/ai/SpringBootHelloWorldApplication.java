package htwb.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import htwb.ai.dao.UserRepo;
import htwb.ai.model.DAOUser;
import htwb.ai.model.SongList;

@SpringBootApplication
public class SpringBootHelloWorldApplication {//implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
	
//	@Autowired
//	private UserRepo userRepository;
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
//	@Override
//	public void run(String... args) throws Exception {
//		
//		DAOUser user = new DAOUser("mmuster", "pass1234", "Max", "Muster");
//		user.setPassword(bcryptEncoder.encode(user.getPassword()));
//		SongList songlist1 = new SongList("Forro music", true);
//		SongList songlist2 = new SongList("Workout music", false);
//		SongList songlist3 = new SongList("Concentration music", false);
//		SongList songlist4 = new SongList("Pary music", false);
//		
//		user.getSongLists().add(songlist1);
//		user.getSongLists().add(songlist2);
//		user.getSongLists().add(songlist3);
//		user.getSongLists().add(songlist4);
//		
//		this.userRepository.save(user);
//	}
}