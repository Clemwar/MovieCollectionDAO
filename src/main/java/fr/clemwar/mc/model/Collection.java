package fr.clemwar.mc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the collections database table.
 * 
 */
@Entity
@Table(name="collections")
@NamedQuery(name="Collection.findAll", query="SELECT c FROM Collection c")
public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coll_id")
	private Integer collId;

	@Column(name="coll_name")
	private String collName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-many association to Film
	@ManyToMany
	@JoinTable(
		name="collections_films"
		, joinColumns={
			@JoinColumn(name="coll_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="film_id")
			}
		)
	private List<Film> films;

	public Collection() {
	}

	public Integer getCollId() {
		return this.collId;
	}

	public void setCollId(Integer collId) {
		this.collId = collId;
	}

	public String getCollName() {
		return this.collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}