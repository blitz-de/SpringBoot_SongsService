package htwb.ai.dao;

import htwb.ai.model.Song;
import htwb.ai.model.User;

import java.util.List;

public interface ISongDAO{
    public Song getById (int id);
    public List<Song> getAll();
    //public void updateUser(int id);
    public Song getById (String userId);
    public Integer save(Song User);
    public void update(Song User);
    public void delete(String userId);
}
