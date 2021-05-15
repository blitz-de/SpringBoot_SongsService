package htwb.ai.model;
import javax.persistence.*;
@Entity @Table(name="songs")
public class Song {

    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100)
    private String artist;

    @Column(length = 100)
    private String label;
    private int released;

    @Id @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }
}
