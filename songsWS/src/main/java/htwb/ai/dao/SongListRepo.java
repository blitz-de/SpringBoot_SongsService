package htwb.ai.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htwb.ai.model.Users;
import htwb.ai.model.SongList;

@Repository
public interface SongListRepo extends JpaRepository<SongList, Integer> {

//	@Query("from DAOUser u inner join fetch songList where u.username = :owner")
	// (songList -> Owner --> SongListen 
//	@Query("from SongList s inner join DAOUser u fetch s.owner where s.owner = :username")
	//@Query("select s from SongList where s.users = :owner")
	//Query("Select a from SongList a inner join Users b on owner=b.username")
//	@Query("from SongList s inner join fetch ")
	List<SongList> findByOwner(Users user);
	

	
}
