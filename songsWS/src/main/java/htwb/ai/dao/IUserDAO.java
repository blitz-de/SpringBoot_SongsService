package htwb.ai.dao;

import htwb.ai.model.User;

public interface IUserDAO {

    public User getUserById (int id);
    public User getUserByUserId (String userId);
    public Integer addUser(User User);
    public void updateUser(User User);
    public void deleteUser(String userId);
}
