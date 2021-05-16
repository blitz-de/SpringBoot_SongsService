package htwb.ai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import htwb.ai.model.Song;

public class TestSystem {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("songsDB-PU");
	
	@PersistenceContext
	private static EntityManager em;
	
	public static void main(String[] args) {
		
		addSong(1, "Student", "Sakhr", "BMG", 2019);//String title, String artist, String label, Integer released
		addSong(2, "Singer", "Feras", "Sony", 2010);
		addSong(3, "Employee", "Hadeel", "Universal", 2009);
		addSong(4, "Character", "Micky", "Disney", 2020);
		
		System.out.println();
		getSong(1);
		getSongs();
		
		ENTITY_MANAGER_FACTORY.close();
	}
	
	public static void addSong(int id, String title, String artist, String label, Integer released) {
		em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		System.out.println("before adding");
		try {
			System.out.println("adding block");
			et = em.getTransaction();
			et.begin();
			Song song = new Song();
			System.out.println(title);
			song.setId(id);
			song.setTitle(title);
			song.setArtist(artist);
			song.setLabel(label);
			song.setReleased(released);
			em.persist(song);
			System.out.println("persised-------------------");
			et.commit();
			System.out.println("added successfully");
		} catch (Exception ex) { if( et != null) { et.rollback(); }
				ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static Song getSong(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Song s WHERE s.id = :id";
		
		TypedQuery<Song> tq = em.createQuery(query, Song.class);
		tq.setParameter("id"
				+ "", id);
		Song song = null;
		try {
			song = tq.getSingleResult();
//			System.out.println("=====================================================");
//			System.out.println("artist: " + song.getArtist() + " id: " + song.getId() +"label: "+song.getLabel());
			return song;
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		return song;
	}
	
	public static List<Song> getSongs() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//		EntityTransaction et = null;
		String strQuery = "SELECT s FROM Song s WHERE s.id is NOT NULL";
		TypedQuery<Song> tq = em.createQuery(strQuery, Song.class);
		
		List<Song> songs;
		try {
			songs = tq.getResultList();
			songs.forEach(song -> System.out.println(song.getArtist() + " " + song.getId()));
			return songs;
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
}
