package htwb.ai.dao;

import java.math.BigInteger;
import java.sql.*;
import java.util.List;

import javax.persistence.*;

import htwb.ai.model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.dialect.internal.StandardDialectResolver;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.type.StandardBasicTypes;


public class SongsDao {
    EntityManagerFactory emf;
    EntityManager em;

    public SongsDao(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public Integer save(Song song) throws PersistenceException {

        em = null;
        EntityTransaction transaction = null;

        try {
            em = emf.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(song);

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

    public Song find(int id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Song.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @SuppressWarnings("unchecked")
    public List<Song> findAll() {
        em = null;
        try {
            em = emf.createEntityManager();
            return em.createQuery("select a from SONGS a", Song.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public Song getSong(int id) {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT s FROM Song s WHERE s.id = :id";

        TypedQuery<Song> tq = em.createQuery(query, Song.class);
        tq.setParameter("id"
                + "", id);
        Song song = new Song(); //war null
        try {
            song = tq.getSingleResult();
//			System.out.println("=====================================================");
//			System.out.println("artist: " + song.getArtist() + " id: " + song.getId() +"label: "+song.getLabel());
            return song;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public List<Song> getSongs() {
        EntityManager em = emf.createEntityManager();
        String strQuery = "SELECT s FROM Song s WHERE s.id is NOT NULL";
        TypedQuery<Song> tq = em.createQuery(strQuery, Song.class);
        List<Song> songs;
        try {
            songs = tq.getResultList();
            songs.forEach(song -> System.out.println(song.toSting()));
            return songs;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public void replaceId(Integer oldId, Integer id) {
        EntityManager em = emf.createEntityManager();

        if (oldId != null && id != null && oldId != id) {
            Song s = getSong(oldId);

            em.getTransaction().begin();
            s.setId(id);
            em.persist(s);
            em.getTransaction().commit();

            em.close();
        }

    }


        public Integer getFreeId() {
            String hql = "SELECT s FROM Song s WHERE s.id IN (SELECT MAX(s.id) FROM Song s)";
            em = emf.createEntityManager();
            Query query = em.createQuery(hql);
            List<Song> results = query.getResultList();

            int key = results.get(0).getId()+1;

            //Query q = em.createNativeQuery("select seq_name.nextval from Songs");
           // return (Integer)q.getSingleResult();
            return key;
        }
}
