package htwb.ai.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import htwb.ai.model.DAOUser;

@Repository
public interface UserRepo extends JpaRepository<DAOUser, Integer> {
	
//	Optional<DAOUser> findByUsername(String username);
	DAOUser findByUsername(String username);
}