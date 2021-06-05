package htwb.ai.dao;

import htwb.ai.model.Song;
import htwb.ai.model.User;

import java.util.List;

public interface ISongDAO{
    public User getUserById (int id);
    public List<User> getAllUsers();
    //public void updateUser(int id);
    public User getUserByUserId (String userId);
    public Integer save(User User);
    public void updateUser(User User);
    public void deleteUser(String userId);
}
