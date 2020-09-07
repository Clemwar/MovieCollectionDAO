package fr.clemwar.mc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.clemwar.mc.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * 
	 * @param email String
	 * @param password String
	 * @return User
	 */
	public User findByEmailAndPassword(String email, String password);
	
}
