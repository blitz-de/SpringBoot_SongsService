package htwb.ai.dao;

import htwb.ai.model.User;

public class DBUserDAO implements IUserDAO {

    // private static EMF emf;
    private String persistenceUnit;

    public void setPersistenceUnit(String pUnit) {
        System.out.println("I'm instanciated: " + pUnit);
        this.persistenceUnit = pUnit;

    }

    public User getUserById(int id) {
        if (id == 1) {
            User fred = User.builder()
                    .withId(1)
                    .withFirstname("FRED")
                    .withLastname("Schmidt")
                    .withUserId("fschmidt")
                    .withPassword("geheim").build();
            return fred;
        }
        return null;
    }

    public User getUserByUserId(String userId) {
        if (userId != null && userId.equals("fschmidt")) {
            return User.builder()
                    .withId(1)
                    .withFirstname("FRED")
                    .withLastname("Schmidt")
                    .withUserId("fschmidt")
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