package htwb.ai.dao;

import htwb.ai.model.Song;
import htwb.ai.model.User;

public class DBSongDAO {
    private String persistenceUnit;

    public void setPersistenceUnit(String pUnit) {
        System.out.println("I'm instanciated: " + pUnit);
        this.persistenceUnit = pUnit;

    }

    public Song getSongById(int id) {
        if (id == 1) {
            Song fred = Song.builder()
                    .withId(1)
                    .withFirstname("FRED")
                    .withLastname("Schmidt")
                    .withSongId(1)
                    .withPassword("geheim").build();
            return fred;
        }
        return null;
    }

    public Song getUserByUserId(String songId) {
        if (songId != null && songId.equals("fschmidt")) {
            return Song.builder()
                    .withId(1)
                    .withFirstname("FRED")
                    .withLastname("Schmidt")
                    .withSongId(1)
                    .withPassword("geheim").build();
        }
        return null;
    }

    public Integer addUser(User User) {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateUser(User User) {
        // TODO Auto-generated method stub
    }

    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
    }
}
