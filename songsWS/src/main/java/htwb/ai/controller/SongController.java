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

    /*
     *         System.out.println("################### new post ######################");
        String artist = request.getParameter("artist");
        String label = request.getParameter("label");
        response.setContentType("application/json");
        if (checkParamsPost(request)) {
            try {
                String title = request.getParameter("title");
                String released = request.getParameter("released");
                System.out.println("released -> " + released);
                if (title != null && !title.equals("")) {

                    create(response, title, artist, label, released);
                } else {
                    sendResponse("set title to create new song", response, HttpServletResponse.SC_NOT_ACCEPTABLE);
                }

            } catch (NumberFormatException | NullPointerException e) {
                // return bad request 400 or something with wrong format
                sendResponse("", response, HttpServletResponse.SC_BAD_REQUEST);
                e.printStackTrace();
            }
        } else {
            sendResponse("", response, HttpServletResponse.SC_BAD_REQUEST);
        }
     */
    @PostMapping(value = "/", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Song> add(@RequestBody Song song) {
//    	if (song.get != null && ))
        List<Song> list = songDAO.getAll();
        //songDAO.getById(song.getId());
        System.out.println("################################# " + song.getId());
        for (Song s : list) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`` " + s.getId());
            if (song.getId() != null
                    && song.getId() < this.songDAO.generateId()
            ) return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
            else if (song.getTitle() == null || song.getTitle().equals("")) {
                return new ResponseEntity<Song>(song, HttpStatus.CONFLICT);
            } else {
                System.out.println("========================================================= " + s.getId());
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Location",
                        "rest/songs/" + songDAO.save(song));
                return new ResponseEntity<Song>(song, responseHeaders, HttpStatus.CREATED);
            }
        }
        return null;
//        songDAO.save(song);
//        //
        // return new Resp;
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<Song> updateSong(@RequestBody Song song, @PathVariable Integer id) {

        // check songId !=null -> bad request
        // check song.id != null -> bad request
        // check songId == song.id -> continue update

        if (id == null) {
            return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
            // check songId !=null -> bad request
            // check song.id != null -> bad request
            // check songId == song.id -> continue update
        }
        if (song.getId() == null)
            return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
        if (song.getId() != Integer.valueOf(id))
            return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
        //songDAO.getById(song.getId());
        System.out.println("[onUpdate]: " + song.getId());
        if ((song.getId() == null || song.getId() < 1) && song.getId() < this.songDAO.generateId()
        ) return new ResponseEntity<Song>(song, HttpStatus.BAD_REQUEST);
        else if (song.getTitle() == null || song.getTitle().equals("")) {
            return new ResponseEntity<Song>(song, HttpStatus.CONFLICT);
        } else {
            songDAO.update(song);
            return new ResponseEntity<Song>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"text/plain"})
    public ResponseEntity<String> deleteSong(
            @PathVariable(value = "id") Integer id) throws IOException {

        if (id > 0 && id < Integer.MAX_VALUE && id != null) {
            songDAO.delete(id);
            return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public List<Song> getAll() {
        return songDAO.getAll();
    }
}
