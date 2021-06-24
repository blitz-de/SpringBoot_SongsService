package htwb.ai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="songs")
public class Song implements Serializable {

    private static final long serialVersionUsongId = 1L;

    @Override
	public String toString() {
		return "Song [songId=" + songId + ", title=" + title + ", artist=" + artist + ", album=" + album + ", released="
				+ released + "]";
	}
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer songId;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "artist", length = 100)
    private String artist;
    @Column(name = "album", length = 100)
    private String album;
    @Column(name = "released")
    private Integer released;


    private Song(Builder builder) {
        this.songId = builder.songId;
        this.title = builder.title;
        this.artist = builder.artist;
        this.album = builder.album;
        this.released = builder.released;
    }
    public Song(Integer songId, String title, String artist, String album, Integer released) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.released = released;
    }

    public Song() {
    }


    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
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
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link Song}.
     */
    public static final class Builder {
        private Integer songId;
        private String title;
        private String artist;
        private String album;
        private Integer released;

        private Builder() {
        }

        public Song.Builder withSongId(Integer songId) {
            this.songId = songId;
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
