package htwb.ai.model;

import java.io.Serializable;

import javax.persistence.*;
@Table(name = "songs")
@Entity
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique =true, nullable=false)
    private Integer id;
    @Column(name="title", length = 100, nullable = false)
    private String title;
    @Column(name="artist", length = 100)
    private String artist;
    @Column(name="label", length = 100)
    private String label;
    @Column(name="released")
    private int released;

    public Song(Integer id, String title, String artist, String label, Integer released) {

    }

    public Song() {

    }

    public String values() {
        return "'value':'test'";
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
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
