package htwb.ai.servlet;


import java.io.*;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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

    public String songToJson(Song song) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(song);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        System.out.println("################### new get ######################");
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        SongsDao dao = new SongsDao(emf);
        if (request.getParameterMap().containsKey("all")) {
            // 1.) get all songs
            List<Song> list = dao.findAll();
            // 2.) pack it to json - imported Gson library to convert List to JSON
            //String payload = SongsToJSON();
            String jsonPayload = new Gson().toJson(list);
            // 3.) response it
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonPayload);
            out.flush();
            
            //reponseString(payload, response, HttpServletResponse.SC_FOUND);

        } else if (request.getParameterMap().containsKey("songId")) {
            int id = Integer.parseInt(request.getParameter("songId"));
            Song song = dao.find(id);
            if (emf != null) {
                emf.close();
            }
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FOUND);
                //
                //out.append(song.getArtist()).append("test");
                response.getWriter().append(song.getArtist()).append(request.getContextPath());
                // now parse song to json and send it in response back as payload
                out.flush();
                out.close();
            }
            //response.getWriter().append("Server at: ").append(request.getContextPath());
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Song song = new Song();
        System.out.println("################### new post ######################");
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String label = request.getParameter("label");
        try {
            int released = Integer.parseInt(request.getParameter("released"));
            song.setReleased(released);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // set params
        song.setTitle(title);
        song.setArtist(artist);
        song.setLabel(label);

        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            SongsDao dao = new SongsDao(emf);
            int id = dao.save(song);
            this.id = id;
            try (PrintWriter out = response.getWriter()) {
                //fetcht alles was hinter dem Fragezeichen (die Parameter) im URL kommt


                response.setHeader("Location", "/songsservlet/songs?id= " + id);

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_CREATED);
                //out.write(id.intValue()+"");
                out.flush();
                out.close();
            }
        } catch (PersistenceException pex) {
            // TODO Auto-generated catch block
            System.out.println("###ERROR### PersistenceException: " + pex.getMessage());
//		} finally {
//			if (emf != null ) {
//				emf.close();
//			}
        }


    }

    @SuppressWarnings("unchecked")
    private static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>() {
            });
        }
    }

    public void reponseString(String payload, HttpServletResponse response, int status) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setStatus(status);
            out.write(payload);
            out.flush();
            out.close();
        }
    }

    private boolean initSongs() {
        try {
            this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            SongsDao dao = new SongsDao(this.emf);
            List<Song> songs = readJSONToSongs("songs.json");
            for (Song s : songs) {
                dao.save(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}