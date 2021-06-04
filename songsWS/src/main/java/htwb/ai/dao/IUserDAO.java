package htwb.ai.dao;

import java.util.List;

import htwb.ai.model.User;

public interface IUserDAO {

    public User getUserById (int id);
    public List<User> getAllUsers();
    public User getUserByUserId (String userId);
    public Integer save(User User);
    public void updateUser(User User);
    public void deleteUser(String userId);
}
