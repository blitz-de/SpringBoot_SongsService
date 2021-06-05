package htwb.ai.dao;

<<<<<<< HEAD
import htwb.ai.model.Song;
import htwb.ai.model.User;

import javax.persistence.*;
import java.util.List;

public class DBSongDAO implements ISongDAO{
    EntityManagerFactory emf;
    EntityManager em;


    public DBSongDAO(String pUnit) {
        this.emf = Persistence.createEntityManagerFactory(pUnit);

    }
    @Override
    public Song getById(int id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Song.class, id);
        } finally {
            if (em != null) {
                em.close();
                return null;
            }
        }
    }

    @Override
    public List<Song> getAll() {
        em = emf.createEntityManager();
        String strQuery = "SELECT u FROM User u WHERE u.id is NOT NULL";
        TypedQuery<Song> tq = em.createQuery(strQuery, Song.class);
        List<Song> songs;
        try {
            songs = tq.getResultList();
            songs.forEach(song -> System.out.println(song.toString()));
            return songs;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Song getById(String userId) {
        em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Song.class, userId);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Integer save(Song song) {
        EntityManager em;
        em = null;
        EntityTransaction transaction = null;

//        User sakhr =
//        User.builder().withId(1).withUserId("sakhr").withFirstname("sakhr").withLastname("nabil").withPassword("1234").build();
        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(song);
            // em.flush();
            transaction.commit();
            return song.getId();

        } catch (IllegalStateException | EntityExistsException | RollbackException ex) {
            System.out.println("#############################################");
            System.out.println("exception in tomcat log ->" + ex.getMessage());
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException(ex.getMessage());

        } finally {
            em.close();
            if (song != null)
                return song.getId();
            else return null;
        }
    }

    @Override
    public void update(Song User) {

    }

    @Override
    public void delete(String userId) {

    }
    public void closeEMF() {
        if (this.emf != null) {
            emf.close();
        }
    }
=======
import java.util.List;

import htwb.ai.model.User;

public class DBSongDAO implements ISongDAO 	{

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User User) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

>>>>>>> 16e1edb768ceb69cb7bcdc92a9371b85b2480a0d
}
