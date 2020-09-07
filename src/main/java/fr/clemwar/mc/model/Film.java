package fr.clemwar.mc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the films database table.
 * 
 */
@Entity
@Table(name="films")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id")
	private Integer filmId;

	@Column(name="imdb_id")
	private String imdbId;

	private String title;

	//bi-directional many-to-many association to Collection
	@ManyToMany(mappedBy="films")
	private List<Collection> collections;

	//bi-directional many-to-many association to Support
	@ManyToMany
	@JoinTable(
		name="films_supports"
		, joinColumns={
			@JoinColumn(name="film_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="support_id")
			}
		)
	private List<Support> supports;

	public Film() {
	}

	public Integer getFilmId() {
		return this.filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public String getImdbId() {
		return this.imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<Support> getSupports() {
		return this.supports;
	}

	public void setSupports(List<Support> supports) {
		this.supports = supports;
	}

}