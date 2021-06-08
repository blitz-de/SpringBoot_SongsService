package htwb.ai.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.model.User;


public class DBUserDAO implements IUserDAO {

    // private static EMF emf;
    //private String persistenceUnit;
    EntityManagerFactory emf;
    EntityManager em;
    // public DBUserDAO(){}

//    public void setPersistenceUnit(String pUnit) {
//        System.out.println("I'm instanciated: " + pUnit);
//        
//        this.persistenceUnit = pUnit;
//    }

    public DBUserDAO(String pUnit) {
        this.emf = Persistence.createEntityManagerFactory(pUnit);

        System.out.println("########################################");
        System.out.println("########################################");

            this.initDB();


        System.out.println("########################################");
        System.out.println("########################################");

    }

    private boolean initDB() {


            User u1 = new User("mmuster", "pass1234", "Maxime", "Muster");
            User u2 = new User("eschuler", "pass1234", "Elena", "Schuler");

            List<User> users = new ArrayList<>();
            users.add(u1);
            users.add(u2);
            for (User user : users) {
                System.out.println();
                System.out.println("### [init db] user -> " + user.toString());
                String jsonId = user.getUserId();
                //song.setId(null);

                String oldId = save(user);
                // here set id
                System.out.println("###################");
                System.out.println("###################");


                System.out.println(user.toString());
                //dao.replaceId(oldId, jsonId);
            }

        return true;
    }

    @SuppressWarnings("unchecked")
    private static List<User> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<User>) objectMapper.readValue(is, new TypeReference<List<User>>() {
            });
        }
    }
    //    public User getUserById(int id) {
//        if (id == 1) {
//            User fred = User.builder()
//                    .withId(1)
//                    .withFirstname("FRED")
//                    .withLastname("Schmidt")
//                    .withUserId("fschmidt")
//                    .withPassword("geheim").build();
//            return fred;
//        }
//        return null;
//    }
//
    /*public User getUserById(int id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(User.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public User getUserByUserId(String userId) {
        em = null;
        if(userId==null) return null;
        try {
            em = emf.createEntityManager();
            return em.find(User.class, userId);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public List<User> getAllUsers() {
        em = emf.createEntityManager();
        String strQuery = "SELECT u FROM User u WHERE u.userId is NOT NULL";
        TypedQuery<User> tq = em.createQuery(strQuery, User.class);
        List<User> users;
        try {
            users = tq.getResultList();
            users.forEach(user -> System.out.println(user.toString()));
            return users;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

//    public User getUserByUserId(String userId) {
//        if (userId != null && userId.equals("fschmidt")) {
//            return User.builder()
//                    .withId(1)
//                    .withFirstname("FRED")
//                    .withLastname("Schmidt")
//                    .withUserId("fschmidt")
//                    .withPassword("geheim").build();
//        }
//        return null;
//    }

//    public Integer addUser(User User) {
//        // TODO Auto-generated method stub
//        return null;
//    }

    public String save(User user) throws PersistenceException {
        EntityManager em;
        em = null;
        EntityTransaction transaction = null;
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(user);
            // em.flush();
            transaction.commit();
            return user.getUserId();

        } catch (IllegalStateException | EntityExistsException | RollbackException ex) {
            System.out.println("#############################################");
            System.out.println("exception in tomcat log ->" + ex.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException(ex.getMessage());

        } finally {
            em.close();
            if (user != null)
                return user.getUserId();
            else return null;
        }
    }

    @Override
    public void updateUser(User User) {

    }


    /*public User updateUser(int id) {
        EntityManager em;
        em = null;
        EntityTransaction transaction = null;

        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            User foundUser = em.find(User.class, id);
            User editedUser = em.merge(foundUser);
            // em.flush();
            transaction.commit();
            return editedUser.getId();

        } catch (IllegalStateException | EntityExistsException | RollbackException ex) {
            System.out.println("#############################################");
            System.out.println("exception in tomcat log ->" + ex.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException(ex.getMessage());

        } finally {
            if (em != null)
                em.close();
        }
    }*/

    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
    }

    @Override
    public String generateToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }


    public void closeEMF() {
        if (this.emf != null) {
            emf.close();
        }
    }
}