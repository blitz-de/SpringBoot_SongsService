package htwb.ai.controller;

import htwb.ai.dao.IUserDAO;
import htwb.ai.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

@RestController
@RequestMapping(value = "/auth")
public class UserController {
    private IUserDAO userDAO;

    public UserController(IUserDAO dao) {
        this.userDAO = dao;

    }

    //GET http://localhost:8080/authSpring/rest/auth/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "id") String username) throws IOException {
        User user = userDAO.getUserByUserId(username);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/", consumes = {"application/json"})
    public ResponseEntity<String> authorize(@RequestBody User user) {
        User u = userDAO.getUserByUserId(user.getUserId());
        if (u == null) return new ResponseEntity<String>("not found...", HttpStatus.NOT_FOUND);

        String sessionId = "not matched...";
        if (u.getPassword().equals(user.getPassword())) {
            sessionId = user.getUserId() + "-123-session";
            return new ResponseEntity<String>(sessionId, HttpStatus.OK);
        }

        return new ResponseEntity<String>(sessionId, HttpStatus.NOT_FOUND);


    }


    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userDAO.getAllUsers();
    }

}
