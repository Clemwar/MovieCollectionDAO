package fr.clemwar.mc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.clemwar.mc.model.Collection;
import fr.clemwar.mc.model.User;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
	
	public Collection findByUser(User user);

}
