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

public class TestSongDAO  {

	SongRepo songRepo;
    private Map<Integer, Song> mySongs = new ConcurrentHashMap<Integer, Song>();
    
    public TestSongDAO() {
        Song song1 = Song.builder()
                .withId(1)
                .withTitle("to the moon")
                .withArtist("Frank Senatra")
                .withAlbum("Dancing")
                .withReleased(2021).build();        
//        Song song2 =  Song.builder().withId(2)
//                .withTitle("new song")
//                .withArtist("aaa")
//                .withAlbum("bbb")
//                .withReleased(1999).build();
        mySongs.put(song1.getSongId(),song1);
//        mySongs.put(song2.getId(), song2);
    }

//    @Override
//    public Song getSongId(int id) {
//        return this.mySongs.get(id); //gibt value zurueck
//    }
//
//    @Override
//    public List<Song> getAll() {
//        return new ArrayList<Song>(this.mySongs.values());
//    }
//
//    @Override
//    public Song getById(String userId) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Integer save(Song song) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Integer update(Song song) {
//    	
//        return null;
//    }
//
//    @Override
//    public void delete(Integer id) {
////        mySongs.remove(mySongs.get(id));
//    }
//
//    @Override
//    public Integer generateId() {
//        // TODO Auto-generated method stub
//        return mySongs.size()+1;
//    }

    @Test
    public void testSize(){
        TestSongDAO dao = new TestSongDAO();
//        assertEquals(dao.mySongs.size(), this.generateId()-1);
    }

}
