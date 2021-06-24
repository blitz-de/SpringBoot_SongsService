package htwb.ai.dao;

import org.springframework.data.repository.CrudRepository;

import htwb.ai.model.Song;

public interface SongRepo extends CrudRepository<Song, Integer> {

	Song findBySongId(Integer songId);

}
