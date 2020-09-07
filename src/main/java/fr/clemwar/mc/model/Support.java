package fr.clemwar.mc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the supports database table.
 * 
 */
@Entity
@Table(name="supports")
@NamedQuery(name="Support.findAll", query="SELECT s FROM Support s")
public class Support implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="support_id")
	private Integer supportId;

	private Integer ean;

	@Column(name="support_type")
	private Integer supportType;

	//bi-directional many-to-many association to Film
	@ManyToMany(mappedBy="supports")
	private List<Film> films;

	public Support() {
	}

	public Integer getSupportId() {
		return this.supportId;
	}

	public void setSupportId(Integer supportId) {
		this.supportId = supportId;
	}

	public Integer getEan() {
		return this.ean;
	}

	public void setEan(Integer ean) {
		this.ean = ean;
	}

	public Integer getSupportType() {
		return this.supportType;
	}

	public void setSupportType(Integer supportType) {
		this.supportType = supportType;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}