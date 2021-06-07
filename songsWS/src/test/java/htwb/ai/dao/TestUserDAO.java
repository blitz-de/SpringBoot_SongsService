package htwb.ai.dao;

import htwb.ai.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestUserDAO implements IUserDAO {

    private Map<String, User> myUsers;

    public TestUserDAO() {

        myUsers = new ConcurrentHashMap<String, User>();

        User bob = User.builder().withUserId("finkin")
                .withPassword("secret")
                .withFirstName("davis")
                .withLastName("sky").build();

        myUsers.put(bob.getUserId(), bob);
    }

    public User getUserById (String uName) {
        Collection<User> allUsers = myUsers.values();
        for(User u : allUsers) {
            if (u.getUserId().equals(uName)) {return u;}
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        list.add(myUsers.get("finkin"));
        return list ;
    }

    @Override
    public User getUserByUserId (String userId) {
        return myUsers.get(userId);
    }

    @Override
    public String save(User User) {
        return User.getUserId();
    }

    @Override
    public void updateUser(User User) {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
    }
    @Override
    public String generateToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}