//package htwb.ai.dao;
//
//import htwb.ai.model.User;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class UserDAO implements IUserDAO {
//    private Map<String, User> myUsers;
//
//    public UserDAO() {
//        myUsers = new ConcurrentHashMap<String, User>();
//
//        User alfred = User.builder().withId(1)
//                .withFirstname("Alfred")
//                .withLastname("Schmidt")
//                .withUserId("aschmidt")
//                .withPassword("geheim").build();
//
//        myUsers.put(alfred.getUsername(), alfred);
//    }
//
//    public User getUserById (int id) {
//        Collection<User> allUsers = myUsers.values();
//        for(User u : allUsers) {
//            if (u.getId() == id) {return u;}
//        }
//        return null;
//    }
//
//    public User getUserByUserId (String userId) {
//        return myUsers.get(userId);
//    }
//
//    public Integer addUser(User User) {
//        // TODO Auto-generated method stub
//        return 1;
//    }
//
//    public void updateUser(User User) {
//        // TODO Auto-generated method stub
//    }
//
//    public void deleteUser(String userId) {
//        // TODO Auto-generated method stub
//    }
//}
