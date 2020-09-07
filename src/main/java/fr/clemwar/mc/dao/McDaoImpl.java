package fr.clemwar.mc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.clemwar.mc.model.Collection;
import fr.clemwar.mc.model.Film;
import fr.clemwar.mc.model.Support;
import fr.clemwar.mc.model.User;

@Component
public class McDaoImpl implements McDao {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CollectionRepository collectionRepository;
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	SupportRepository supportRepository;

	@Override
	public User add(User user) {
		if (user != null)
			return userRepository.save(user);
		else
			return null;
	}

	@Override
	public User getUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent())
			return user.get();
		else
			return null;
	}

	@Override
	public User getUserByCredentials(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User update(User user) {
		if (user != null)
			return userRepository.save(user);
		else
			return null;
	}

	@Override
	public void delete(User user) {
		if (user != null)
			userRepository.delete(user);
	}

	@Override
	public Collection add(Collection collection) {
		if (collection != null)
			return collectionRepository.save(collection);
		else
			return null;
	}

	@Override
	public Collection getColelectionByUser(User user) {
		return collectionRepository.findByUser(user);
	}

	@Override
	public Collection update(Collection collection) {
		if (collection != null)
			return collectionRepository.save(collection);
		else
			return null;
	}

	@Override
	public void delete(Collection collection) {
		collectionRepository.delete(collection);
	}

	@Override
	public Collection addMovie(Collection collection, Film film) {
		if (collection != null && film != null) {
			List<Film> mList = collection.getFilms();
			if (mList == null) {
				mList = new ArrayList<Film>();
			}
			if (!mList.contains(film)) {
				if (mList.add(film)) {
					collection.setFilms(mList);
					return collectionRepository.save(collection);
				}
			}
		}
		return null;
	}

	@Override
	public Collection removeMovie(Collection collection, Film film) {
		if (collection != null && film != null) {
			List<Film> mList = collection.getFilms();
			if (mList != null && mList.contains(film)) {
				if (mList.remove(film)) {
					collection.setFilms(mList);
					return collectionRepository.save(collection);
				}
			}
		}
		return null;
	}

	@Override
	public Film add(Film film) {
		if (film != null)
			return filmRepository.save(film);
		else
			return null;
	}

	@Override
	public Film update(Film film) {
		if (film != null)
			return filmRepository.save(film);
		else
			return null;
	}

	@Override
	public void delete(Film film) {
		filmRepository.delete(film);
	}

	@Override
	public Film addSupport(Film film, Support support) {
		if (film != null && support != null) {
			List<Support> sList = film.getSupports();
			if (sList == null) {
				sList = new ArrayList<Support>();
			}
			if (!sList.contains(support)) {
				if (sList.add(support)) {
					film.setSupports(sList);
					return filmRepository.save(film);
				}
			}
		}
		return null;
	}

	@Override
	public Film removeSupport(Film film, Support support) {
		if (film != null && support != null) {
			List<Support> sList = film.getSupports();
			if (sList != null && sList.contains(support)) {
				if (sList.remove(support)) {
					film.setSupports(sList);
					return filmRepository.save(film);
				}
			}
		}
		return null;
	}

	@Override
	public Support add(Support support) {
		if (support != null)
			return supportRepository.save(support);
		else
			return null;
	}

	@Override
	public Support getSupport(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Support update(Support support) {
		if (support != null)
			return supportRepository.save(support);
		else
			return null;
	}

	@Override
	public void delete(Support support) {
		supportRepository.delete(support);
	}

}
