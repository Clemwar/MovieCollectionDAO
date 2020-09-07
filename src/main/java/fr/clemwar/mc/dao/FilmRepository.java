package fr.clemwar.mc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.clemwar.mc.model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
