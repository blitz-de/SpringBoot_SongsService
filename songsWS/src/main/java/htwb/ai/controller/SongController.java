package htwb.ai.controller;


import htwb.ai.dao.ISongDAO;
import htwb.ai.dao.IUserDAO;
import htwb.ai.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/songs")
public class SongController {
    private ISongDAO songDAO;
    public SongController(ISongDAO dao){
        this.songDAO = dao;

    }
    //GET http://localhost:8080/authSpring/rest/auth/1
    @GetMapping(value="/{id}")
    public ResponseEntity<Song> get(
            @PathVariable(value="id") Integer id) throws IOException {
        Song user = songDAO.getById(id);
        if (user != null) {
            return new ResponseEntity<Song>(user, HttpStatus.OK);
        }
        return new ResponseEntity<Song>(user, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes= {"application/json"})
    public Song add(@RequestBody Song user) {
        songDAO.save(user);
        return user;
    }

    @GetMapping(value="/songs")
    public List<Song> getAll(){
        return songDAO.getAll();
    }
}
