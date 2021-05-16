package htwb.ai.servlet;


import java.io.*;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.sun.istack.NotNull;
import htwb.ai.dao.SongsDao;
import htwb.ai.model.Song;

public class SongsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    int id = -1;

    private EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "songsDB-PU";

    public SongsServlet() {
        super();
        //readJSONToSongs("songs.json");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        List<Song> list = new SongsDao(emf).getSongs();
        System.out.println("########################################");
        System.out.println("how many songs in db? => " + list.size());
        System.out.println("########################################");
        if (list.size() == 0)
            this.initSongs();

        emf.close();
    }

    // GET SECTION
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        System.out.println("################### new get ######################");
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        SongsDao dao = new SongsDao(emf);
        if (request.getParameterMap().containsKey("all")) {

            this.getAll(dao, response);
        } else if (request.getParameterMap().containsKey("songId")) {
            int id = Integer.parseInt(request.getParameter("songId"));
            this.getSong(id, dao, response);
        }
    }

    private void getSong(int id, SongsDao dao, HttpServletResponse response) {
        Song song = dao.getSong(id);
        String jsonPayload = new Gson().toJson(song);
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(jsonPayload);
        out.flush();
        out.close();
    }

    private void getAll(SongsDao dao, HttpServletResponse response) {
        List<Song> list = dao.getSongs();
        // 2.) pack it to json - imported Gson library to convert List to JSON
        //String payload = SongsToJSON();
        String jsonPayload = new Gson().toJson(list);
        // 3.) response it
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(jsonPayload);
        out.flush();
        out.close();
    }
    //
    //
    // POST SECTION

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("################### new post ######################");
        String artist = request.getParameter("artist");
        String label = request.getParameter("label");
        try {

            String title = request.getParameter("title");
            int released = Integer.parseInt(request.getParameter("released"));
            System.out.println("test -> " + title);
            if (title != null && !title.equals("")) {

                create(response, title, artist, label, released);
            } else {
                sendResponse("-", response, HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        } catch (NumberFormatException | NullPointerException e) {
            // return bad request 400 or something with wrong format
            //sendResponse("", response, HttpServletResponse.SC_NOT_ACCEPTABLE);
            e.printStackTrace();
        }


    }

    // HELPER
    private void create(HttpServletResponse response, @NotNull String title, String artist, String label, Integer released) {
        try {
            Song song = new Song();
            song.setTitle(title);
            song.setArtist(artist);
            song.setReleased(released);
            song.setLabel(label);
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            SongsDao dao = new SongsDao(emf);

            Integer freeId = dao.getFreeId();
            song.setId(freeId);
                    Integer id = dao.save(song);
            try (PrintWriter out = response.getWriter()) {
                if (id != null) {
                    //fetcht alles was hinter dem Fragezeichen (die Parameter) im URL kommt
                    response.setHeader("Location", "/songsservlet/songs?id= " + id);
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_CREATED);
                    out.flush();
                } else {
                    sendResponse("", response, HttpServletResponse.SC_CONFLICT);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (PersistenceException pex) {
            // TODO Auto-generated catch block
            System.out.println("###ERROR### PersistenceException: " + pex.getMessage());
        } finally {
            if (emf != null) {
                emf.close();
            }
        }

    }

    public void sendResponse(String payload, HttpServletResponse response, int status) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setStatus(status);
            out.write(payload);
            out.flush();
        } finally {
            response.getWriter().close();
        }
    }

    private boolean initSongs() {
        try {
            this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            SongsDao dao = new SongsDao(this.emf);
            List<Song> songs = readJSONToSongs("src/main/resources/songs.json");

            for (Song song : songs) {
                System.out.println();
                System.out.println("song -> " + song.toSting());
                Integer jsonId = song.getId();
                //song.setId(null);

                Integer oldId = dao.save(song);
                // here set id
                System.out.println("###################");
                System.out.println("id -> "+oldId+ ",json id -> "+jsonId);
                //dao.replaceId(oldId, jsonId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>() {
            });
        }
    }
}