package htwb.ai.controller;

import htwb.ai.dao.UserDao;
//import htwb.ai.dao.IUserDAO;
import htwb.ai.model.DAOUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class UserController {
    private UserDao userDAO;

    public UserController(UserDao dao) {
        this.userDAO = dao;

    }

    // GET http://localhost:8080/authSpring/rest/auth/eschuler
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<DAOUser> getUser(@PathVariable(value = "id") String username) throws IOException {
        DAOUser user = userDAO.findByUsername(username);
//        		userDAO.getUserByUserId(username);
        if (user == null) {
            return new ResponseEntity<DAOUser>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DAOUser>(user, HttpStatus.OK);
    }

//    @PostMapping(value = "/", consumes = { "application/json" })
//    public ResponseEntity<String> authorize(@RequestBody User user) {
//        if (user == null || user.getUserId().equals("") || user.getUserId() == null || user.getPassword().equals("")
//                || user.getPassword() == null)
//            return new ResponseEntity<String>("something wrong with body probably...", HttpStatus.BAD_REQUEST);
//        User u = userDAO.findByUsername(user.getUserId());
////        		userDAO.getUserByUserId(user.getUserId());
//        if (u == null)
//            return new ResponseEntity<String>("not found...", HttpStatus.NOT_FOUND);
//
//        String sessionId = "not matched...";
//        if (u.getPassword().equals(user.getPassword())) {
//            sessionId = userDAO.generateToken();
//            return new ResponseEntity<String>(sessionId, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<String>(sessionId, HttpStatus.UNAUTHORIZED);
//
//    }

    @GetMapping(value = "/users")
    public Iterable<DAOUser> getUsers() {
        return userDAO.findAll();
//    	return userDAO.getAllUsers();
    }

}
