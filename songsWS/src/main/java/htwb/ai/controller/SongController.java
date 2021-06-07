package htwb.ai.controller;

import htwb.ai.dao.ISongDAO;
import htwb.ai.model.Song;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/songs")
public class SongController {
    private ISongDAO songDAO;

    public SongController(ISongDAO dao) {
        this.songDAO = dao;

    }

    //GET http://localhost:8080/authSpring/rest/songs/1
    @GetMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Song> getSong(
            @PathVariable(value = "id") Integer id) throws IOException {
        Song song = songDAO.getById(id);
        if (id > 0 && id < Integer.MAX_VALUE && song != null) return new ResponseEntity<Song>(song, HttpStatus.OK);
        return new ResponseEntity<Song>(song, HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Song> add(@RequestBody Song song) {
//    	if (song.get != null && ))
        try {
            List<Song> list = songDAO.getAll();
            for (Song s : list) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`` " + s.getId());
                if (song.getId() != null
                        && song.getId() < this.songDAO.generateId()
                ) return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
                else if (song.getTitle() == null || song.getTitle().equals("")) {
                    return new ResponseEntity<Song>(song, HttpStatus.CONFLICT);
                } else {
                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("Location",
                            "rest/songs/" + songDAO.save(song));
                    return new ResponseEntity<Song>(song, responseHeaders, HttpStatus.CREATED);
                }
            }
            return null;

        } catch (Exception e){
            return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Song> updateSong(@RequestBody Song song, @PathVariable(value = "id") Integer id) {

        try {
            if (id == null) {
                return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
            }
            if (song.getId() == null)
                return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
            if (song.getId() != Integer.valueOf(id))
                return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
            //songDAO.getById(song.getId());
            System.out.println("[onUpdate]: " + song.getId());
            if ((song.getId() == null || song.getId() < 1) && song.getId() < this.songDAO.generateId()
            ) return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
            // body and pathVar not equal -> 400 (should return 404)
            // not found
            if (songDAO.getById(id) == null) {
                System.out.println("################################################################" + songDAO.getAll().size());
                return new ResponseEntity<Song>(song, HttpStatus.NOT_FOUND);
            } else if (song.getTitle() == null || song.getTitle().equals("")) {
                return new ResponseEntity<Song>(song, HttpStatus.CONFLICT);
            } else {
                songDAO.update(song);
                return new ResponseEntity<Song>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"text/plain"})
    public ResponseEntity<String> deleteSong(
            @PathVariable(value = "id") Integer id) throws IOException {
        try {
            if (songDAO.getById(id) == null) return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
            if (id > 0 && id < Integer.MAX_VALUE && id != null) {
                songDAO.delete(id);
                return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public List<Song> getAll() {
        try{
            return songDAO.getAll();
        } catch (Exception e){
            return null;
        }
    }
}
