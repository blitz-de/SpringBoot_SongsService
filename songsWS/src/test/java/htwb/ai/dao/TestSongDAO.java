package htwb.ai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import htwb.ai.model.Song;

public class TestSongDAO implements ISongDAO {

    private Map<Integer, Song> mySongs;
    
    public TestSongDAO() {
    	Song song = Song.builder()
    			.withId(1)
    			.withTitle("title")
    			.withArtist("Frank Senatra")
    			.withAlbum("alb")
    			.withReleased(1995).build();
    	
    	mySongs.put(song.getId(),song);
    }

	@Override
	public Song getById(int id) {
		return this.mySongs.get(id); //gibt value zurueck
	}

	@Override
	public List<Song> getAll() {
		return new ArrayList<Song>(this.mySongs.values());
	}

	@Override
	public Song getById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer generateId() {
		// TODO Auto-generated method stub
		return null;
	}

}
