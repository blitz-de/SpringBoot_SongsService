package htwb.ai.model;


public class BadSong  {


    @Override
	public String toString() {
		return "BadSong [id=" + id + ", title=" + title + ", artist=" + artist + ", album=" + album + ", released="
				+ released + "]";
	}

    private String id;
    private String title;
    private String artist;
    private String album;

    private String released;


    private BadSong(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.artist = builder.artist;
        this.album = builder.album;
        this.released = builder.released;
    }
    public BadSong(String id, String title, String artist, String album, String released) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.released = released;
    }

    public BadSong() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released=released;
    }
    /**
     * Creates builder to build {@link BadSong}.
     *
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link BadSong}.
     */
    public static final class Builder {
        private String id;
        private String title;
        private String artist;
        private String album;
        private String released;

        private Builder() {
        }

        public BadSong.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public BadSong.Builder withTitle(String title) {
            this.title =title;
            return this;
        }

        public BadSong.Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public BadSong.Builder withAlbum(String album) {
            this.album = album;
            return this;
        }

        public BadSong.Builder withReleased(String released){
            this.released = released;
            return this;
        }

        public BadSong build() {
            return new BadSong(this);
        }
    }
}
