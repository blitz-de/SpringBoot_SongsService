package htwb.ai.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import htwb.ai.dao.SongListRepo;
import htwb.ai.dao.UserRepo;
import htwb.ai.exception.ResourceNotFoundException;
import htwb.ai.model.SongList;
import htwb.ai.model.Users;
import htwb.ai.model.Song;
@RestController
@RequestMapping(value = "songsWS/rest/songLists")
public class SongListsController {

	@Autowired
	private SongListRepo songListRepo;
	@Autowired
	private UserRepo userRepo;
	
    public SongListsController(SongListRepo dao) {
        this.songListRepo = dao;

    }
    
    @GetMapping(value="/{username}")
    public List<SongList> getSongList(@PathVariable("username") String username){
    	//Optional<SongList> songlist = songListRepo.findByOwner(userId);
    	List<SongList> songlists= (List<SongList>) songListRepo.findByOwner(username);
    	return songlists;
    }
    
//    @GetMapping(value="/{username}")
//    public ResponseEntity<SongList> getSongList (@PathVariable(value="username") String username){
//    	
//    	// find by UserId and not by SongId
//    	List<SongList> songList = songListRepo.findByOwner(username);
//    	
//    	return new ResponseEntity<SongList>((SongList) songList, HttpStatus.ACCEPTED);
//    }
    
//    @Transactional/?isername=mmuster
    @PostMapping(value = "/{username}", consumes = { "application/json" }, produces = "application/json")
    public ResponseEntity<SongList> postSongList(@RequestBody SongList songlist,
    		@PathVariable(value="username") String username) {
    	
    	Users user = userRepo.findByUsername(username);
 
    	songlist.setOwner(user);
    	SongList list =songListRepo.save(songlist);
    	songListRepo.flush();
    	
    	return new ResponseEntity<SongList>(list,
    			HttpStatus.ACCEPTED);
//        return userRepo.findByUsername(username).map(user -> {
//        	songlist.setOwner(user);;
//            return songListRepo.save(songlist);
//        }).orElseThrow(() -> new ResourceNotFoundException("username " + username + " not found"));
        // if (song.get != null && ))
//    	songListRepo.save(songlist);
//    	
//    	return new ResponseEntity<SongList>(songlist, HttpStatus.OK);

    }
    
//    @Transactional
    @JsonIgnore
    @GetMapping(value="/all")
    public List<SongList> getAllSongLists(){
    	return songListRepo.findAll();
    }
    
//    @PutMapping("/{username}/")
}
