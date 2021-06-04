package htwb.ai.dao;

import htwb.ai.model.Song;
import htwb.ai.model.User;

public interface ISongDAO{
    public User getSongById (int id);
    public User getById (Integer songId);
    public Integer add(Song song);
    public void update(Song song);
    public void delete(Integer songId);
}
