package htwb.ai.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htwb.ai.model.DAOUser;
import htwb.ai.model.SongList;

@Repository
public interface SongListRepo extends JpaRepository<SongList, Integer> {

//	@Query("from DAOUser u inner join fetch songLists where u.username = :owner")
//	@Query("from SongList s inner join fetch s.owner where s.owner = :owner")
	List<SongList> findByOwner(@Param("owner") String owner);
	

	
}
