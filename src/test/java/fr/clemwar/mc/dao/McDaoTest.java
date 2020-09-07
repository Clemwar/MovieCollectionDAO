package fr.clemwar.mc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.clemwar.mc.model.Collection;
import fr.clemwar.mc.model.Film;
import fr.clemwar.mc.model.Support;
import fr.clemwar.mc.model.User;

@SpringBootTest(classes = { Application.class })
class McDaoTest {

	private static McDao mcDao;

	@Autowired
	private void setMcDao(McDao mcDao) {
		McDaoTest.mcDao = mcDao;
	}

	private static List<User> users;
	private static List<Film> films;
	private static List<Collection> collections;
	private static List<Support> supports;

	private User getUser() {
		User user = new User();
		user.setFirstName("Bob");
		user.setLastName("Razowski");
		user.setEmail("bob.razowski@monsterandco.com");
		user.setPassword("bwaaah!");
		return user;
	}
	
	private Collection getCollection() {
		Collection collection = new Collection();
		collection.setCollName("Mes films");
		collection.setUser(mcDao.getUser(27));
		return collection;
	}
	
	private Film getFilm() {
		Film film = new Film();
		film.setTitle("Moulin Rouge");
		film.setImdbId("tt0203009");
		return film;
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		users = new ArrayList<User>();
		films = new ArrayList<Film>();
		collections = new ArrayList<Collection>();
		supports = new ArrayList<Support>();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		for (User user : users) {
			mcDao.delete(user);
		}
		for (Collection collection : collections) {
			Film film = collection.getFilms().get(0);
			mcDao.removeMovie(collection, film);
			mcDao.delete(collection);
		}
		for (Film film : films) {
			mcDao.delete(film);
		}
		for (Support support : supports) {
			mcDao.delete(support);
		}
	}

	@Test
	void addUserTest() {
		User user1 = null;
		if (users.isEmpty()) {
			User user = getUser();
			user1 = mcDao.add(user);
			users.add(user1);
		} else {
			user1 = users.get(0);
		}
		User user2 = mcDao.getUserByCredentials("bob.razowski@monsterandco.com", "bwaaah!");
		assertEquals(user1.getEmail(), user2.getEmail());
	}

	@Test
	void updateUserTest() {
		User newUser = null;
		if (users.isEmpty()) {
			newUser = mcDao.add(getUser());
			users.add(newUser);
		} else {
			newUser = users.get(0);
		}
		newUser.setFirstName("Bobby");
		mcDao.update(newUser);
		User updatedUser = mcDao.getUser(newUser.getUserId());
		assertEquals(updatedUser.getFirstName(), newUser.getFirstName());
	}
	
	@Test
	void addMovieTest() {
		Film film = mcDao.add(getFilm());
		films.add(film);
		Collection coll = mcDao.add(getCollection());
		collections.add(coll);
		
		Collection coll2 = mcDao.addMovie(coll, film);
		assert(true);
	}

}
