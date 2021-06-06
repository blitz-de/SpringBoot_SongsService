package htwb.ai.dao;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import htwb.ai.model.Song;
import org.junit.jupiter.api.Test;

public class TestSongDAO implements ISongDAO {

    private Map<Integer, Song> mySongs = new ConcurrentHashMap<Integer, Song>();

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
        mySongs.remove(mySongs.get(id));
    }

    @Override
    public Integer generateId() {
        // TODO Auto-generated method stub
        return mySongs.size()+1;
    }

    @Test
    public void testSize(){
        TestSongDAO dao = new TestSongDAO();
        assertEquals(dao.mySongs.size(), this.generateId()-1);
    }

}
