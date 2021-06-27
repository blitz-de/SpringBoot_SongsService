package htwb.ai.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import htwb.ai.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	
//	Optional<DAOUser> findByUsername(String username);
	Users findByUsername(String username);
}