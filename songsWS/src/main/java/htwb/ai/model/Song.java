package htwb.ai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="songs")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Song [songId=" + id + ", title=" + title + ", artist=" + artist + "]";
    }
    @Id
    @Column(name = "songId", unique = true)
    private Integer id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "artist", length = 100)
    private String artist;
    @Column(name = "album", length = 100)
    private String album;
    @Column(name = "released")
    private Integer released;


    private Song(Song.Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.artist = builder.artist;
        this.album = builder.album;
        this.released = builder.released;
    }

    public Song() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum (String album) {
        this.album = album;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released=released;
    }
    /**
     * Creates builder to build {@link Song}.
     *
     * @return created builder
     */
    public static Song.Builder builder() {
        return new Song.Builder();
    }

    /**
     * Builder to build {@link Song}.
     */
    public static final class Builder {
        private Integer id;
        private String title;
        private String artist;
        private String album;
        private Integer released;

        private Builder() {
        }

        public Song.Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Song.Builder withTitle(String title) {
            this.title =title;
            return this;
        }

        public Song.Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Song.Builder withAlbum(String album) {
            this.album = album;
            return this;
        }

        public Song.Builder withReleased(Integer released){
            this.released = released;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
    }
}
