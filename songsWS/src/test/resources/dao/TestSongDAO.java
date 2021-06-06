import java.util.List;

import htwb.ai.model.Song;

public class TestSongDAO {


    private List<Song> mySongs;
    
    public TestSongDAO() {
    	Song song = Song.builder()
    			.withId(1)
    			.withTitle("title")
    			.withArtist("Frank Senatra")
    			.withAlbum("alb")
    			.withReleased(1995).build();
    	
    	mySongs.add(song);
    }

}
