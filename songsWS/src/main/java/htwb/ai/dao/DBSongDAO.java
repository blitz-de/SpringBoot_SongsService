//package htwb.ai.dao;
//
//import htwb.ai.model.Song;
//
//import javax.persistence.*;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class DBSongDAO implements ISongDAO {
//    EntityManagerFactory emf;
//    EntityManager em;
//    String pUnit;
//
//    public DBSongDAO(String pUnit) {
//////        this.emf = Persistence.createEntityManagerFactory(pUnit);
//        this.emf = Persistence.createEntityManagerFactory(pUnit);
//////        this.pUnit = pUnit;//
//        System.out.println("########################################");
//        System.out.println("########################################");
//
////            this.initDB();
//        List<Song> list = getAll();
//
//
//        System.out.println("########################################");
//        System.out.println("########################################");
//        if (list.size() == 0) {
//            this.initSongs();
//            emf.close();
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
//            return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>() {
//            });
//        }
//    }
//
//    private boolean initSongs() {
//        try {
////            this.emf = Persistence.createEntityManagerFactory(this.pUnit);
////            SongsDao dao = new SongsDao(this.emf);
//            List<Song> songs = readJSONToSongs("src/main/resources/songs.json");
//
//            for (Song song : songs) {
//                System.out.println();
//                System.out.println("song -> " + song.toString());
//                Integer jsonId = song.getId();
//                //song.setId(null);
//
//                Integer oldId = save(song); //uses save (unten)
//                // here set id
//                System.out.println("###################");
//                System.out.println("id -> " + oldId + ",json id -> " + jsonId);
//                //dao.replaceId(oldId, jsonId);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
////    private boolean initDB() {
////
////
////        User u1 = new User("mmuster", "pass1234", "Maxime", "Muster");
////        User u2 = new User("eschuler", "pass1234", "Elena", "Schuler");
////
////        List<User> users = new ArrayList<>();
////        users.add(u1);
////        users.add(u2);
////        for (User user : users) {
////            System.out.println();
////            System.out.println("[init db] user -> " + user.toString());
////            String jsonId = user.getUserId();
////            //song.setId(null);
////
////            String oldId = save(user);
////            // here set id
////            System.out.println("###################");
////            System.out.println("id -> " + oldId + ",json id -> " + jsonId);
////            //dao.replaceId(oldId, jsonId);
////        }
////
////    return true;
////}
//
//    @Override
//    public Song getById(int id) {
//        EntityManager em = null;
//        try {
//            em = emf.createEntityManager();
//            return em.find(Song.class, id);
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    @Override
//    public List<Song> getAll() {
//        em = emf.createEntityManager();
//        String strQuery = "SELECT s FROM Song s WHERE s.id is NOT NULL";
//        TypedQuery<Song> tq = em.createQuery(strQuery, Song.class);
//        List<Song> songs;
//        try {
//            songs = tq.getResultList();
//            songs.forEach(song -> System.out.println(song.toString()));
//            return songs;
//        } catch (NoResultException ex) {
//            ex.printStackTrace();
//        } finally {
//            em.close();
//        }
//        return null;
//    }
//
//    public Integer generateId() {
//        String hql = "SELECT s FROM Song s WHERE s.id IN (SELECT MAX(s.id) FROM Song s)";
//        em = emf.createEntityManager();
//        Query query = em.createQuery(hql);
//        List<Song> results = query.getResultList();
//
//        int key = results.get(0).getId() + 1;
//        return key;
//    }
//
//    @Override
//    public Song getById(String userId) {
//        em = null;
//        try {
//            em = emf.createEntityManager();
//            return em.find(Song.class, userId);
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    @Override
//    public Integer save(Song song) {
//        EntityManager em;
//        em = null;
//        EntityTransaction transaction = null;
//        if (song.getId() == null) song.setId(this.generateId());
//
//
////        User sakhr =
////        User.builder().withId(1).withUserId("sakhr").withFirstname("sakhr").withLastname("nabil").withPassword("1234").build();
//        try {
//            em = emf.createEntityManager();
//            transaction = em.getTransaction();
//            transaction.begin();
//            em.merge(song);
//            // em.flush();
//            transaction.commit();
//            return song.getId();
//
//        } catch (IllegalStateException | EntityExistsException | RollbackException ex) {
//            System.out.println("#############################################");
//            System.out.println("exception in tomcat log ->" + ex.getMessage());
//            if (em != null) {
//                em.getTransaction().rollback();
//            }
//            throw new PersistenceException(ex.getMessage());
//
//        } finally {
//            em.close();
//            if (song != null)
//                return song.getId();
//            else return null;
//        }
//    }
//
//    @Override
//    public Integer update(Song song) {
//        EntityManager em;
//        em = null;
//        EntityTransaction transaction = null;
//        try {
//            em = emf.createEntityManager();
//            transaction = em.getTransaction();
//            transaction.begin();
//            em.merge(song);
//            transaction.commit();
//            return song.getId();
//
//        } catch (IllegalStateException | EntityExistsException | RollbackException ex) {
//            System.out.println("#############################################");
//            System.out.println("exception in tomcat log ->" + ex.getMessage());
//            if (em != null) {
//                em.getTransaction().rollback();
//            }
//            throw new PersistenceException(ex.getMessage());
//
//        } finally {
//            em.close();
//            if (song != null)
//                return song.getId();
//            else return null;
//        }
//
//    }
//    @Override
//    public void delete(Integer songId) {
//        EntityTransaction transaction = null;
//        em = null;
//
//        em = emf.createEntityManager();
//        transaction = em.getTransaction();
//        transaction.begin();
//        Query query = em.createQuery("DELETE FROM Song s WHERE s.id = :s");
//        query.setParameter("s",songId);
//        query.executeUpdate();
//        transaction.commit();
//
//    }
//
//    public void closeEMF() {
//        if (this.emf != null) {
//            emf.close();
//        }
//    }
//}
