package htwb.ai.model;

import javax.persistence.criteria.CriteriaBuilder;

public class Song {
    private int id;
    @Override
    public String toString() {
        return "Song [id=" + id + ", songId=" + songId + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }

    private Integer songId;
    private String firstname;
    private String lastname;
    private String password;

    private Song(Song.Builder builder) {
        this.id = builder.id;
        this.songId = builder.songId;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.password = builder.password;
    }

    public Song() {}

    public Song(Integer songId) {
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songid) {
        this.songId = songid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Creates builder to build {@link Song}.
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
        private Integer songId;
        private String firstname;
        private String lastname;
        private String password;

        private Builder() {
        }

        public Song.Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Song.Builder withSongId(Integer songId) {
            this.songId = songId;
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
