package fr.clemwar.mc.dao;

import fr.clemwar.mc.model.Collection;
import fr.clemwar.mc.model.Film;
import fr.clemwar.mc.model.Support;
import fr.clemwar.mc.model.User;

public interface McDao {
	
	// ============ Users =================
	public User add(User user);
	public User getUser(int id);
	public User getUserByCredentials(String email, String password);
	public User update(User user);
	public void delete(User user);
	
	// ============ Collections =================
	public Collection add(Collection collection);
	public Collection getColelectionByUser(User user);
	public Collection update(Collection collection);
	public void delete(Collection collection);
	public Collection addMovie(Collection collection, Film film);
	public Collection removeMovie(Collection collection, Film film);
	
	// ============ Films =================
	public Film add(Film film);
	public Film update(Film film);
	public void delete(Film film);
	public Film addSupport(Film film, Support support);
	public Film removeSupport(Film film, Support support);
	
	// ============ Supports =================
	public Support add(Support support);
	public Support getSupport(int id);
	public Support update(Support support);
	public void delete(Support support);

}
