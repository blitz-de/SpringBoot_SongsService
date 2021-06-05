package htwb.ai.dao;

import java.util.List;

import htwb.ai.model.User;

public interface IUserDAO {

    public List<User> getAllUsers();
    public User getUserByUserId (String userId);
    public String save(User User);
    public void updateUser(User User);
    public void deleteUser(String userId);
}
