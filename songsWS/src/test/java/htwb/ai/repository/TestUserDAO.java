//package htwb.ai.dao;
//
//import htwb.ai.model.DAOUser;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class TestUserDAO implements IUserDAO {
//
//    private Map<String, DAOUser> myUsers;
//
//    public TestUserDAO() {
//
//        myUsers = new ConcurrentHashMap<String, DAOUser>();
//
//        DAOUser bob = DAOUser.builder().withUserId("finkin")
//                .withPassword("secret")
//                .withFirstName("davis")
//                .withLastName("sky").build();
//
//        myUsers.put(bob.getUserId(), bob);
//    }
//
//    public DAOUser getUserById (String uName) {
//        Collection<DAOUser> allUsers = myUsers.values();
//        for(DAOUser u : allUsers) {
//            if (u.getUserId().equals(uName)) {return u;}
//        }
//        return null;
//    }
//
//    @Override
//    public List<DAOUser> getAllUsers() {
//        List<DAOUser> list = new ArrayList<DAOUser>();
//        list.add(myUsers.get("finkin"));
//        return list ;
//    }
//
//    @Override
//    public DAOUser getUserByUserId (String userId) {
//        return myUsers.get(userId);
//    }
//
//    @Override
//    public String save(DAOUser User) {
//        return User.getUserId();
//    }
//
//    @Override
//    public void updateUser(DAOUser User) {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//        // TODO Auto-generated method stub
//    }
//    @Override
//    public String generateToken() {
//        String uuid = UUID.randomUUID().toString();
//        return uuid;
//    }
//}