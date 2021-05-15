package htwb.ai.model;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

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
    private Integer id;
    public Song(Integer id, String title, String artist, String label, Integer released){

    }
    public Song(){

    }

    public static String values() {
        return "";
    }

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
