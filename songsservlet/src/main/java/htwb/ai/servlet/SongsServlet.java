package htwb.ai.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import htwb.ai.dao.SongsDao;
import htwb.ai.model.Song;

//@WebServlet("/register")
public class SongsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    int id = -1;

    private EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "songsDB-PU";

    public SongsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        System.out.println("################### new get ######################");
        if (request.getParameterMap().containsKey("all")) {

        } else if (request.getParameterMap().containsKey("songId")) {

            int id = Integer.parseInt(request.getParameter("id"));

            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            SongsDao dao = new SongsDao(emf);
            Song song = dao.find(id);
            if (emf != null) {
                emf.close();
            }
        }
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FOUND);
            // TODO:
            // now parse song to json and send it in response back as payload
            out.flush();
            out.close();
        }
        response.getWriter().append("Server at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("################### new post ######################");
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String label = request.getParameter("label");

        Song song = new Song();
        try {
            int released = Integer.parseInt(request.getParameter("released"));
            song.setReleased(released);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        song.setTitle(title);
        song.setArtist(artist);
        song.setLabel(label);

        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            SongsDao dao = new SongsDao(emf);
            int id = dao.save(song);
            this.id = id;
        } catch (PersistenceException pex) {
            // TODO Auto-generated catch block
            this.id = -1;
            System.out.println("###ERROR### PersistenceException: " + pex.getMessage());
//		} finally {
//			if (emf != null ) {
//				emf.close();
//			}
        }

        try (PrintWriter out = response.getWriter()) {
            //fetcht alles was hinter dem Fragezeichen (die Parameter) im URL kommt


            response.setHeader("Location", "/songsservlet/songs?id= " + this.id);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            //out.write(id.intValue()+"");
            out.flush();
            out.close();
        }
    }
}