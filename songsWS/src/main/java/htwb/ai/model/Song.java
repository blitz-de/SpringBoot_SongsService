package htwb.ai.model;


public class Song {
    private int id;

    @Override
    public String toString() {
        return "Song [songId=" + id + ", title=" + title + ", artist=" + artist + "]";
    }


    private String title;
    private String artist;
    private String label;

    private Song(Song.Builder builder) {
        this.id = builder.id;
        this.title = builder.firstname;
        this.artist = builder.lastname;
        this.label = builder.password;
    }

    public Song() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstname() {
        return title;
    }

    public void setFirstname(String firstname) {
        this.title = firstname;
    }

    public String getLastname() {
        return artist;
    }

    public void setLastname(String lastname) {
        this.artist = lastname;
    }

    public String getLabel() {
        return label;
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
        private int id;
        private String firstname;
        private String lastname;
        private String password;

        private Builder() {
        }

        public Song.Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Song.Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Song.Builder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Song.Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
    }
}
