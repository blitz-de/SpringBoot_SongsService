package htwb.ai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import htwb.ai.model.Song;

@Repository
public interface SongRepo extends JpaRepository<Song, Integer> {

//	Song findBySongId(Integer songId);

}
